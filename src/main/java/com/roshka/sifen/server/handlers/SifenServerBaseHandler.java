package com.roshka.sifen.server.handlers;

import com.roshka.sifen.core.SifenConfig;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.logging.Logger;

public class SifenServerBaseHandler implements HttpHandler {

    protected static Logger logger = Logger.getLogger(SifenServerBaseHandler.class.toString());

    private final SifenConfig sifenConfig;

    public SifenServerBaseHandler(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        URI requestURI = exchange.getRequestURI();
        logger.info("Procesando petición de tipo [" + exchange.getRequestMethod() + "] y path: " + requestURI.getPath());

        /*
        PROCESAR REQUEST
         */

        String response = "{\"hello\": \"world\"}";

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }


}
