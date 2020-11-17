package com.roshka.sifen.http;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.util.SifenExceptionUtil;
import com.roshka.sifen.soap.MessageHelper;
import com.roshka.sifen.soap.MimeHeadersHelper;
import com.roshka.sifen.ssl.SSLContextHelper;
import com.roshka.sifen.util.IOUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class SOAPHelper {
    private final static Logger logger = Logger.getLogger(SOAPHelper.class.toString());

    private static void setupHttpURLConnectionProperties(HttpURLConnection httpURLConnection, SifenConfig sifenConfig) {
        httpURLConnection.setConnectTimeout(sifenConfig.getHttpConnectTimeout());
        httpURLConnection.setReadTimeout(sifenConfig.getHttpReadTimeout());
    }

    private static void setupHttpURLConnectionHeaders(MimeHeaders mimeHeaders, SifenConfig sifenConfig) {
        mimeHeaders.addHeader("User-Agent", sifenConfig.getUserAgent());
    }

    public static SOAPResponse makeSOAPRequest(SifenConfig sifenConfig, String urlString, SOAPMessage soapMessage) throws SifenException {
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
            MimeHeaders mimeHeaders = soapMessage.getMimeHeaders();
            setupHttpURLConnectionProperties(httpURLConnection, sifenConfig);
            setupHttpURLConnectionHeaders(mimeHeaders, sifenConfig);

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            // Conexion
            logger.info("Conectando a: " + url.toString());
            httpURLConnection.connect();

            // Se envía el mensaje SOAP
            logger.info("Enviando mensaje SOAP");
            soapMessage.writeTo(httpURLConnection.getOutputStream());

            // Respuesta
            soapResponse.setStatus(httpURLConnection.getResponseCode());
            soapResponse.setContentType(httpURLConnection.getContentType());

            /*BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
            StringBuilder res = new StringBuilder();
            String l;
            while ((l = in.readLine()) != null) {
                res.append(l);
            }*/

            if (!soapResponse.isRequestSuccessful()) {
                // Hubo un error en la petición
                logger.severe("El servidor devolvió un estado HTTP de fallo: " + soapResponse.getStatus());

                try {
                    SOAPMessage errorSOAPMessage = MessageHelper.parseMessage(
                            MimeHeadersHelper.getFromHttpURLConnection(httpURLConnection),
                            httpURLConnection.getErrorStream()
                    );

                    soapResponse.setSoapError(errorSOAPMessage);
                } catch (SOAPException se) {
                    logger.severe("SOAPException -> No se puede convertir el error obtenido en un mensaje SOAP: " + se.getLocalizedMessage());
                    soapResponse.setRawErrorData(IOUtil.getByteArrayFromInputStream(httpURLConnection.getErrorStream()));
                } catch (IOException ioe) {
                    logger.severe("IOException -> No se puede convertir el error obtenido en un mensaje SOAP: " + ioe.getLocalizedMessage());
                    soapResponse.setRawErrorData(IOUtil.getByteArrayFromInputStream(httpURLConnection.getErrorStream()));
                }

                return soapResponse;
            }

            // La respuesta fue correcta
            SOAPMessage respuestaSOAPMessage = MessageHelper.parseMessage(
                    MimeHeadersHelper.getFromHttpURLConnection(httpURLConnection),
                    httpURLConnection.getInputStream()
            );
            soapResponse.setSoapResponse(respuestaSOAPMessage);

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
