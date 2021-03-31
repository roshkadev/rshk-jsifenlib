package com.roshka.sifen.sdk.v150.response.recepcionEv;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.response.common.ResProc;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class ResProcEVe extends SifenObjectBase {
    private String dEstRes;
    private String dProtAuto;
    private String id;
    private final List<ResProc> gResProc = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dEstRes":
                dEstRes = ResponseUtil.getTextValue(value);
                break;
            case "dProtAuto":
                dProtAuto = ResponseUtil.getTextValue(value);
                break;
            case "id":
                id = ResponseUtil.getTextValue(value);
                break;
            case "gResProc":
                gResProc.add(SifenObjectFactory.getFromNode(value, ResProc.class));
                break;
        }
    }

    public String getdEstRes() {
        return dEstRes;
    }

    public String getdProtAuto() {
        return dProtAuto;
    }

    public String getId() {
        return id;
    }

    public List<ResProc> getgResProc() {
        return gResProc;
    }
}