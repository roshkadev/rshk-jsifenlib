package com.roshka.sifen.test.soap;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.context.SifenCtx;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.envi.REnviConsRUC;
import com.roshka.sifen.sdk.v150.ConsultaRUC;
import com.roshka.sifen.soap.MessageHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SOAPTests {

    private static SifenCtx _sifenContext;

    @BeforeClass
    public static void setupSifenCtx() throws IOException, SifenException {
        Properties properties = new Properties();
        properties.load(new FileReader("conf/roshka.properties"));
        SifenConfig sifenConfig = SifenConfig.loadFromProperties(properties);
        _sifenContext = new SifenCtx(sifenConfig);
    }

    @Test
    public void testBasicMessage()
            throws SOAPException, IOException {
        SOAPMessage soapMessage = MessageHelper.createMessage();
        soapMessage.writeTo(System.out);
    }

    @Test
    public void testConsultaRUC()
            throws SOAPException, IOException, SifenException {

        ConsultaRUC consultaRUC = new ConsultaRUC(_sifenContext);
        consultaRUC.consultaRUC("980527");
    }
}
