package com.roshka.sifen.test.soap;

import com.roshka.sifen.model.envi.REnviConsRUC;
import com.roshka.sifen.sdk.v150.ConsultaRUC;
import com.roshka.sifen.soap.MessageHelper;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;

public class SOAPTests {

    @Test
    public void testBasicMessage()
            throws SOAPException, IOException {
        SOAPMessage soapMessage = MessageHelper.createMessage();
        soapMessage.writeTo(System.out);
    }

    @Test
    public void testConsultaRUC()
            throws SOAPException, IOException
    {
        SOAPMessage soapMessage = MessageHelper.createMessage();
        REnviConsRUC rEnviConsRUC = new REnviConsRUC();
        rEnviConsRUC.setdId(1);
        rEnviConsRUC.setdRUCCons("980527");
        rEnviConsRUC.setupSOAPBody(soapMessage.getSOAPBody());
        soapMessage.writeTo(System.out);
    }
}
