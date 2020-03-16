package com.roshka.sifen.model.res;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import org.w3c.dom.Node;

public abstract class RResBase extends SifenObjectBase {

    public static final String NOMBRE_ELEMENTO_DCODRES = "dCodRes";
    public static final String NOMBRE_ELEMENTO_DMSGRES = "dMsgRes";

    private String dCodRes;
    private String dMsgRes;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals(NOMBRE_ELEMENTO_DCODRES)) {
            setdCodRes(getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DMSGRES)) {
            setdMsgRes(getTextValue(value));
        } else {
            super.setValueFromChildNode(value);
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
