package com.roshka.sifen.test.soap;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.EventosDE;
import com.roshka.sifen.core.beans.ValidezFirmaDigital;
import com.roshka.sifen.core.beans.response.*;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.*;
import com.roshka.sifen.core.fields.request.event.TgGroupTiEvt;
import com.roshka.sifen.core.fields.request.event.TrGeVeDisconf;
import com.roshka.sifen.core.fields.request.event.TrGesEve;
import com.roshka.sifen.core.types.*;
import com.roshka.sifen.internal.helpers.SoapHelper;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class SOAPTests {
    private final static Logger logger = Logger.getLogger(SOAPTests.class.toString());

    @Test
    public void testBasicMessage() throws SOAPException, IOException {
        SOAPMessage soapMessage = SoapHelper.createSoapMessage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        soapMessage.writeTo(baos);
        String s = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        logger.info(s);
        assert s.contains(":Envelope");
    }


}
