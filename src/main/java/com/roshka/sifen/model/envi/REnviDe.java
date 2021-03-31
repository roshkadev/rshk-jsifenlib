package com.roshka.sifen.model.envi;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.Constants;
import com.roshka.sifen.sdk.v150.beans.DocumentoElectronico;
import com.roshka.sifen.soap.SignatureHelper;
import com.roshka.sifen.util.HttpUtil;
import com.roshka.sifen.util.SifenExceptionUtil;
import com.roshka.sifen.util.SifenUtil;
import org.w3c.dom.Attr;

import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.LinkedHashMap;

import static com.roshka.sifen.model.Constants.SIFEN_CURRENT_VERSION;

public class REnviDe extends REnviBase {
    public static final String TAG_NAME = "rEnviDe";

    private DocumentoElectronico DE;

    @Override
    public void setupSOAPBody(SOAPBody soapBody, SifenConfig sifenConfig) throws SifenException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            this.DE.generateCDC();

            // Main Element
            SOAPBodyElement rResEnviDe = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, TAG_NAME));
            rResEnviDe.addChildElement("dId").setTextContent(String.valueOf(this.getdId()));

            SOAPElement rDE = rResEnviDe.addChildElement("xDE").addChildElement(new QName(Constants.SIFEN_NS_URI, "rDE"));

            rDE.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rDE.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", Constants.SIFEN_NS_URI_RECEP_DE);

            rDE.addChildElement("dVerFor").setTextContent(SIFEN_CURRENT_VERSION);

            SOAPElement DE = rDE.addChildElement("DE");
            DE.setAttribute("Id", this.DE.getId());
            Attr idAttribute = DE.getAttributeNode("Id");
            DE.setIdAttributeNode(idAttribute, true);

            DE.addChildElement("dDVId").setTextContent(this.DE.getdDVId());
            DE.addChildElement("dFecFirma").setTextContent(this.DE.getdFecFirma().format(formatter));
            DE.addChildElement("dSisFact").setTextContent(String.valueOf(this.DE.getdSisFact()));

            // Se completa con los demás elementos del XML
            this.DE.setupSOAPElements(DE);

            // Firma Digital del XML
            SignedInfo signedInfo = SignatureHelper.signDocument(sifenConfig, rDE, this.DE.getId());

            // Preparación de la URL del QR
            SOAPElement gCamFuFD = rDE.addChildElement("gCamFuFD");
            gCamFuFD.addChildElement("dCarQR").setTextContent(this.generateQRLink(signedInfo, sifenConfig));
        } catch (SOAPException | NoSuchAlgorithmException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    private String generateQRLink(SignedInfo signedInfo, SifenConfig sifenConfig) throws NoSuchAlgorithmException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LinkedHashMap<String, String> queryParams = new LinkedHashMap<>();

        queryParams.put("nVersion", SIFEN_CURRENT_VERSION);
        queryParams.put("Id", this.DE.getId());
        queryParams.put("dFeEmiDE", SifenUtil.bytesToHex(this.DE.getdDatGralOpe().getdFeEmiDE().format(formatter).getBytes(StandardCharsets.UTF_8)));

        if (this.DE.getdDatGralOpe().getgDatRec().getiNatRec().getVal() == 1) {
            queryParams.put("dRucRec", this.DE.getdDatGralOpe().getgDatRec().getdRucRec());
        } else if (this.DE.getdDatGralOpe().getgDatRec().getiTiOpe().getVal() != 4 && this.DE.getdDatGralOpe().getgDatRec().getdNumIDRec() != null) {
            queryParams.put("dNumIDRec", this.DE.getdDatGralOpe().getgDatRec().getdNumIDRec());
        } else {
            queryParams.put("dNumIDRec", "0");
        }

        if (this.DE.getgTimb().getTiDE().getVal() != 7) {
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

        byte[] digestValue = Base64.getEncoder().encode(((Reference) signedInfo.getReferences().get(0)).getDigestValue());
        queryParams.put("DigestValue", SifenUtil.bytesToHex(digestValue));
        queryParams.put("IdCSC", sifenConfig.getIdCSC());

        String urlParamsString = HttpUtil.buildUrlParams(queryParams);
        String hashedParams = SifenUtil.sha256Hex(urlParamsString + sifenConfig.getCSC());

        return sifenConfig.getUrlConsultaQr() + urlParamsString + "&cHashQR=" + hashedParams;
    }

    public void setDE(DocumentoElectronico DE) {
        this.DE = DE;
    }
}