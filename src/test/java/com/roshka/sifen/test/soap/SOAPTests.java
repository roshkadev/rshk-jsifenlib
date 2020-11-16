package com.roshka.sifen.test.soap;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.sdk.v150.beans.DocumentoElectronico;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.soap.MessageHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.util.logging.Logger;

public class SOAPTests {
    private final static Logger logger = Logger.getLogger(SOAPTests.class.toString());

    private static long currentdId = 0;

    @BeforeClass
    public static void setupSifenConfig() {
        Sifen.setSifenConfig(new SifenConfig("C:\\Users\\mdazarza\\Documents\\taxare.pfx",
                "Pqntslc0$", SifenConfig.TipoCertificadoCliente.PFX));
    }

    @Test
    public void testBasicMessage() throws SOAPException, IOException {
        SOAPMessage soapMessage = MessageHelper.createMessage();
        soapMessage.writeTo(System.out);
    }

    @Test
    public void testConsultaRUC() throws SifenException {
        RespuestaSifen ret = Sifen.consultaRUC(++currentdId, "4579993");
        logger.info(ret.toString());
    }

    @Test
    public void testRecepcionDE() throws SifenException {
        DocumentoElectronico DE = new DocumentoElectronico();
        RespuestaSifen ret = Sifen.recepcionDE(++currentdId, DE);
        logger.info(ret.toString());
    }
}
