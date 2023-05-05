package com.roshka.sifen.server.handlers;

import com.roshka.sifen.server.exceptions.SifenServerException;
import com.roshka.sifen.server.http.SifenServerRequest;
import com.roshka.sifen.server.http.SifenServerResponse;
import com.sun.net.httpserver.HttpExchange;

public interface SifenServerHandler {

    SifenServerResponse process(SifenServerRequest request)
        throws SifenServerException;

}
