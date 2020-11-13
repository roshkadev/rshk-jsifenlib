package com.roshka.sifen.sdk.v150;

import com.roshka.sifen.context.SifenCtx;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionUtil;
import com.roshka.sifen.http.RespuestaSOAP;
import com.roshka.sifen.http.RespuestaSifen;
import com.roshka.sifen.http.SOAPHelper;
import com.roshka.sifen.model.envi.REnviBase;
import com.roshka.sifen.model.res.RResBase;
import com.roshka.sifen.soap.MessageHelper;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.logging.Logger;

public abstract class ConsultaBase<T extends REnviBase, U extends RResBase> {
    private final static Logger logger = Logger.getLogger(ConsultaBase.class.toString());

    private SifenCtx sifenCtx;
    private String url;

    public ConsultaBase(SifenCtx sifenCtx) {
        this.sifenCtx = sifenCtx;
    }

    public RespuestaSifen<T, U> ejecutarConsulta(T peticion) throws SifenException {
        try {
            RespuestaSifen<T, U> respuestaSifen = new RespuestaSifen<>();
            respuestaSifen.setPeticion(peticion);

            // Preparamos el mensaje
            SOAPMessage message = MessageHelper.createMessage();
            peticion.setupSOAPBody(message.getSOAPBody(), this.sifenCtx.getSifenConfig());

            // Realizamos la consulta
            RespuestaSOAP respuestaSOAP = SOAPHelper.performSOAPRequest(this.getSifenCtx(), message, this.getUrl());
            respuestaSifen.setRespuestaSOAP(respuestaSOAP);
            if (respuestaSOAP.llamadaCorrecta()) {
                logger.info("Llamada Sifen Correcta en FORMA");
                respuestaSifen.setRespuesta(procesarRespuesta(respuestaSOAP.getRespuestaSOAP()));
            } else {
                logger.info("Llamada Incorrecta!");
            }
            return respuestaSifen;
        } catch (SOAPException e) {
            String msg = "Ocurrió un error al realizan la petición a: " + getUrl() + ". Mensaje: " + e.getLocalizedMessage();
            throw SifenExceptionUtil.llamadaSOAPInvalida(msg, e);
        }
    }

    protected Node getMainNode(SOAPBody soapBody, String nombreNodo) throws SifenException {
        if (soapBody == null)
            throw SifenExceptionUtil.respuestaSOAPInvalida("El cuerpo del mensaje soap es nulo. No se puede obtener el nodo principal");

        Node node = soapBody.getFirstChild();
        if (node == null)
            throw SifenExceptionUtil.respuestaSOAPInvalida("El cuerpo del mensaje soap tiene el primer nodo nulo");

        if (node.getNodeName() == null || !node.getLocalName().equalsIgnoreCase(nombreNodo)) {
            throw SifenExceptionUtil.respuestaSOAPInvalida(
                    "El nombre del nodo [" + node.getLocalName() + "] no coincide con el nombre esperado [" + nombreNodo + "]"
            );
        }
        return node;
    }

    public abstract U procesarRespuesta(SOAPMessage soapMessage) throws SOAPException, SifenException;

    public SifenCtx getSifenCtx() {
        return sifenCtx;
    }

    public void setSifenCtx(SifenCtx sifenCtx) {
        this.sifenCtx = sifenCtx;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
