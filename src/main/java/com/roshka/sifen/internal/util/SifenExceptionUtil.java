package com.roshka.sifen.internal.util;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.exceptions.SifenExceptionCodes;

/**
 * Util encargado de crear las excepciones.
 */
public class SifenExceptionUtil {
    public static SifenException invalidConfiguration(String message) {
        return SifenExceptionUtil.invalidConfiguration(message, null);
    }

    public static SifenException invalidConfiguration(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.INVALID_CONFIGURATION_CODE,
                message,
                t
        );
    }

    public static SifenException invalidSSLContext(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.UNEXPECTED_ERROR_CODE,
                message,
                t
        );
    }

    public static SifenException invalidSOAPRequest(String message) {
        return SifenExceptionUtil.invalidSOAPRequest(message, null);
    }

    public static SifenException invalidSOAPRequest(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.INVALID_SOAP_REQUEST_CODE,
                message,
                t
        );
    }

    public static SifenException invalidSOAPResponse(String message) {
        return invalidSOAPResponse(message, null);
    }

    public static SifenException invalidSOAPResponse(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.INVALID_SOAP_RESPONSE_CODE,
                message,
                t
        );
    }

    public static SifenException unexpectedError(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.UNEXPECTED_ERROR_CODE,
                message,
                t
        );
    }

    public static SifenException requestPreparationError(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.REQUEST_PREPARATION_ERROR_CODE,
                message,
                t
        );
    }

    public static SifenException requestSigningError(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.REQUEST_SIGNING_ERROR_CODE,
                message,
                t
        );
    }

    public static SifenException fieldNotFound(String message) {
        return fieldNotFound(message, null);
    }

    public static SifenException fieldNotFound(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.FIELD_NOT_FOUND_CODE,
                message,
                t
        );
    }

    public static SifenException xmlParsingError(String message) {
        return xmlParsingError(message, null);
    }

    public static SifenException xmlParsingError(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.XML_PARSING_ERROR_CODE,
                message,
                t
        );
    }

    public static SifenException invalidSignatureError(String message) {
        return xmlParsingError(message, null);
    }

    public static SifenException invalidSignatureError(String message, Throwable t) {
        return new SifenException(
                SifenExceptionCodes.INVALID_SIGNATURE_ERROR_CODE,
                message,
                t
        );
    }
}