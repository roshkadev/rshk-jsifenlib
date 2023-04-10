package com.roshka.sifen.internal.request;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.SOAPResponse;
import com.roshka.sifen.internal.helpers.SoapHelper;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.Constants;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.beans.response.RespuestaConsultaRUC;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Clase encargada de la Consulta de RUC.
 */
public class ReqConsRuc extends BaseRequest {
    private String dRUCCons;
    private final static Logger logger = Logger.getLogger(ReqConsRuc.class.toString());

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
    BaseResponse processResponse(SOAPResponse soapResponse) throws SifenException {
        Node rResEnviConsRuc = null;
        try {
            rResEnviConsRuc = ResponseUtil.getMainNode(soapResponse.getSoapResponse(), "rResEnviConsRuc");
        } catch (SifenException e) {
            logger.warning(e.getMessage());
        }

        RespuestaConsultaRUC respuestaConsultaRUC = new RespuestaConsultaRUC();
        if (rResEnviConsRuc != null) {
            respuestaConsultaRUC = SifenObjectFactory.getFromNode(rResEnviConsRuc, RespuestaConsultaRUC.class);
        }

        respuestaConsultaRUC.setCodigoEstado(soapResponse.getStatus());
        respuestaConsultaRUC.setRespuestaBruta(new String(soapResponse.getRawData(), StandardCharsets.UTF_8));
        return respuestaConsultaRUC;
    }

    public void setdRUCCons(String dRUCCons) {
        this.dRUCCons = dRUCCons;
    }
}