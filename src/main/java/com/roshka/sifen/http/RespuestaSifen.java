package com.roshka.sifen.http;

import javax.xml.soap.SOAPMessage;
import java.net.HttpURLConnection;

public class RespuestaSifen {

    private int status;
    private String contentType;
    private byte[] datosError;

    private SOAPMessage errorSOAPMessage;
    private SOAPMessage respuestaSOAPMessage;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean llamadaCorrecta() {
        return
                status == HttpURLConnection.HTTP_ACCEPTED ||
                status == HttpURLConnection.HTTP_OK;
    }

    public void procesarDatos(SOAPMessage soapMessage) {
        this.respuestaSOAPMessage = soapMessage;
    }

    public void procesarDatosError(SOAPMessage soapMessage) {
        this.errorSOAPMessage = soapMessage;
    }

    public void procesarDatosError(byte[] datosError)
    {
        this.datosError = datosError;
        if (contentType != null && contentType.startsWith("application/soap")) {
            //

        }
    }

    public byte[] getDatosError() {
        return datosError;
    }

    public void setDatosError(byte[] datosError) {
        this.datosError = datosError;
    }
}
