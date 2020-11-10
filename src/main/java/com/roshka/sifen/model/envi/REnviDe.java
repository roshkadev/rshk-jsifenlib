package com.roshka.sifen.model.envi;

import com.roshka.sifen.model.NamespacesConstants;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class REnviDe extends REnviBase {
    public static final String TAG_NAME = "rEnviDe";

    @Override
    public void setupSOAPBody(SOAPBody soapBody) throws SOAPException {
        // main element
        SOAPBodyElement rResEnviConsRUC = soapBody.addBodyElement(
                new QName(NamespacesConstants.SIFEN_NS_URI, TAG_NAME, NamespacesConstants.SIFEN_NS_PREFIX)
        );

        // dId
        SOAPElement dId = rResEnviConsRUC.addChildElement("dId", NamespacesConstants.SIFEN_NS_PREFIX);
        dId.setTextContent(String.valueOf(this.getdId()));

        // xDe
        SOAPElement xDe = rResEnviConsRUC.addChildElement("xDe", NamespacesConstants.SIFEN_NS_PREFIX);
        dId.setTextContent("");
    }
}