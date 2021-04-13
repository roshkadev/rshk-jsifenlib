package com.roshka.sifen.core.fields.response.batch;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.response.TgResProc;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class TgResProcLote extends SifenObjectBase {
    private String id;
    private String dEstRes;
    private String dProtAut;
    private final List<TgResProc> gResProc = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "id":
                id = ResponseUtil.getTextValue(value);
                break;
            case "dEstRes":
                dEstRes = ResponseUtil.getTextValue(value);
                break;
            case "dProtAut":
                dProtAut = ResponseUtil.getTextValue(value);
                break;
            case "gResProc":
                gResProc.add(SifenObjectFactory.getFromNode(value, TgResProc.class));
                break;
        }
    }

    public String getdEstRes() {
        return dEstRes;
    }

    public String getdProtAut() {
        return dProtAut;
    }

    public String getId() {
        return id;
    }

    public List<TgResProc> getgResProc() {
        return gResProc;
    }
}