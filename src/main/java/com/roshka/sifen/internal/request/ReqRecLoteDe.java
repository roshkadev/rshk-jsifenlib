package com.roshka.sifen.internal.request;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionLoteDE;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.Constants;
import com.roshka.sifen.internal.SOAPResponse;
import com.roshka.sifen.internal.ctx.GenerationCtx;
import com.roshka.sifen.internal.helpers.SoapHelper;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Clase encargada de la petición de Recepción de Lote de Documentos Electrónicos.
 */
public class ReqRecLoteDe extends BaseRequest {
    private List<DocumentoElectronico> DEList;
    private final static Logger logger = Logger.getLogger(ReqRecLoteDe.class.toString());
//    @Value("#{new Boolean('${useReceivedCDC}')}")
//    public Boolean useReceivedCDC;
//    private Boolean useReceivedCDC = true;

    public ReqRecLoteDe(long dId, SifenConfig sifenConfig) {
        super(dId, sifenConfig);
    }

    @Override
    SOAPMessage setupSoapMessage(GenerationCtx generationCtx) throws SifenException {
        try {
            SOAPMessage message = SoapHelper.createSoapMessage();
            SOAPBody soapBody = message.getSOAPBody();

            // Main Element
            SOAPBodyElement rEnvioLote = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, "rEnvioLote"));
            rEnvioLote.addChildElement("dId").setTextContent(String.valueOf(this.getdId()));
            SOAPElement xDE = rEnvioLote.addChildElement("xDE");

            SOAPElement rLoteDE = SoapHelper.createSoapMessage().getSOAPBody().addChildElement("rLoteDE");
            for (DocumentoElectronico DE : DEList) {
                DE.setupDE(generationCtx, rLoteDE, this.getSifenConfig());
            }
//            FIN CAMBIO

            // Obtenemos el XML
            final StringWriter sw = new StringWriter();
            try {
                TransformerFactory.newInstance().newTransformer().transform(new DOMSource(rLoteDE), new StreamResult(sw));
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }

            // Comprimimos a un archivo zip
            byte[] zipFile = SifenUtil.compressXmlToZip(sw.toString());

            // Convertimos el zip a Base64
            String rLoteDEBase64 = new String(Base64.getEncoder().encode(zipFile), StandardCharsets.UTF_8);
            xDE.setTextContent(rLoteDEBase64);

            return message;
        } catch (SOAPException | IOException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    @Override
    BaseResponse processResponse(SOAPResponse soapResponse) throws SifenException {
        Node rResEnviLoteDe = null;
        try {
            rResEnviLoteDe = ResponseUtil.getMainNode(soapResponse.getSoapResponse(), "rResEnviLoteDe");
        } catch (SifenException e) {
            logger.warning(e.getMessage());
        }

        RespuestaRecepcionLoteDE respuestaRecepcionLoteDE = new RespuestaRecepcionLoteDE();
        if (rResEnviLoteDe != null) {
            respuestaRecepcionLoteDE = SifenObjectFactory.getFromNode(rResEnviLoteDe, RespuestaRecepcionLoteDE.class);
        }

        respuestaRecepcionLoteDE.setCodigoEstado(soapResponse.getStatus());
        respuestaRecepcionLoteDE.setRespuestaBruta(new String(soapResponse.getRawData(), StandardCharsets.UTF_8));
        return respuestaRecepcionLoteDE;
    }

    public void setDEList(List<DocumentoElectronico> DEList) {
        this.DEList = DEList;
    }

}