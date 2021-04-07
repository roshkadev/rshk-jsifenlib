package com.roshka.sifen.internal.request;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.beans.SOAPResponse;
import com.roshka.sifen.internal.helpers.SoapHelper;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.core.Constants;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.RespuestaSifen;
import com.roshka.sifen.core.beans.response.RespuestaConsultaRuc;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class ReqConsRuc extends BaseRequest {
    private String dRUCCons;

    public ReqConsRuc(long dId, SifenConfig sifenConfig) {
        super(dId, sifenConfig);
    }

    @Override
    SOAPMessage setupSoapMessage() throws SifenException {
        try {
            // Main Element
            SOAPMessage message = SoapHelper.createSoapMessage();
            SOAPBody soapBody = message.getSOAPBody();
            SOAPBodyElement rResEnviConsRUC = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, "rEnviConsRUC"));

            // Elements
            rResEnviConsRUC.addChildElement("dId").setTextContent(String.valueOf(this.getdId()));
            rResEnviConsRUC.addChildElement("dRUCCons").setTextContent(this.dRUCCons);

            return message;
        } catch (SOAPException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    @Override
    RespuestaSifen processResponse(SOAPResponse soapResponse) throws SifenException {
        Node nodeRResEnviConsRuc = ResponseUtil.getMainNode(soapResponse.getSoapResponse(), "rResEnviConsRuc");
        RespuestaConsultaRuc respuestaConsultaRuc = SifenObjectFactory.getFromNode(nodeRResEnviConsRuc, RespuestaConsultaRuc.class);

        RespuestaSifen respuestaSifen = new RespuestaSifen();
        respuestaSifen.setCodigoEstado(soapResponse.getStatus());
        respuestaSifen.setRespuesta(respuestaConsultaRuc);
        return respuestaSifen;
    }

    public void setdRUCCons(String dRUCCons) {
        this.dRUCCons = dRUCCons;
    }
}