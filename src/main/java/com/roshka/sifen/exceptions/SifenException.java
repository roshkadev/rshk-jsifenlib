package com.roshka.sifen.exceptions;

public class SifenException extends Exception {
    private String code;

    public SifenException(String code, String message) {
        this(code, message, null);
    }

    public SifenException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
