package com.roshka.sifen.soap;

import javax.xml.soap.*;
import java.io.IOException;
import java.io.InputStream;

public class MessageHelper {
    public static SOAPMessage createMessage() throws SOAPException {
        MessageFactory mf12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        return mf12.createMessage();
    }

    public static SOAPMessage parseMessage(MimeHeaders mimeHeaders, InputStream is)
            throws SOAPException, IOException {
        MessageFactory mf12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        return mf12.createMessage(mimeHeaders, is);
    }
}
