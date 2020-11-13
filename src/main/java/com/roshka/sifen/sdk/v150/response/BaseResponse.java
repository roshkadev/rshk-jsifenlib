package com.roshka.sifen.sdk.v150.response;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.logging.Logger;

public abstract class BaseResponse extends SifenObjectBase {
    private final static Logger logger = Logger.getLogger(BaseResponse.class.toString());

    public static final String NOMBRE_ELEMENTO_DCODRES = "dCodRes";
    public static final String NOMBRE_ELEMENTO_DMSGRES = "dMsgRes";

    private String dCodRes;
    private String dMsgRes;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals(NOMBRE_ELEMENTO_DCODRES)) {
            setdCodRes(ResponseUtil.getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DMSGRES)) {
            setdMsgRes(ResponseUtil.getTextValue(value));
        }
    }

    public String getdCodRes() {
        return dCodRes;
    }

    public void setdCodRes(String dCodRes) {
        this.dCodRes = dCodRes;
    }

    public String getdMsgRes() {
        return dMsgRes;
    }

    public void setdMsgRes(String dMsgRes) {
        this.dMsgRes = dMsgRes;
    }
}
