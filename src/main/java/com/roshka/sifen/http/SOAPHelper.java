package com.roshka.sifen.http;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.context.SifenCtx;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SOAPHelper {

    private final static Logger logger = Logger.getLogger(SOAPHelper.class.toString());

    //
    public static SOAPMessage performSOAPRequest(SOAPMessage soapMessage, String url)
        throws SOAPException
    {
        logger.info("Calling url -> " + url + " with soapMessage");
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        return soapConnection.call(soapMessage, url);
    }

}
