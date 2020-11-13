package com.roshka.sifen.sdk.v150.request;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionUtil;
import com.roshka.sifen.http.SOAPHelper;
import com.roshka.sifen.http.SOAPResponse;
import com.roshka.sifen.model.envi.REnviBase;
import com.roshka.sifen.soap.MessageHelper;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.logging.Logger;

public abstract class BaseRequest {
    private final static Logger logger = Logger.getLogger(BaseRequest.class.toString());

    private static String url;
    private static SifenConfig sifenConfig;

    public static <T extends REnviBase> SOAPResponse makeRequest(T request) throws SifenException {
        try {
            // Preparamos el mensaje
            SOAPMessage message = MessageHelper.createMessage();
            request.setupSOAPBody(message.getSOAPBody(), sifenConfig);

            // Realizamos la consulta
            String requestUrl = (sifenConfig.getUrlBase() != null ? sifenConfig.getUrlBase() : sifenConfig.getUrlBaseLocal()) + url;
            return SOAPHelper.makeSOAPRequest(sifenConfig, requestUrl, message);
        } catch (SOAPException e) {
            String msg = "Ocurrió un error al realizan la petición a: " + url + ". Mensaje: " + e.getLocalizedMessage();
            throw SifenExceptionUtil.llamadaSOAPInvalida(msg, e);
        }
    }

    protected static void setConfig(SifenConfig newSifenConfig, String newUrl) {
        url = newUrl;
        sifenConfig = newSifenConfig;
    }
}
