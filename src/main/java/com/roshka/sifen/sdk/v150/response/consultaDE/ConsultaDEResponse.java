package com.roshka.sifen.sdk.v150.response.consultaDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.response.BaseResponse;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;

public class ConsultaDEResponse extends BaseResponse {
    private LocalDateTime dFecProc;
    private ContenDE xContenDE;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dFecProc")) {
            dFecProc = ResponseUtil.getDateTimeValue(value);
        } else if (value.getLocalName().equals("xContenDE")) {
            xContenDE = SifenObjectFactory.getFromNode(value, ContenDE.class);
        } else {
            super.setValueFromChildNode(value);
        }
    }

    public LocalDateTime getdFecProc() {
        return dFecProc;
    }

    public ContenDE getxContenDE() {
        return xContenDE;
    }
}