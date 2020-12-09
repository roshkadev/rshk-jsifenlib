package com.roshka.sifen.sdk.v150.request;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.http.SOAPResponse;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.envi.REnviDe;
import com.roshka.sifen.sdk.v150.beans.DocumentoElectronico;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.sdk.v150.response.recepcionDE.RecepcionDEResponse;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.logging.Logger;

public class RecepcionDE extends BaseRequest {

    private final static Logger logger = Logger.getLogger(RecepcionDE.class.toString());

    public static RespuestaSifen prepareRequest(SifenConfig sifenConfig, long dId, DocumentoElectronico de) throws SifenException {
        logger.info("Preparando petición 'Recepción de DE'");
        setConfig(sifenConfig, sifenConfig.getUrlRecibe());

        REnviDe rEnviDe = new REnviDe();
        rEnviDe.setdId(dId);
        rEnviDe.setDE(de);

        return processResponse(makeRequest(rEnviDe));
    }

    private static RespuestaSifen processResponse(SOAPResponse soapResponse) throws SifenException {
        Node nodeRRetEnviDe = ResponseUtil.getMainNode(soapResponse.getSoapResponse(), "rRetEnviDe");
        RecepcionDEResponse recepcionDEResponse = SifenObjectFactory.getFromNode(nodeRRetEnviDe, RecepcionDEResponse.class);

        RespuestaSifen respuestaSifen = new RespuestaSifen();
        respuestaSifen.setCodigoEstado(soapResponse.getStatus());
        respuestaSifen.setRespuesta(recepcionDEResponse);
        return respuestaSifen;
    }
}
