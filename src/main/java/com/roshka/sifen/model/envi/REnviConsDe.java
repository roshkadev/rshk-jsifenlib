package com.roshka.sifen.model.envi;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.Constants;
import com.roshka.sifen.util.SifenExceptionUtil;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;

public class REnviConsDe extends REnviBase {
    public static final String TAG_NAME = "rEnviConsDeRequest";

    private String dCDC;

    @Override
    public void setupSOAPBody(SOAPBody soapBody, SifenConfig sifenConfig) throws SifenException {
        try {
            // Main Element
            SOAPBodyElement rEnviConsDeRequest = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, TAG_NAME));

            // Elements
            rEnviConsDeRequest.addChildElement("dId").setTextContent(String.valueOf(this.getdId()));
            rEnviConsDeRequest.addChildElement("dCDC").setTextContent(this.getdCDC());
        } catch (SOAPException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    public String getdCDC() {
        return dCDC;
    }

    public void setdCDC(String dCDC) {
        this.dCDC = dCDC;
    }
}