package com.roshka.sifen.sdk.v150.response.recepcionEv;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RetEnviEventoDe extends SifenObjectBase {
    private LocalDateTime dFecProc;
    private final List<ResProcEVe> gResProcEVe = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dFecProc")) {
            dFecProc = ResponseUtil.getDateTimeValue(value, true);
        } else if (value.getLocalName().equals("gResProcEVe")) {
            gResProcEVe.add(SifenObjectFactory.getFromNode(value, ResProcEVe.class));
        }
    }

    public LocalDateTime getdFecProc() {
        return dFecProc;
    }

    public List<ResProcEVe> getgResProcEVe() {
        return gResProcEVe;
    }
}