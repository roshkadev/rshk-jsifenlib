package com.roshka.sifen.core.exceptions;

import java.util.HashMap;
import java.util.Map;

public class SifenExceptionCodes {
    public static final String UNSET_ERROR_MESSAGE = "No hay mensaje de error";

    public static final String UNEXPECTED_ERROR_CODE = "i100";
    public static final String UNEXPECTED_ERROR_MESSAGE = "Error Inesperado";
    public static final String INVALID_CONFIGURATION_CODE = "i101";
    public static final String INVALID_CONFIGURATION_MESSAGE = "Configuración inválida";
    public static final String INVALID_SSL_CONTEXT_CODE = "i200";
    public static final String INVALID_SSL_CONTEXT_MESSAGE = "Contexto SSL Inválido";
    public static final String INVALID_SOAP_REQUEST_CODE = "i300";
    public static final String INVALID_SOAP_REQUEST_MESSAGE = "Llamada SOAP inválida";
    public static final String INVALID_SOAP_RESPONSE_CODE = "i301";
    public static final String INVALID_SOAP_RESPONSE_MESSAGE = "Respuesta SOAP inválida";
    public static final String REQUEST_PREPARATION_ERROR_CODE = "i400";
    public static final String REQUEST_PREPARATION_ERROR_MESSAGE = "Error al preparar el cuerpo de la petición";
    public static final String REQUEST_SIGNING_ERROR_CODE = "i401";
    public static final String REQUEST_SIGNING_ERROR_MESSAGE = "Error al firmar la petición";
    public static final String FIELD_NOT_FOUND_CODE = "i402";
    public static final String FIELD_NOT_FOUND_MESSAGE = "Campo no encontrado";
    public static final String XML_PARSING_ERROR_CODE = "i403";
    public static final String XML_PARSING_ERROR_MESSAGE = "Error al parsear el XML";
    public static final String INVALID_SIGNATURE_ERROR_CODE = "i404";
    public static final String INVALID_SIGNATURE_ERROR_MESSAGE = "La firma del Documento Electrónico es inválida";

    private static final Map<String, String> errorMessages;

    static {
        errorMessages = new HashMap<>();
        errorMessages.put(UNEXPECTED_ERROR_CODE, UNEXPECTED_ERROR_MESSAGE);
        errorMessages.put(INVALID_CONFIGURATION_CODE, INVALID_CONFIGURATION_MESSAGE);
        errorMessages.put(INVALID_SSL_CONTEXT_CODE, INVALID_SSL_CONTEXT_MESSAGE);
        errorMessages.put(INVALID_SOAP_REQUEST_CODE, INVALID_SOAP_REQUEST_MESSAGE);
        errorMessages.put(INVALID_SOAP_RESPONSE_CODE, INVALID_SOAP_RESPONSE_MESSAGE);
        errorMessages.put(REQUEST_PREPARATION_ERROR_CODE, REQUEST_PREPARATION_ERROR_MESSAGE);
        errorMessages.put(REQUEST_SIGNING_ERROR_CODE, REQUEST_SIGNING_ERROR_MESSAGE);
        errorMessages.put(FIELD_NOT_FOUND_CODE, FIELD_NOT_FOUND_MESSAGE);
        errorMessages.put(XML_PARSING_ERROR_CODE, XML_PARSING_ERROR_MESSAGE);
        errorMessages.put(INVALID_SIGNATURE_ERROR_CODE, INVALID_SIGNATURE_ERROR_MESSAGE);
    }

    public static String getErrorMessage(String code) {
        if (errorMessages.containsKey(code)) {
            return errorMessages.get(code);
        }
        return UNSET_ERROR_MESSAGE;
    }
}