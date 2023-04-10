package com.roshka.sifen.internal;

import jakarta.xml.soap.SOAPMessage;
import java.net.HttpURLConnection;

/**
 * Clase interna que almacena la respuesta SOAP proveída por Sifen.
 */
public class SOAPResponse {
    private int status;
    private SOAPMessage soapResponse;
    private byte[] rawData;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SOAPMessage getSoapResponse() {
        return soapResponse;
    }

    public void setSoapResponse(SOAPMessage soapResponse) {
        this.soapResponse = soapResponse;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public boolean isRequestSuccessful() {
        return status == HttpURLConnection.HTTP_ACCEPTED || status == HttpURLConnection.HTTP_OK;
    }
}