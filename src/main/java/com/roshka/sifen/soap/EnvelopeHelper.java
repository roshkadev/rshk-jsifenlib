package com.roshka.sifen.soap;

import com.roshka.sifen.model.Constants;

import javax.xml.soap.*;

public class EnvelopeHelper {
    public static void setupEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        // do something with envelope
        envelope.addNamespaceDeclaration(Constants.SIFEN_NS_PREFIX, Constants.SIFEN_NS_URI);
    }
}
