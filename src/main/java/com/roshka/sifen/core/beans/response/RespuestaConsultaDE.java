package com.roshka.sifen.core.beans.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.response.BaseResponse;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.core.fields.response.de.TxContenDE;
import org.w3c.dom.Node;

import java.time.LocalDateTime;

public class RespuestaConsultaDE extends BaseResponse {
    private LocalDateTime dFecProc;
    private TxContenDE xContenDE;

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