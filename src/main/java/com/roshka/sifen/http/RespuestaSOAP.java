package com.roshka.sifen.http;

import javax.xml.soap.SOAPMessage;
import java.net.HttpURLConnection;

public class RespuestaSOAP {

    private int status;
    private String contentType;
    private SOAPMessage respuestaSOAP;
    private SOAPMessage errorSOAP;
    private byte[] datosErrorCrudo;


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

    public SOAPMessage getRespuestaSOAP() {
        return respuestaSOAP;
    }

    public void setRespuestaSOAP(SOAPMessage respuestaSOAP) {
        this.respuestaSOAP = respuestaSOAP;
    }

    public SOAPMessage getErrorSOAP() {
        return errorSOAP;
    }

    public void setErrorSOAP(SOAPMessage errorSOAP) {
        this.errorSOAP = errorSOAP;
    }

    public boolean llamadaCorrecta()
    {
        return
                status == HttpURLConnection.HTTP_ACCEPTED ||
                status == HttpURLConnection.HTTP_OK;
    }

    public byte[] getDatosErrorCrudo() {
        return datosErrorCrudo;
    }

    public void setDatosErrorCrudo(byte[] datosErrorCrudo) {
        this.datosErrorCrudo = datosErrorCrudo;
    }
}
