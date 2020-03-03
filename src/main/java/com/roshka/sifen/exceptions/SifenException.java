package com.roshka.sifen.exceptions;

public class SifenException extends Exception {

    private String code;

    public SifenException(int code) {

    }

    public SifenException(int code, Throwable cause) {

    }


    public SifenException(String message, String code) {
        this(message, code, null);
    }

    public SifenException(String message, String code, Throwable cause) {
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
