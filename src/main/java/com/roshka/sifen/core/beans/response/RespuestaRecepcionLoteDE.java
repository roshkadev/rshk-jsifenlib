package com.roshka.sifen.core.beans.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;

/**
 * Clase principal que contiene la respuesta de Sifen al envío de lote de Documentos Electrónicos para aprobación.
 */
public class RespuestaRecepcionLoteDE extends BaseResponse {
    private LocalDateTime dFecProc;
    private String dProtConsLote;
    private int dTpoProces;

    /**
     * Método interno, no usar.
     */
    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dFecProc":
                dFecProc = ResponseUtil.getDateTimeValue(value);
                break;
            case "dProtConsLote":
                dProtConsLote = ResponseUtil.getTextValue(value);
                break;
            case "dTpoProces":
                dTpoProces = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            default:
                super.setValueFromChildNode(value);
        }
    }

    public LocalDateTime getdFecProc() {
        return dFecProc;
    }

    public String getdProtConsLote() {
        return dProtConsLote;
    }

    public int getdTpoProces() {
        return dTpoProces;
    }
}