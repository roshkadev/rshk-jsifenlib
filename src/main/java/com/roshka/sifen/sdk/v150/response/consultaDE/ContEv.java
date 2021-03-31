package com.roshka.sifen.sdk.v150.response.consultaDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.response.recepcionEv.RecepcionEvResponse;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

public class ContEv extends SifenObjectBase {
    private String xEvento;
    private RecepcionEvResponse rResEnviEventoDe;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "xEvento":
                xEvento = ResponseUtil.getTextValue(value);
                break;
            case "rResEnviEventoDe":
                rResEnviEventoDe = SifenObjectFactory.getFromNode(value, RecepcionEvResponse.class);
                break;
        }
    }

    public String getxEvento() {
        return xEvento;
    }

    public RecepcionEvResponse getrResEnviEventoDe() {
        return rResEnviEventoDe;
    }
}