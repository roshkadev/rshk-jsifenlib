package com.roshka.sifen.server.exceptions;

public class SifenServerException extends Exception {

    private String code;

    private int httpCode;

    public SifenServerException(String message, String code, int httpCode, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.httpCode = httpCode;
    }

    public SifenServerException(String message, String code, int httpCode) {
        this(message, code, httpCode, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
