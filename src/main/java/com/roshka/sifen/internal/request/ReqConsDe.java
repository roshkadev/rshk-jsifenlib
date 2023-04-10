package com.roshka.sifen.internal.request;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.SOAPResponse;
import com.roshka.sifen.internal.helpers.SoapHelper;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.Constants;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.beans.response.RespuestaConsultaDE;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Clase encargada de la Consulta de Documentos Electrónicos.
 */
public class ReqConsDe extends BaseRequest {
    private String dCDC;
    private final static Logger logger = Logger.getLogger(ReqConsDe.class.toString());

    public ReqConsDe(long dId, SifenConfig sifenConfig) {
        super(dId, sifenConfig);
    }

    @Override
    SOAPMessage setupSoapMessage() throws SifenException {
        try {
            // Main Element
            SOAPMessage message = SoapHelper.createSoapMessage();
            SOAPBody soapBody = message.getSOAPBody();
            SOAPBodyElement rEnviConsDeRequest = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, "rEnviConsDeRequest"));

            // Elements
            rEnviConsDeRequest.addChildElement("dId").setTextContent(String.valueOf(this.getdId()));
            rEnviConsDeRequest.addChildElement("dCDC").setTextContent(this.dCDC);

            return message;
        } catch (SOAPException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    @Override
    BaseResponse processResponse(SOAPResponse soapResponse) throws SifenException {
        Node rEnviConsDe = null;
        String rawResponse = null;
        try {
            SOAPMessage soapMessage = ResponseUtil.parseSoapMessage(soapResponse.getSoapResponse());
            rEnviConsDe = ResponseUtil.getMainNode(soapMessage, "rEnviConsDeResponse");
            rawResponse = ResponseUtil.getXmlFromMessage(soapMessage, false);
        } catch (SifenException e) {
            logger.warning(e.getMessage());
        }

        RespuestaConsultaDE respuestaConsultaDE = new RespuestaConsultaDE();
        if (rEnviConsDe != null) {
            respuestaConsultaDE = SifenObjectFactory.getFromNode(rEnviConsDe, RespuestaConsultaDE.class);
        }

        respuestaConsultaDE.setCodigoEstado(soapResponse.getStatus());
        respuestaConsultaDE.setRespuestaBruta(rawResponse != null ? rawResponse : new String(soapResponse.getRawData(), StandardCharsets.UTF_8));
        return respuestaConsultaDE;
    }

    public void setdCDC(String dCDC) {
        this.dCDC = dCDC;
    }
}