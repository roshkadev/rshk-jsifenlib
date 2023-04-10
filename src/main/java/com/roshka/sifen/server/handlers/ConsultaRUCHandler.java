package com.roshka.sifen.server.handlers;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.response.RespuestaConsultaRUC;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.server.exceptions.SifenServerException;
import com.roshka.sifen.server.http.SifenServerRequest;
import com.roshka.sifen.server.http.SifenServerResponse;
import com.sun.net.httpserver.HttpExchange;

import java.util.logging.Logger;

public class ConsultaRUCHandler implements SifenServerHandler {

    private static final Logger logger = Logger.getLogger(ConsultaRUCHandler.class.toString());

    private final SifenConfig sifenConfig;

    public ConsultaRUCHandler(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;
    }

    @Override
    public SifenServerResponse process(SifenServerRequest request) throws SifenServerException {

        SifenServerResponse sifenServerResponse = new SifenServerResponse();

        try {
            RespuestaConsultaRUC respuestaConsultaRUC = Sifen.consultaRUC("980527-3", sifenConfig);

            logger.info(respuestaConsultaRUC.getdMsgRes());

        } catch (SifenException e) {
            logger.throwing(this.getClass().getName(), "process", e);
        }

        return sifenServerResponse;
    }
}
