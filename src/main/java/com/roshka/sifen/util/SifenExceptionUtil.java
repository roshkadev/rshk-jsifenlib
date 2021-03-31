package com.roshka.sifen.util;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionCodes;

public class SifenExceptionUtil {

    public static SifenException invalidConfiguration(String message) {
        return new SifenException(
                SifenExceptionCodes.INVALID_CONFIGURATION_CODE,
                message
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
}