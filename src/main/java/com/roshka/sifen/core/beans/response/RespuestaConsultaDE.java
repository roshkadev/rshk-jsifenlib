package com.roshka.sifen.core.beans.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.response.de.TxContenDE;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;

/**
 * Clase principal que contiene la respuesta de Sifen a la consulta de un Documento Electrónico.
 */
public class RespuestaConsultaDE extends BaseResponse {
    private LocalDateTime dFecProc;
    private TxContenDE xContenDE;

    /**
     * Método interno, no usar.
     */
    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dFecProc")) {
            dFecProc = ResponseUtil.getDateTimeValue(value);
        } else if (value.getLocalName().equals("xContenDE")) {
            xContenDE = SifenObjectFactory.getFromNode(value, TxContenDE.class);
        } else {
            super.setValueFromChildNode(value);
        }
    }

    public LocalDateTime getdFecProc() {
        return dFecProc;
    }

    public TxContenDE getxContenDE() {
        return xContenDE;
    }
}