package com.roshka.sifen.exceptions;

public class SifenExceptionUtil {

    public static SifenException configuracionInvalida(String message)
    {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_CONFIGURACION_INVALIDA,
                message
        );
    }

    public static SifenException contextoSSLInvalido(String message, Throwable t)
    {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_ERROR_INESPERADO,
                message,
                t
        );
    }

    public static SifenException llamadaSOAPInvalida(String message) {
        return SifenExceptionUtil.llamadaSOAPInvalida(message, null);
    }
    public static SifenException llamadaSOAPInvalida(String message, Throwable t)
    {
        return new SifenException(
                SifenExceptionCodes.ERRCODE_ERROR_INESPERADO,
                message,
                t
        );
    }


}
