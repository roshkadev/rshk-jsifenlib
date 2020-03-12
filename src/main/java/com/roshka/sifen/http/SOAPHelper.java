package com.roshka.sifen.http;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.context.SifenCtx;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionUtil;
import com.roshka.sifen.soap.MessageHelper;
import com.roshka.sifen.soap.MimeHeadersHelper;
import com.roshka.sifen.ssl.SSLContextHelper;
import com.roshka.sifen.util.IOUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.PrintWriter;
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

    private static final int[] _httpSuccessStatuses = new int[] {
            HttpURLConnection.HTTP_ACCEPTED,
            HttpURLConnection.HTTP_OK
    };

    //
    public static RespuestaSifen performSOAPRequest(SifenCtx sifenCtx, SOAPMessage soapMessage, String urlString)
        throws SifenException
    {

        SifenConfig sifenConfig = sifenCtx.getSifenConfig();

        RespuestaSifen respuestaSifen = new RespuestaSifen();

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

            // respuesta
            respuestaSifen.setStatus(httpURLConnection.getResponseCode());
            respuestaSifen.setContentType(httpURLConnection.getContentType());

            if (!respuestaSifen.llamadaCorrecta()) {
                // hubo un error en la petición
                logger.severe("El servidor devolvió un estado HTTP de fallo: " + respuestaSifen.getStatus());

                try {

                    SOAPMessage errorSOAPMessage = MessageHelper.parseMessage(
                            MimeHeadersHelper.getFromHttpURLConnection(httpURLConnection),
                            httpURLConnection.getErrorStream()
                    );

                    respuestaSifen.procesarDatosError(errorSOAPMessage);


                } catch (SOAPException se) {
                    logger.severe("SOAPException -> No se puede convertir el error obtenido en un mensaje SOAP: " + se.getLocalizedMessage());
                    respuestaSifen.procesarDatosError(IOUtil.getByteArrayFromInputStream(httpURLConnection.getErrorStream()));
                } catch (IOException ioe) {
                    logger.severe("IOException -> No se puede convertir el error obtenido en un mensaje SOAP: " + ioe.getLocalizedMessage());
                    respuestaSifen.procesarDatosError(IOUtil.getByteArrayFromInputStream(httpURLConnection.getErrorStream()));
                }

                return respuestaSifen;

            }

            // la respuesta fue correcta

            SOAPMessage respuestaSOAPMessage = MessageHelper.parseMessage(
                    MimeHeadersHelper.getFromHttpURLConnection(httpURLConnection),
                    httpURLConnection.getInputStream()
            );

            respuestaSifen.procesarDatos(respuestaSOAPMessage);

            return respuestaSifen;

        } catch (MalformedURLException e) {
            throw SifenExceptionUtil.llamadaSOAPInvalida("El URL " + urlString + " es inválido: " + e.getLocalizedMessage(), e);
        } catch (IOException e) {
            throw SifenExceptionUtil.llamadaSOAPInvalida("Excepción de entrada/salida al realizar llamada SOAP: " + e.getLocalizedMessage(), e);
        } catch (SOAPException e) {
            throw SifenExceptionUtil.llamadaSOAPInvalida("Excepción de mensajería SOAP: " + e.getLocalizedMessage(), e);
        }

    }

}
