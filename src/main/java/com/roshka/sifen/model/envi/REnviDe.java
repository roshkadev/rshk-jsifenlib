package com.roshka.sifen.model.envi;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionUtil;
import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.TDE;
import com.roshka.sifen.model.de.TgCamDEAsoc;
import com.roshka.sifen.model.de.TgPagCont;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.sdk.Constants;
import com.roshka.sifen.ssl.SSLContextHelper;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static com.roshka.sifen.sdk.Constants.SIFEN_CURRENT_VERSION;

public class REnviDe extends REnviBase {
    public static final String TAG_NAME = "rEnviDe";

    private TDE DE;

    @Override
    public void setupSOAPBody(SOAPBody soapBody, SifenConfig sifenConfig) throws SifenException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            // Main Element
            SOAPBodyElement rResEnviDe = soapBody.addBodyElement(
                    new QName(NamespacesConstants.SIFEN_NS_URI, TAG_NAME, NamespacesConstants.SIFEN_NS_PREFIX)
            );

            rResEnviDe.addChildElement("dId", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.getdId()));

            SOAPElement rDe = rResEnviDe.addChildElement("xDe", NamespacesConstants.SIFEN_NS_PREFIX)
                    .addChildElement("rDe", NamespacesConstants.SIFEN_NS_PREFIX);

            rDe.addChildElement("dVerFor", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(SIFEN_CURRENT_VERSION);

            SOAPElement DE = rDe.addChildElement("DE", NamespacesConstants.SIFEN_NS_PREFIX);
            DE.addAttribute(new QName("Id"), this.DE.getId());
            DE.addChildElement("dDVId", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.DE.getdDVId()));
            DE.addChildElement("dFecFirma",NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.DE.getdFecFirma()));
            DE.addChildElement("dSisFact", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.DE.getdSisFact()));

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

            SOAPElement gCamFuFD = rDe.addChildElement("gCamFuFD", NamespacesConstants.SIFEN_NS_PREFIX);
            gCamFuFD.addChildElement("dCarQR", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent("");

            // Firma Digital del XML
            this.signFields(rDe, sifenConfig);
        } catch (SOAPException e) {
            throw SifenExceptionUtil.errorPreparacionPeticion("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    private void signFields(Node nodeToSign, SifenConfig sifenConfig) throws SifenException {
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
            signatureContext.putNamespacePrefix(XMLSignature.XMLNS, NamespacesConstants.SIFEN_NS_PREFIX);
            signature.sign(signatureContext);
        } catch (KeyException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | XMLSignatureException | MarshalException e) {
            throw SifenExceptionUtil.errorFirmaPeticion("Ocurrió un error al firmar la petición SOAP utilizando el certificado activo", e);
        }
    }
}
