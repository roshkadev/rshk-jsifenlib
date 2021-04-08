package com.roshka.sifen.core.exceptions;

/**
 * Excepción para los errores ocurridos en las diferentes partes de las consultas a Sifen.
 */
public class SifenException extends Exception {
    private final String code;

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
}