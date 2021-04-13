package com.roshka.sifen.core.beans.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.response.batch.TgResProcLote;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que contiene la respuesta de Sifen a la consulta de estado de un lote de Documentos Electrónicos.
 */
public class RespuestaConsultaLoteDE extends BaseResponse {
    private LocalDateTime dFecProc;
    private String dCodResLot;
    private String dMsgResLot;
    private final List<TgResProcLote> gResProcLoteList = new ArrayList<>();

    /**
     * Método interno, no usar.
     */
    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dFecProc":
                dFecProc = ResponseUtil.getDateTimeValue(value);
                break;
            case "dCodResLot":
                dCodResLot = ResponseUtil.getTextValue(value);
                break;
            case "dMsgResLot":
                dMsgResLot = ResponseUtil.getTextValue(value);
                break;
            case "gResProcLote":
                gResProcLoteList.add(SifenObjectFactory.getFromNode(value, TgResProcLote.class));
                break;
        }
    }

    public LocalDateTime getdFecProc() {
        return dFecProc;
    }

    public String getdCodResLot() {
        return dCodResLot;
    }

    public String getdMsgResLot() {
        return dMsgResLot;
    }

    public List<TgResProcLote> getgResProcLoteList() {
        return gResProcLoteList;
    }
}