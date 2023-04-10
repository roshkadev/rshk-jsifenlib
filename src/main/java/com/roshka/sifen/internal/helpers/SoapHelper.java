package com.roshka.sifen.internal.helpers;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.SOAPResponse;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.util.SifenUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Helper encargado de manejar las peticiones SOAP.
 */
public class SoapHelper {
    private final static Logger logger = Logger.getLogger(SoapHelper.class.toString());

    private static void setupHttpURLConnectionProperties(HttpsURLConnection httpsConnection, SifenConfig sifenConfig) {
        httpsConnection.setConnectTimeout(sifenConfig.getHttpConnectTimeout());
        httpsConnection.setReadTimeout(sifenConfig.getHttpReadTimeout());
    }

    private static void setupHttpURLConnectionHeaders(HttpsURLConnection httpsConnection, SifenConfig sifenConfig) {
        httpsConnection.setRequestProperty("User-Agent", sifenConfig.getUserAgent());
        httpsConnection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
    }

    public static SOAPMessage createSoapMessage() throws SOAPException {
        MessageFactory mf12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        return mf12.createMessage();
    }

    public static SOAPMessage parseSoapMessage(InputStream is)
            throws SOAPException, IOException {
        MessageFactory mf12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        return mf12.createMessage(null, is);
    }

    public static SOAPResponse makeSoapRequest(SifenConfig sifenConfig, String urlString, SOAPMessage soapMessage) throws SifenException {
        SOAPResponse soapResponse = new SOAPResponse();
        HttpsURLConnection httpsConnection = null;
        try {
            URL url = new URL(urlString);
            httpsConnection = (HttpsURLConnection) url.openConnection();
            if (url.getProtocol().equalsIgnoreCase("https")) {
                SSLContext sslContext = SSLContextHelper.getContextFromConfig(sifenConfig);
                httpsConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            } else if (!url.getProtocol().equalsIgnoreCase("http")) {
                throw SifenExceptionUtil.invalidSOAPRequest("El protocolo " + url.getProtocol() + " es inválido");
            }

            httpsConnection.setRequestMethod("POST");
            httpsConnection.setDoOutput(true);
            setupHttpURLConnectionProperties(httpsConnection, sifenConfig);
            setupHttpURLConnectionHeaders(httpsConnection, sifenConfig);

            // Conexión
            logger.info("Conectando a: " + url);
            httpsConnection.connect();

            // Petición
            logger.info("Enviando mensaje SOAP");
            soapMessage.writeTo(httpsConnection.getOutputStream());

            // Respuesta
            soapResponse.setStatus(httpsConnection.getResponseCode());
            InputStream inputStream;
            if (soapResponse.isRequestSuccessful()) {
                inputStream = httpsConnection.getInputStream();
            } else {
                inputStream = httpsConnection.getErrorStream();
            }

            byte[] readData = SifenUtil.getByteArrayFromInputStream(inputStream);
            SOAPMessage successSoapMessage = SoapHelper.parseSoapMessage(new ByteArrayInputStream(readData));
            soapResponse.setSoapResponse(successSoapMessage);
            soapResponse.setRawData(readData);

            return soapResponse;
        } catch (MalformedURLException e) {
            throw SifenExceptionUtil.invalidSOAPRequest("El URL " + urlString + " es inválido: " + e.getLocalizedMessage(), e);
        } catch (IOException e) {
            throw SifenExceptionUtil.invalidSOAPRequest("Excepción de entrada/salida al realizar llamada SOAP: " + e.getLocalizedMessage(), e);
        } catch (SOAPException e) {
            throw SifenExceptionUtil.invalidSOAPRequest("Excepción de mensajería SOAP: " + e.getLocalizedMessage(), e);
        } finally {
            if (httpsConnection != null)
                httpsConnection.disconnect();
        }
    }
}