package com.roshka.sifen.internal.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

/**
 * Clase abstracta heredada por las clases de respuestas a las peticiones.
 */
public abstract class BaseResponse extends SifenObjectBase {
    private int codigoEstado;
    private String respuestaBruta;

    private String dCodRes;
    private String dMsgRes;
    private String requestSent;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dCodRes")) {
            dCodRes = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals("dMsgRes")) {
            dMsgRes = ResponseUtil.getTextValue(value);
        }
    }

    public String getdCodRes() {
        return dCodRes;
    }

    public String getdMsgRes() {
        return dMsgRes;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getRespuestaBruta() {
        return respuestaBruta;
    }

    public void setRespuestaBruta(String respuestaBruta) {
        this.respuestaBruta = respuestaBruta;
    }

    public void setdCodRes(String dCodRes) {
        this.dCodRes = dCodRes;
    }

    public void setdMsgRes(String dMsgRes) {
        this.dMsgRes = dMsgRes;
    }

    public String getRequestSent() {
        return requestSent;
    }

    public void setRequestSent(String requestSent) {
        this.requestSent = requestSent;
    }
}