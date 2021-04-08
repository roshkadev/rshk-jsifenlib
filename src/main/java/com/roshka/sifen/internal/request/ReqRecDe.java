package com.roshka.sifen.internal.request;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.SOAPResponse;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.RespuestaSifen;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionDE;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class ReqRecDe extends BaseRequest {
    private DocumentoElectronico DE;

    public ReqRecDe(long dId, SifenConfig sifenConfig) {
        super(dId, sifenConfig);
    }

    @Override
    SOAPMessage setupSoapMessage() throws SifenException {
        try {
            return this.DE.setupSOAPElements(this.getdId(), this.getSifenConfig());
        } catch (SOAPException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    @Override
    RespuestaSifen processResponse(SOAPResponse soapResponse) throws SifenException {
        Node nodeRRetEnviDe = ResponseUtil.getMainNode(soapResponse.getSoapResponse(), "rRetEnviDe");
        RespuestaRecepcionDE respuestaRecepcionDE = SifenObjectFactory.getFromNode(nodeRRetEnviDe, RespuestaRecepcionDE.class);

        RespuestaSifen respuestaSifen = new RespuestaSifen();
        respuestaSifen.setCodigoEstado(soapResponse.getStatus());
        respuestaSifen.setRespuesta(respuestaRecepcionDE);
        return respuestaSifen;
    }

    public void setDE(DocumentoElectronico DE) {
        this.DE = DE;
    }
}