package com.roshka.sifen.exceptions;

public class SifenExceptionUtil {

    public static SifenException configuracionInvalida(String message)
    {
        return new SifenException(
                SifenExceptionCodes.ERRMESS_CONFIGURACION_INVALIDA,
                message
        );
    }
}
