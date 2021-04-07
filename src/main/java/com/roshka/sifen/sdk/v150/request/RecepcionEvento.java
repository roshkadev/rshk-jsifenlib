package com.roshka.sifen.sdk.v150.request;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.http.SOAPResponse;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.envi.REnviEventoDe;
import com.roshka.sifen.sdk.v150.beans.EventoDE;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.sdk.v150.response.recepcionEv.RecepcionEvResponse;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.logging.Logger;

public class RecepcionEvento extends BaseRequest {

    private final static Logger logger = Logger.getLogger(RecepcionEvento.class.toString());

    public static RespuestaSifen prepareRequest(SifenConfig sifenConfig, long dId, EventoDE eventoDE) throws SifenException {
        logger.info("Preparando petición 'Recepción de Eventos'");
        setConfig(sifenConfig, sifenConfig.getUrlEvento());

        REnviEventoDe rEnviEventoDe = new REnviEventoDe();
        rEnviEventoDe.setdId(dId);
        rEnviEventoDe.setEventoDE(eventoDE);

        return processResponse(makeRequest(rEnviEventoDe));
    }

    private static RespuestaSifen processResponse(SOAPResponse soapResponse) throws SifenException {
        Node rRetEnviEventoDe = ResponseUtil.getMainNode(soapResponse.getSoapResponse(), "rRetEnviEventoDe");
        RecepcionEvResponse recepcionEvResponse = SifenObjectFactory.getFromNode(rRetEnviEventoDe, RecepcionEvResponse.class);

        RespuestaSifen respuestaSifen = new RespuestaSifen();
        respuestaSifen.setCodigoEstado(soapResponse.getStatus());
        respuestaSifen.setRespuesta(recepcionEvResponse);
        return respuestaSifen;
    }
}