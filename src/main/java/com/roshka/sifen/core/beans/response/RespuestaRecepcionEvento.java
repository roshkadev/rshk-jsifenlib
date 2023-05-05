package com.roshka.sifen.core.beans.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.response.event.TgResProcEVe;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que contiene la respuesta de Sifen al envío de eventos relacionados a los Documentos Electrónicos.
 */
public class RespuestaRecepcionEvento extends BaseResponse {
    private LocalDateTime dFecProc;
    private final List<TgResProcEVe> gResProcEVe = new ArrayList<>();

    /**
     * Método interno, no usar.
     */
    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dFecProc")) {
            dFecProc = ResponseUtil.getDateTimeValue(value);
        } else if (value.getLocalName().equals("gResProcEVe")) {
            gResProcEVe.add(SifenObjectFactory.getFromNode(value, TgResProcEVe.class));
        }
    }

    public LocalDateTime getdFecProc() {
        return dFecProc;
    }

    public List<TgResProcEVe> getgResProcEVe() {
        return gResProcEVe;
    }
}