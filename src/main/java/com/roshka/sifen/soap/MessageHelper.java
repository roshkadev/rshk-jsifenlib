package com.roshka.sifen.soap;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class MessageHelper {

    public static SOAPMessage createMessage()
        throws SOAPException
    {
        MessageFactory mf12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SOAPMessage message = mf12.createMessage();
        EnvelopeHelper.setupEnvelope(message);
        HeaderHelper.setupHeader(message);
        return message;
    }

}
