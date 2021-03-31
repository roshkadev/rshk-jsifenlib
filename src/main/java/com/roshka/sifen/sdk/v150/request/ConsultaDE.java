package com.roshka.sifen.sdk.v150.request;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.http.SOAPResponse;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.envi.REnviConsDe;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.sdk.v150.response.consultaDE.ConsultaDEResponse;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPMessage;
import java.util.logging.Logger;

public class ConsultaDE extends BaseRequest {
    private final static Logger logger = Logger.getLogger(ConsultaDE.class.toString());

    public static RespuestaSifen prepareRequest(SifenConfig sifenConfig, long dId, String cdc) throws SifenException {
        logger.info("Preparando petición 'Consulta de DE'");
        setConfig(sifenConfig, sifenConfig.getUrlConsulta());

        REnviConsDe rEnviConsDe = new REnviConsDe();
        rEnviConsDe.setdId(dId);
        rEnviConsDe.setdCDC(cdc);

        return processResponse(makeRequest(rEnviConsDe));
    }

    private static RespuestaSifen processResponse(SOAPResponse soapResponse) throws SifenException {
        SOAPMessage soapMessage = ResponseUtil.parseSoapMessage(soapResponse.getSoapResponse());
        Node nodeRRetEnviConsDe = ResponseUtil.getMainNode(soapMessage, "rEnviConsDeResponse");
        ConsultaDEResponse consultaDEResponse = SifenObjectFactory.getFromNode(nodeRRetEnviConsDe, ConsultaDEResponse.class);

        RespuestaSifen respuestaSifen = new RespuestaSifen();
        respuestaSifen.setCodigoEstado(soapResponse.getStatus());
        respuestaSifen.setRespuesta(consultaDEResponse);
        return respuestaSifen;
    }
}