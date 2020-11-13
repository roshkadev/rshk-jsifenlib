package com.roshka.sifen.exceptions;

public class SifenExceptionUtil {
    public static SifenException configuracionInvalida(String message) {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_CONFIGURACION_INVALIDA,
                message
        );
    }

    public static SifenException contextoSSLInvalido(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_ERROR_INESPERADO,
                message,
                t
        );
    }

    public static SifenException llamadaSOAPInvalida(String message) {
        return SifenExceptionUtil.llamadaSOAPInvalida(message, null);
    }

    public static SifenException llamadaSOAPInvalida(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_LLAMADA_SOAP_INVALIDA,
                message,
                t
        );
    }

    public static SifenException respuestaSOAPInvalida(String message) {
        return respuestaSOAPInvalida(message, null);
    }

    public static SifenException respuestaSOAPInvalida(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_RESPUESTA_SOAP_INVALIDA,
                message,
                t
        );
    }

    public static SifenException errorInesperado(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_ERROR_INESPERADO,
                message,
                t
        );
    }

    public static SifenException errorPreparacionPeticion(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_ERROR_PETICION,
                message,
                t
        );
    }

    public static SifenException errorFirmaPeticion(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_ERROR_FIRMA_PETICION,
                message,
                t
        );
    }
}
