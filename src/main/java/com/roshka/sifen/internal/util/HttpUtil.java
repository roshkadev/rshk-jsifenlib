package com.roshka.sifen.internal.util;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    public static String buildUrlParams(HashMap<String, String> params) {
        if (params.isEmpty()) return "";

        StringBuilder paramsString = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            paramsString.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        return paramsString.substring(0, paramsString.length() - 1);
    }
}