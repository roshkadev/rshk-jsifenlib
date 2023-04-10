package com.roshka.sifen.server.json;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.roshka.sifen.server.exceptions.SifenServerException;

public class SifenServerExceptionHelper {

    public static JsonValue toJSON(SifenServerException e) {
        JsonObject jo = new JsonObject();
        jo.add("code", e.getCode());
        jo.add("message", e.getMessage());
        return jo;
    }

}
