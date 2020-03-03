package com.roshka.sifen.exceptions;

import java.util.HashMap;
import java.util.Map;

public class SifenExceptionCodes {

    private static final String UNEXPECTED_ERROR = "Error Inesperado";

    private static final Map<String, String> errorMessages;


    // error codes and messages


    static {
        errorMessages = new HashMap<>();
    }

    //


    public static String getErrorMessage(String code) {
        if (errorMessages.containsKey(code)) {
            return errorMessages.get(code);
        }
        return UNEXPECTED_ERROR;
    }



}
