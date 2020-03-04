package com.roshka.sifen.soap;

import com.roshka.sifen.model.NamespacesConstants;

import javax.xml.soap.*;

public class EnvelopeHelper {

    public static void setupEnvelope(SOAPMessage soapMessage)
        throws SOAPException
    {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        // do something with envelope
        envelope.addNamespaceDeclaration(NamespacesConstants.SIFEN_NS_PREFIX, NamespacesConstants.SIFEN_NS_URI);
    }


}
