package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

public class TgActEco extends SifenObjectBase {
    private String cActEco;
    private String dDesActEco;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("cActEco")) {
            this.cActEco = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals("dDesActEco")) {
            this.dDesActEco = ResponseUtil.getTextValue(value);
        }
    }

    public String getcActEco() {
        return cActEco;
    }

    public void setcActEco(String cActEco) {
        this.cActEco = cActEco;
    }

    public String getdDesActEco() {
        return dDesActEco;
    }

    public void setdDesActEco(String dDesActEco) {
        this.dDesActEco = dDesActEco;
    }
}