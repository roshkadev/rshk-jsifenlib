package com.roshka.sifen.core.fields.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TxProtDe extends SifenObjectBase {
    private String id;
    private LocalDateTime dFecProc;
    private String dDigVal;
    private String dEstRes;
    private String dProtAut;
    private final List<TgResProc> gResProc = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "Id":
                id = ResponseUtil.getTextValue(value);
                break;
            case "dFecProc":
                dFecProc = ResponseUtil.getDateTimeValue(value);
                break;
            case "dDigVal":
                dDigVal = ResponseUtil.getTextValue(value);
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

    public String getId() {
        return id;
    }

    public LocalDateTime getdFecProc() {
        return dFecProc;
    }

    public String getdDigVal() {
        return dDigVal;
    }

    public String getdEstRes() {
        return dEstRes;
    }

    public String getdProtAut() {
        return dProtAut;
    }

    public List<TgResProc> getgResProc() {
        return gResProc;
    }
}