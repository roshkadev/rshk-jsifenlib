package com.roshka.sifen.sdk.v150.response.recepcionDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class ProtDe extends SifenObjectBase {
    public static final String NOMBRE_ELEMENTO_ID = "id";
    public static final String NOMBRE_ELEMENTO_DFECPROC = "dFecProc";
    public static final String NOMBRE_ELEMENTO_DDIGVAL = "dDigVal";
    public static final String NOMBRE_ELEMENTO_DESTRES = "dEstRes";
    public static final String NOMBRE_ELEMENTO_DPROTAUT = "dProtAut";
    public static final String NOMBRE_ELEMENTO_GRESPROC = "gResProc";

    private String id;
    private String dFecProc;
    private String dDigVal;
    private String dEstRes;
    private String dProtAut;
    private final List<ResProc> gResProc = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals(NOMBRE_ELEMENTO_ID)) {
            id = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DFECPROC)) {
            dFecProc = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DDIGVAL)) {
            dDigVal = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DESTRES)) {
            dEstRes = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DPROTAUT)) {
            dProtAut = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_GRESPROC)) {
            gResProc.add(SifenObjectFactory.getFromNode(value, ResProc.class));
        }
    }

    public String getId() {
        return id;
    }

    public String getdFecProc() {
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

    public List<ResProc> getgResProc() {
        return gResProc;
    }
}
