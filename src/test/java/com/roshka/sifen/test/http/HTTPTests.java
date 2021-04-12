package com.roshka.sifen.test.http;

import com.roshka.sifen.internal.helpers.HttpHelper;
import org.junit.Ignore;
import org.junit.Test;

public class HTTPTests {
    @Test
    @Ignore
    public void testWSDLConnection() {
        String url = "https://sifen-test.set.gov.py/de/ws/consultas/consulta-ruc.wsdl?wsdl";
        HttpHelper.request(null, url);
    }
}