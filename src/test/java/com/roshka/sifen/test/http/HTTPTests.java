package com.roshka.sifen.test.http;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.http.HTTPHelper;
import org.junit.Test;

public class HTTPTests {

    @Test
    public void testWSDLConnection() throws SifenException {
        String url = "https://sifen-test.set.gov.py/de/ws/consultas/consulta-ruc.wsdl?wsdl";
        HTTPHelper.request(null, url);
    }
}
