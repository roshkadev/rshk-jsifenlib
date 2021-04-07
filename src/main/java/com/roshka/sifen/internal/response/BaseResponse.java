package com.roshka.sifen.internal.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

public abstract class BaseResponse extends SifenObjectBase {
    private String dCodRes;
    private String dMsgRes;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dCodRes")) {
            dCodRes = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals("dMsgRes")) {
            dMsgRes = ResponseUtil.getTextValue(value);
        }
    }

    public String getdCodRes() {
        return dCodRes;
    }

    public String getdMsgRes() {
        return dMsgRes;
    }
}