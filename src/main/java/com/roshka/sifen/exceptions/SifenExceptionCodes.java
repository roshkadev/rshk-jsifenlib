package com.roshka.sifen.exceptions;

import java.util.HashMap;
import java.util.Map;

public class SifenExceptionCodes {
    public static final String ERRMESS_NO_ESTABLECIDO = "No hay mensaje de error";

    public static final String ERRCODE_ERROR_INESPERADO = "i100";
    public static final String ERRMESS_ERROR_INESPERADO = "Error Inesperado";
    public static final String ERRCODE_CONFIGURACION_INVALIDA = "i101";
    public static final String ERRMESS_CONFIGURACION_INVALIDA = "Configuración inválida";
    public static final String ERRCODE_CONTEXTO_SSL_INVALIDO = "i200";
    public static final String ERRMESS_CONTEXTO_SSL_INVALIDO = "Contexto SSL Inválido";
    public static final String ERRCODE_LLAMADA_SOAP_INVALIDA = "i300";
    public static final String ERRMESS_LLAMADA_SOAP_INVALIDA = "Llamada SOAP inválida";
    public static final String ERRCODE_RESPUESTA_SOAP_INVALIDA = "i301";
    public static final String ERRMESS_RESPUESTA_SOAP_INVALIDA = "Respuesta SOAP inválida";
    public static final String ERRCODE_ERROR_PETICION = "i400";
    public static final String ERRMESS_ERROR_PETICION = "Error al preparar el cuerpo de la petición";
    public static final String ERRCODE_ERROR_FIRMA_PETICION = "i401";
    public static final String ERRMESS_ERROR_FIRMA_PETICION = "Error al firmar la petición";

    private static final Map<String, String> errorMessages;

    static {
        errorMessages = new HashMap<>();
        errorMessages.put(ERRCODE_ERROR_INESPERADO, ERRMESS_ERROR_INESPERADO);
        errorMessages.put(ERRCODE_CONFIGURACION_INVALIDA, ERRMESS_CONFIGURACION_INVALIDA);
        errorMessages.put(ERRCODE_CONTEXTO_SSL_INVALIDO, ERRMESS_CONTEXTO_SSL_INVALIDO);
        errorMessages.put(ERRCODE_LLAMADA_SOAP_INVALIDA, ERRMESS_LLAMADA_SOAP_INVALIDA);
        errorMessages.put(ERRCODE_ERROR_PETICION, ERRMESS_ERROR_PETICION);
        errorMessages.put(ERRCODE_ERROR_FIRMA_PETICION, ERRCODE_ERROR_FIRMA_PETICION);
    }

    public static String getErrorMessage(String code) {
        if (errorMessages.containsKey(code)) {
            return errorMessages.get(code);
        }
        return ERRMESS_NO_ESTABLECIDO;
    }
}
