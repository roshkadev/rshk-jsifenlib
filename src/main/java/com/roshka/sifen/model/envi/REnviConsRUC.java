package com.roshka.sifen.model.envi;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.util.SifenExceptionUtil;
import com.roshka.sifen.model.Constants;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;

public class REnviConsRUC extends REnviBase {
    public static final String TAG_NAME = "rEnviConsRUC";

    private String dRUCCons;

    @Override
    public void setupSOAPBody(SOAPBody soapBody, SifenConfig sifenConfig) throws SifenException {
        try {
            // Main Element
            SOAPBodyElement rResEnviConsRUC = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, TAG_NAME));

            // Elements
            rResEnviConsRUC.addChildElement("dId").setTextContent(String.valueOf(this.getdId()));
            rResEnviConsRUC.addChildElement("dRUCCons").setTextContent(this.getdRUCCons());
        } catch (SOAPException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    public String getdRUCCons() {
        return dRUCCons;
    }

    public void setdRUCCons(String dRUCCons) {
        this.dRUCCons = dRUCCons;
    }
}
