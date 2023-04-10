package com.roshka.sifen.server.http;

import com.roshka.sifen.server.handlers.SifenServerHandler;
import com.sun.net.httpserver.HttpExchange;

public class SifenServerRequest {

    private String method;

    private String basePath;
    private String fullPath;
    private String pathParameters;

    private Object requestObject;

    private SifenServerHandler handler;

    private HttpExchange exchange;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(String pathParameters) {
        this.pathParameters = pathParameters;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public SifenServerHandler getHandler() {
        return handler;
    }

    public void setHandler(SifenServerHandler handler) {
        this.handler = handler;
    }

    public HttpExchange getExchange() {
        return exchange;
    }

    public void setExchange(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public Object getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(Object requestObject) {
        this.requestObject = requestObject;
    }
}