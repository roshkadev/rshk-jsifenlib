package com.roshka.sifen.core.fields.response;

import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

public class TgResProc extends SifenObjectBase {
    private String dCodRes;
    private String dMsgRes;

    @Override
    public void setValueFromChildNode(Node value) {
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