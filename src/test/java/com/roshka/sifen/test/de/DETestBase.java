package com.roshka.sifen.test.de;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.TgEmis;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.test.soap.SOAPTests;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

public class DETestBase {

    protected final static Logger logger = Logger.getLogger(DETestBase.class.toString());

    protected static final DocumentBuilderFactory _documentBuilderFactory = DocumentBuilderFactory.newInstance();
    protected static DocumentBuilder _documentBuilder;

    static {
        _documentBuilderFactory.setNamespaceAware(true);
        try {
            _documentBuilder = _documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Error al crear el DocumentBuilder");
        }
    }


    @BeforeClass
    public static void setupSifenConfig() throws SifenException {
        InputStream testProperties = SOAPTests.class.getClassLoader().getResourceAsStream("test.properties");
        SifenConfig sifenConfig = SifenConfig.cargarConfiguracion(testProperties);
        logger.info("Using CONFIG: " + sifenConfig);
        Sifen.setSifenConfig(sifenConfig);
    }

    protected TgEmis cargarEmisor(String ruc) throws IOException, SAXException, SifenException {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("tests/emisores/" + ruc + ".xml");
        Document document = _documentBuilder.parse(resource);
        NodeList gEmis = document.getElementsByTagName("gEmis");
        Assert.assertEquals(1, gEmis.getLength());
        Node node = gEmis.item(0);
        Assert.assertNotNull(node);
        TgEmis tgEmis = SifenObjectFactory.getFromNode(node, TgEmis.class);
        return tgEmis;
    }

    @Test
    @Ignore
    public void testEmisorRoshka() throws Exception {
        TgEmis tgEmis = cargarEmisor("80089752-8");
        Assert.assertNotNull(tgEmis);
    }



}
