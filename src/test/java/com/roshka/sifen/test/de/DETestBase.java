package com.roshka.sifen.test.de;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.test.soap.SOAPTests;
import org.junit.BeforeClass;

import java.io.InputStream;
import java.util.logging.Logger;

public class DETestBase {

    protected final static Logger logger = Logger.getLogger(DETestBase.class.toString());

    @BeforeClass
    public static void setupSifenConfig() throws SifenException {
        InputStream testProperties = SOAPTests.class.getClassLoader().getResourceAsStream("test.properties");
        SifenConfig sifenConfig = SifenConfig.cargarConfiguracion(testProperties);
        logger.info("Using CONFIG: " + sifenConfig);
        Sifen.setSifenConfig(sifenConfig);
    }


}
