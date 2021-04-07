package com.roshka.sifen.sdk.v150.response.consultaDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.beans.EventoDE;
import com.roshka.sifen.sdk.v150.response.recepcionEv.RecepcionEvResponse;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ContEv extends SifenObjectBase {
    private EventoDE xEvento;
    private RecepcionEvResponse rResEnviEventoDe;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "xEvento":
                xEvento = SifenObjectFactory.getFromNode(value, EventoDE.class);
                break;
            case "rResEnviEventoDe":
                NodeList childNodes = value.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node node = childNodes.item(i);
                    if (node.getLocalName().equals("rRetEnviEventoDe")) {
                        rResEnviEventoDe = SifenObjectFactory.getFromNode(node, RecepcionEvResponse.class);
                    }
                }
                break;
        }
    }

    public EventoDE getxEvento() {
        return xEvento;
    }

    public RecepcionEvResponse getrResEnviEventoDe() {
        return rResEnviEventoDe;
    }
}