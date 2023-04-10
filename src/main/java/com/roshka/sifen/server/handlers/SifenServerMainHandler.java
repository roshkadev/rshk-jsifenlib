package com.roshka.sifen.server.handlers;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.server.exceptions.SifenServerException;
import com.roshka.sifen.server.exceptions.SifenServerExceptionCodes;
import com.roshka.sifen.server.http.SifenServerRequest;
import com.roshka.sifen.server.http.SifenServerResponse;
import com.roshka.sifen.server.json.JSONHelper;
import com.roshka.sifen.server.json.SifenServerExceptionHelper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SifenServerMainHandler implements HttpHandler {

    protected static Logger logger = Logger.getLogger(SifenServerMainHandler.class.toString());

    private final SifenConfig sifenConfig;

    private Map<String, SifenServerHandler> handlersMap;

    public SifenServerMainHandler(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;

        setupHandlers();
    }

    private void setupHandlers() {
        handlersMap = new HashMap<>();
        handlersMap.put("/ruc", new ConsultaRUCHandler(sifenConfig));

    }

    private Object parseRequestContent(HttpExchange exchange)
            throws SifenServerException
    {
        Headers headers = exchange.getRequestHeaders();

        if (headers.containsKey("Content-Type")) {
            String contentType = headers.getFirst("Content-Type");
            switch (contentType) {
                case "application/json":
                    break;
                case "application/x-www-form-urlencoded":
                    break;
                default:
                    throw new SifenServerException("Content type [" + contentType + "] unsupported", SifenServerExceptionCodes.NOT_SUPPORTED_CONTENT_TYPE, 501);
            }
        }

        return null;
    }

    private SifenServerRequest getSifenRequestFromExchange(HttpExchange exchange) throws SifenServerException {

        SifenServerRequest ssr = new SifenServerRequest();

        URI requestURI = exchange.getRequestURI();

        ssr.setExchange(exchange);
        ssr.setMethod(exchange.getRequestMethod());
        ssr.setFullPath(requestURI.getPath());

        boolean handlerFound = false;

        for (String path : handlersMap.keySet()) {

            if (ssr.getFullPath().startsWith(path)) {
                ssr.setBasePath(path);
                if (ssr.getFullPath().length() >= path.length()) {
                    ssr.setPathParameters(ssr.getFullPath().substring(path.length()));
                }
                ssr.setHandler(handlersMap.get(path));
                handlerFound = true;
                break;
            }

        }

        if (!handlerFound) {
            throw new SifenServerException("Requested path [" + ssr.getFullPath() + "] not found", SifenServerExceptionCodes.PATH_NOT_FOUND, 404);
        }

        Object o = parseRequestContent(exchange);

        if (o != null) {
            // no content
            ssr.setRequestObject(o);
        }

        return ssr;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        URI requestURI = exchange.getRequestURI();
        logger.info("Procesando petición de tipo [" + exchange.getRequestMethod() + "] y path: " + requestURI.getPath());


        /*
        PROCESAR REQUEST
         */

        try {
            SifenServerRequest ssReq = getSifenRequestFromExchange(exchange);
            SifenServerHandler requestHandler = ssReq.getHandler();
            SifenServerResponse ssRes = requestHandler.process(ssReq);

            String response = "{\"hello\": \"world\"}";

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        } catch (SifenServerException e) {
            String response = JSONHelper.serialize(SifenServerExceptionHelper.toJSON(e));
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(e.getHttpCode(), response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }


    }


}
