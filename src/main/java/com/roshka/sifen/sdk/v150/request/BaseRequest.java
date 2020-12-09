package com.roshka.sifen.sdk.v150.request;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.http.SOAPHelper;
import com.roshka.sifen.http.SOAPResponse;
import com.roshka.sifen.model.envi.REnviBase;
import com.roshka.sifen.model.envi.RSignedEnviBase;
import com.roshka.sifen.soap.MessageHelper;
import com.roshka.sifen.soap.SignatureHelper;
import com.roshka.sifen.util.SifenExceptionUtil;
import com.roshka.sifen.util.SifenUtil;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.logging.Logger;

public abstract class BaseRequest {
    private final static Logger logger = Logger.getLogger(BaseRequest.class.toString());

    private static String url;
    private static SifenConfig sifenConfig;

    public static <T extends REnviBase> SOAPResponse makeRequest(T request) throws SifenException {
        logger.info("Realizando petición");
        try {
            // Preparamos el mensaje
            SOAPMessage message = MessageHelper.createMessage();
            request.setupSOAPBody(message.getSOAPBody(), sifenConfig);

            message.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
            message.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");

            // Para obtener el xml
            /*final StringWriter sw = new StringWriter();

            try {
                TransformerFactory.newInstance().newTransformer().transform(
                        new DOMSource(message.getSOAPPart()),
                        new StreamResult(sw));
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }

            String xml = sw.toString();*/

            // Realizamos la consulta
            String requestUrl = SifenUtil.coalesce(sifenConfig.getUrlBase(), sifenConfig.getUrlBaseLocal()) + url;
            if (request instanceof RSignedEnviBase) {
                SignatureHelper.signDocument(sifenConfig, (RSignedEnviBase)request);
            }
            return SOAPHelper.makeSOAPRequest(sifenConfig, requestUrl, message);
        } catch (SOAPException e) {
            String msg = "Ocurrió un error al realizan la petición a: " + url + ". Mensaje: " + e.getLocalizedMessage();
            throw SifenExceptionUtil.invalidSOAPRequest(msg, e);
        }
    }

    protected static void setConfig(SifenConfig newSifenConfig, String newUrl) {
        url = newUrl;
        sifenConfig = newSifenConfig;
    }
}
