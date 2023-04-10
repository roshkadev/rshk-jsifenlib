package com.roshka.sifen.server.json;

import com.eclipsesource.json.*;

public class JSONHelper {

    private static final WriterConfig _writerConfig;

    static {
        _writerConfig = PrettyPrint.indentWithSpaces(4);
    }

    public static String serialize(JsonValue jsonValue) {
        return jsonValue.toString(_writerConfig);
    }

}
