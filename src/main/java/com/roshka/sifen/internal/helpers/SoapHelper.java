package com.roshka.sifen.internal.helpers;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.SOAPResponse;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.util.SifenUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Helper encargado de manejar las peticiones SOAP.
 */
public class SoapHelper {
    private final static Logger logger = Logger.getLogger(SoapHelper.class.toString());

    private static void setupHttpURLConnectionProperties(HttpURLConnection httpURLConnection, SifenConfig sifenConfig) {
        httpURLConnection.setConnectTimeout(sifenConfig.getHttpConnectTimeout());
        httpURLConnection.setReadTimeout(sifenConfig.getHttpReadTimeout());
    }

    private static void setupHttpURLConnectionHeaders(MimeHeaders mimeHeaders, SifenConfig sifenConfig) {
        mimeHeaders.addHeader("User-Agent", sifenConfig.getUserAgent());
    }

    public static SOAPMessage createSoapMessage() throws SOAPException {
        MessageFactory mf12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        return mf12.createMessage();
    }

    public static SOAPMessage parseSoapMessage(MimeHeaders mimeHeaders, InputStream is)
            throws SOAPException, IOException {
        MessageFactory mf12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        return mf12.createMessage(mimeHeaders, is);
    }

    private static MimeHeaders getMimeHeadersFromHttpConnection(HttpURLConnection httpURLConnection) {
        MimeHeaders mimeHeaders = new MimeHeaders();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields != null) {
            for (String key : headerFields.keySet()) {
                if (key == null) continue;
                for (String v : headerFields.get(key)) {
                    mimeHeaders.addHeader(key, v);
                }
            }
        }
        return mimeHeaders;
    }

    public static SOAPResponse makeSoapRequest(SifenConfig sifenConfig, String urlString, SOAPMessage soapMessage) throws SifenException {
        SOAPResponse soapResponse = new SOAPResponse();
        URL url;
        SSLContext sslContext;
        try {
            url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            if (url.getProtocol().equalsIgnoreCase("https")) {
                sslContext = SSLContextHelper.getContextFromConfig(sifenConfig);
                if (urlConnection instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) urlConnection).setSSLSocketFactory(sslContext.getSocketFactory());
                } else {
                    logger.warning("Se esperaba una instancia de HttpsURLConnection. Se obtuvo una de " + urlConnection.getClass().getCanonicalName());
                }
            } else if (!url.getProtocol().equalsIgnoreCase("http")) {
                throw SifenExceptionUtil.invalidSOAPRequest("El protocolo " + url.getProtocol() + " es inválido");
            }

            if (!(urlConnection instanceof HttpURLConnection)) {
                throw SifenExceptionUtil.invalidSOAPRequest("Se esperaba una instancia de HttpURLConnection o derivados. Se obtuvo: " + urlConnection.getClass().getCanonicalName());
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("POST"); // Siempre POST
            setupHttpURLConnectionProperties(httpURLConnection, sifenConfig);
            setupHttpURLConnectionHeaders(soapMessage.getMimeHeaders(), sifenConfig);

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            // Conexion
            logger.info("Conectando a: " + url);
            httpURLConnection.connect();

            // Se envía el mensaje SOAP
            logger.info("Enviando mensaje SOAP");
            soapMessage.writeTo(httpURLConnection.getOutputStream());

            // Respuesta
            soapResponse.setStatus(httpURLConnection.getResponseCode());

            MimeHeaders mimeHeaders = getMimeHeadersFromHttpConnection(httpURLConnection);
            if (!soapResponse.isRequestSuccessful()) {
                // Hubo un error en la petición
                logger.warning("El servidor devolvió un estado HTTP de fallo: " + soapResponse.getStatus());

                try {
                    // Actualizamos el content type para poder parsear la respuesta
                    mimeHeaders.setHeader("Content-Type", "application/soap+xml");

                    byte[] readData = SifenUtil.getByteArrayFromInputStream(httpURLConnection.getErrorStream());
                    SOAPMessage errorSoapMessage = SoapHelper.parseSoapMessage(mimeHeaders, new ByteArrayInputStream(readData));
                    soapResponse.setSoapResponse(errorSoapMessage);
                    soapResponse.setRawData(readData);
                } catch (SOAPException | IOException e) {
                    logger.severe("No se puede convertir el error obtenido en un mensaje SOAP: " + e.getLocalizedMessage());
                }
            } else {
                // La respuesta fue correcta
                byte[] readData = SifenUtil.getByteArrayFromInputStream(httpURLConnection.getInputStream());
                SOAPMessage successSoapMessage = SoapHelper.parseSoapMessage(mimeHeaders, new ByteArrayInputStream(readData));
                soapResponse.setSoapResponse(successSoapMessage);
                soapResponse.setRawData(readData);
            }

            return soapResponse;
        } catch (MalformedURLException e) {
            throw SifenExceptionUtil.invalidSOAPRequest("El URL " + urlString + " es inválido: " + e.getLocalizedMessage(), e);
        } catch (IOException e) {
            throw SifenExceptionUtil.invalidSOAPRequest("Excepción de entrada/salida al realizar llamada SOAP: " + e.getLocalizedMessage(), e);
        } catch (SOAPException e) {
            throw SifenExceptionUtil.invalidSOAPRequest("Excepción de mensajería SOAP: " + e.getLocalizedMessage(), e);
        }
    }
}