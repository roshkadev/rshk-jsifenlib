package com.roshka.sifen.http;

import javax.xml.soap.SOAPMessage;
import java.net.HttpURLConnection;

public class SOAPResponse {
    private int status;
    private String contentType;
    private SOAPMessage soapResponse;
    private SOAPMessage soapError;
    private byte[] rawErrorData;

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

    public SOAPMessage getSoapResponse() {
        return soapResponse;
    }

    public void setSoapResponse(SOAPMessage soapResponse) {
        this.soapResponse = soapResponse;
    }

    public SOAPMessage getSoapError() {
        return soapError;
    }

    public void setSoapError(SOAPMessage soapError) {
        this.soapError = soapError;
    }

    public byte[] getRawErrorData() {
        return rawErrorData;
    }

    public void setRawErrorData(byte[] rawErrorData) {
        this.rawErrorData = rawErrorData;
    }

    public boolean isRequestSuccessful() {
        return status == HttpURLConnection.HTTP_ACCEPTED || status == HttpURLConnection.HTTP_OK;
    }
}
