package com.roshka.sifen.model.envi;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.util.HttpUtil;
import com.roshka.sifen.util.SifenExceptionUtil;
import com.roshka.sifen.sdk.v150.beans.DocumentoElectronico;
import com.roshka.sifen.model.de.TgCamDEAsoc;
import com.roshka.sifen.model.de.TgPagCont;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.model.Constants;
import com.roshka.sifen.ssl.SSLContextHelper;
import com.sun.deploy.net.HttpUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;

import static com.roshka.sifen.model.Constants.SIFEN_CURRENT_VERSION;

public class REnviDe extends REnviBase {
    public static final String TAG_NAME = "rEnviDe";

    private DocumentoElectronico DE;

    @Override
    public void setupSOAPBody(SOAPBody soapBody, SifenConfig sifenConfig) throws SifenException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            // Main Element
            SOAPBodyElement rResEnviDe = soapBody.addBodyElement(
                    new QName(Constants.SIFEN_NS_URI, TAG_NAME, Constants.SIFEN_NS_PREFIX)
            );

            rResEnviDe.addChildElement("dId", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.getdId()));

            SOAPElement rDe = rResEnviDe.addChildElement("xDe", Constants.SIFEN_NS_PREFIX)
                    .addChildElement("rDe", Constants.SIFEN_NS_PREFIX);

            rDe.addChildElement("dVerFor", Constants.SIFEN_NS_PREFIX).setTextContent(SIFEN_CURRENT_VERSION);

            SOAPElement DE = rDe.addChildElement("DE", Constants.SIFEN_NS_PREFIX);
            DE.addAttribute(new QName("Id"), this.DE.getId());
            DE.addChildElement("dDVId", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.DE.getdDVId()));
            DE.addChildElement("dFecFirma",Constants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.DE.getdFecFirma()));
            DE.addChildElement("dSisFact", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.DE.getdSisFact()));

            TTiDE iTiDE = this.DE.getgTimb().getTiDE();
            this.DE.getgOpeDE().setupSOAPElements(DE, iTiDE);
            this.DE.getgTimb().setupSOAPElements(DE);
            this.DE.getdDatGralOpe().setupSOAPElements(DE, iTiDE);
            this.DE.getgDtipDE().setupSOAPElements(DE, iTiDE, this.DE.getdDatGralOpe());

            if (iTiDE.getVal() != 7)
                this.DE.getgTotSub().setupSOAPElements(DE, iTiDE, this.DE.getdDatGralOpe().getgOpeCom());

            if (this.DE.getgCamGen() != null)
                this.DE.getgCamGen().setupSOAPElements(DE, iTiDE);

            if (iTiDE.getVal() == 4 || iTiDE.getVal() == 5 || iTiDE.getVal() == 6 || ((iTiDE.getVal() == 1 || iTiDE.getVal() == 7) && this.DE.getgCamDEAsocList().size() > 0)) {
                boolean retencionExists = false;
                for (TgPagCont gPaCondEIni : this.DE.getgDtipDE().getgCamCond().getgPaCondEIniList()) {
                    if (gPaCondEIni.getiTiPago().getVal() == 10) {
                        retencionExists = true;
                        break;
                    }
                }

                for (TgCamDEAsoc gCamDEAsoc : this.DE.getgCamDEAsocList()) {
                    gCamDEAsoc.setupSOAPElements(DE, this.DE.getdDatGralOpe().getgOpeCom().getTipTra(), retencionExists);
                }
            }

            // Firma Digital del XML
            SignedInfo signedInfo = this.signFields(rDe, sifenConfig);

            // Preparación de la URL del QR
            HashMap<String, String> queryParams = new HashMap<>();
            queryParams.put("nVersion", SIFEN_CURRENT_VERSION);
            queryParams.put("Id", this.DE.getId());
            queryParams.put("dFeEmiDE", HexBin.encode(dateFormat.format(this.DE.getdDatGralOpe().getdFeEmiDE()).getBytes(StandardCharsets.UTF_8)));

            if (this.DE.getdDatGralOpe().getgDatRec().getiNatRec().getVal() == 1) {
                queryParams.put("dRucRec", this.DE.getdDatGralOpe().getgDatRec().getdRucRec());
            } else if (this.DE.getdDatGralOpe().getgDatRec().getiTiOpe().getVal() != 4 && this.DE.getdDatGralOpe().getgDatRec().getdNumIDRec() != null) {
                queryParams.put("dNumIDRec", this.DE.getdDatGralOpe().getgDatRec().getdNumIDRec());
            } else {
                queryParams.put("dNumIDRec", "0");
            }

            if (iTiDE.getVal() != 7) {
                queryParams.put("dTotGralOpe", String.valueOf(this.DE.getgTotSub().getdTotGralOpe()));
                queryParams.put("dTotIVA",
                        this.DE.getdDatGralOpe().getgOpeCom().getiTImp().getVal() == 1 || this.DE.getdDatGralOpe().getgOpeCom().getiTImp().getVal() == 5
                                ? String.valueOf(this.DE.getgTotSub().getdTotIVA())
                                : "0"
                );
            } else {
                queryParams.put("dTotGralOpe", "0");
                queryParams.put("dTotIVA", "0");
            }

            queryParams.put("cItems", String.valueOf(this.DE.getgDtipDE().getgCamItemList().size()));
            queryParams.put("DigestValue", HexBin.encode(((Reference) signedInfo.getReferences().get(0)).getDigestValue()));
            queryParams.put("IdCSC", "");

            String urlParamsString = HttpUtil.buildUrlParams(queryParams);

            String CSC = "";
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String hashedParams = HexBin.encode(digest.digest((urlParamsString + CSC).getBytes(StandardCharsets.UTF_8)));

            String dCarQR = sifenConfig.getUrlConsultaQr() + urlParamsString + "&cHashQR=" + hashedParams;
            dCarQR = dCarQR.replace("&", "&amp;");

            SOAPElement gCamFuFD = rDe.addChildElement("gCamFuFD", Constants.SIFEN_NS_PREFIX);
            gCamFuFD.addChildElement("dCarQR", Constants.SIFEN_NS_PREFIX).setTextContent(dCarQR);
        } catch (SOAPException | NoSuchAlgorithmException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    private SignedInfo signFields(Node nodeToSign, SifenConfig sifenConfig) throws SifenException {
        try {
            XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance();
            Reference ref = sigFactory.newReference(this.DE.getId(), sigFactory.newDigestMethod(DigestMethod.SHA256, null));
            SignedInfo signedInfo = sigFactory.newSignedInfo(
                    sigFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    sigFactory.newSignatureMethod(Constants.RSA_SHA256, null),
                    Collections.singletonList(ref)
            );

            KeyPair keyPair = SSLContextHelper.getCertificateKeyPair(sifenConfig);
            KeyInfoFactory keyInfoFactory = sigFactory.getKeyInfoFactory();
            KeyValue keyValue = keyInfoFactory.newKeyValue(keyPair.getPublic());
            KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(keyValue));

            XMLSignature signature = sigFactory.newXMLSignature(signedInfo, keyInfo);
            DOMSignContext signatureContext = new DOMSignContext(keyPair.getPrivate(), nodeToSign);
            signatureContext.putNamespacePrefix(XMLSignature.XMLNS, Constants.SIFEN_NS_PREFIX);
            signature.sign(signatureContext);

            return signedInfo;
        } catch (KeyException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | XMLSignatureException | MarshalException e) {
            throw SifenExceptionUtil.requestSigningError("Ocurrió un error al firmar la petición SOAP utilizando el certificado activo", e);
        }
    }

    public void setDE(DocumentoElectronico DE) {
        this.DE = DE;
    }
}
