package com.roshka.sifen.sdk.v150.response.consultaDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import org.w3c.dom.Node;

public class ContenEv extends SifenObjectBase {
    private ContEv rContEv;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("rContEv")) {
            rContEv = SifenObjectFactory.getFromNode(value, ContEv.class);
        }
    }

    public ContEv getrContEv() {
        return rContEv;
    }
}