package com.roshka.sifen.sdk.v150.response.consultaDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class ContenEv extends SifenObjectBase {
    private final List<ContEv> rContEvList = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("rContEv")) {
            rContEvList.add(SifenObjectFactory.getFromNode(value, ContEv.class));
        }
    }

    public List<ContEv> getrContEvList() {
        return rContEvList;
    }
}