package com.roshka.sifen.http;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.context.SifenCtx;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionUtil;
import com.roshka.sifen.ssl.SSLContextHelper;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class SOAPHelper {

    private final static Logger logger = Logger.getLogger(SOAPHelper.class.toString());

    private static void setupHttpURLConnectionProperties(HttpURLConnection httpURLConnection, SifenCtx sifenCtx)
    {
        SifenConfig sifenConfig = sifenCtx.getSifenConfig();
        httpURLConnection.setConnectTimeout(sifenConfig.getHttpConnectTimeout());
        httpURLConnection.setReadTimeout(sifenConfig.getHttpReadTimeout());
    }

    private static void setupHttpURLConnectionHeaders(MimeHeaders mimeHeaders, SifenCtx sifenCtx)
    {
        SifenConfig sifenConfig = sifenCtx.getSifenConfig();
        mimeHeaders.addHeader("User-Agent", sifenConfig.getUserAgent());
    }

    //
    public static SOAPMessage performSOAPRequest(SifenCtx sifenCtx, SOAPMessage soapMessage, String urlString)
        throws SifenException
    {

        SifenConfig sifenConfig = sifenCtx.getSifenConfig();
        URL url;
        SSLContext sslContext = null;
        try {
            url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            if (url.getProtocol().equalsIgnoreCase("https")) {
                sslContext = SSLContextHelper.getContextFromConfig(sifenConfig);
                if (urlConnection instanceof  HttpsURLConnection) {
                    ((HttpsURLConnection) urlConnection).setSSLSocketFactory(sslContext.getSocketFactory());
                } else {
                    logger.warning("Se esperaba una instancia de HttpsURLConnection. Se obtuvo una de " + urlConnection.getClass().getCanonicalName());
                }
            } else if (url.getProtocol().equalsIgnoreCase("http")) {
                // no hacer nada
            } else {
                // protocolo inválido
                throw SifenExceptionUtil.llamadaSOAPInvalida("El protocolo " + url.getProtocol() + " es inválido");
            }
            if (!(urlConnection instanceof HttpURLConnection)) {
                throw SifenExceptionUtil.llamadaSOAPInvalida("Se esperaba una instancia de HttpURLConnection o derivados. Se obtuvo: " + urlConnection.getClass().getCanonicalName());
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // siempre POST
            httpURLConnection.setRequestMethod("POST");
            MimeHeaders mimeHeaders = soapMessage.getMimeHeaders();
            setupHttpURLConnectionProperties(httpURLConnection, sifenCtx);
            setupHttpURLConnectionHeaders(mimeHeaders, sifenCtx);

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            // connectar!
            logger.info("Conectanto a: " + url.toString());
            httpURLConnection.connect();

            // enviar mensaje SOAP
            logger.info("Enviando mensaje SOAP");
            soapMessage.writeTo(httpURLConnection.getOutputStream());

            DataInputStream in = new DataInputStream(httpURLConnection.getInputStream());
            byte[] buff = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int count;
            while ((count = in.read(buff)) != -1) {
                baos.write(buff, 0, count);
            }

            String out = new String(baos.toByteArray());

            logger.info("Respuesta: " + out);

            return null;

        } catch (MalformedURLException e) {
            throw SifenExceptionUtil.llamadaSOAPInvalida("El URL " + urlString + " es inválido: " + e.getLocalizedMessage(), e);
        } catch (IOException e) {
            throw SifenExceptionUtil.llamadaSOAPInvalida("Excepción de entrada/salida al realizar llamada SOAP: " + e.getLocalizedMessage(), e);
        } catch (SOAPException e) {
            throw SifenExceptionUtil.llamadaSOAPInvalida("Excepción de mensajería SOAP: " + e.getLocalizedMessage(), e);
        }

    }

}
