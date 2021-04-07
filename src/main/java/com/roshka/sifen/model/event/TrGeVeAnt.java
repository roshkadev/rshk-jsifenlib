package com.roshka.sifen.model.event;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

public class TrGeVeAnt extends SifenObjectBase {
    private String Id;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("Id")) {
            this.Id = ResponseUtil.getTextValue(value);
        }
    }

    public String getId() {
        return Id;
    }
}