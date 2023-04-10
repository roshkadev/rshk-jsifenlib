package com.roshka.sifen.server.http;

import java.net.HttpURLConnection;
import java.util.Map;

public class SifenServerResponse {

    private int status;

    private Map<String, Object> data;

    public SifenServerResponse() {
        this.status = HttpURLConnection.HTTP_OK;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
