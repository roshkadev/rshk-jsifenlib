package com.roshka.sifen.sdk.v150.request;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.http.SOAPResponse;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.envi.REnviConsRUC;
import com.roshka.sifen.sdk.v150.response.consultaRuc.ConsultaRUCResponse;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.logging.Logger;

public class ConsultaRUC extends BaseRequest {
    private final static Logger logger = Logger.getLogger(ConsultaRUC.class.toString());

    public static RespuestaSifen prepareRequest(SifenConfig sifenConfig, long dId, String ruc) throws SifenException {
        logger.info("Preparando petición 'Consulta de RUC'");
        setConfig(sifenConfig, sifenConfig.getUrlConsultaRUC());

        REnviConsRUC rEnviConsRUC = new REnviConsRUC();
        rEnviConsRUC.setdId(dId);
        rEnviConsRUC.setdRUCCons(ruc);

        return processResponse(makeRequest(rEnviConsRUC));
    }

    private static RespuestaSifen processResponse(SOAPResponse soapResponse) throws SifenException {
        Node nodeRResEnviConsRuc = ResponseUtil.getMainNode(soapResponse.getSoapResponse(), "rResEnviConsRuc");
        ConsultaRUCResponse consultaRUCResponse = SifenObjectFactory.getFromNode(nodeRResEnviConsRuc, ConsultaRUCResponse.class);

        RespuestaSifen respuestaSifen = new RespuestaSifen();
        respuestaSifen.setCodigoEstado(soapResponse.getStatus());
        respuestaSifen.setRespuesta(consultaRUCResponse);
        return respuestaSifen;
    }
}
