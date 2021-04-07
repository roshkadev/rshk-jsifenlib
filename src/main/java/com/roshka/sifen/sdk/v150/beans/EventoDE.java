package com.roshka.sifen.sdk.v150.beans;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.event.TrGesEve;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Conjunto de Eventos de Documentos Electrónicos
 */
public class EventoDE extends SifenObjectBase {
    List<TrGesEve> rGesEveList;

    public void setupSOAPElements(SOAPElement gGroupGesEve, SifenConfig sifenConfig) throws SOAPException, SifenException {
        for (TrGesEve trGesEve : this.rGesEveList) {
            trGesEve.setupSOAPElements(gGroupGesEve, sifenConfig);
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("rGesEve")) {
            if (this.rGesEveList == null) {
                this.rGesEveList = new ArrayList<>();
            }
            this.rGesEveList.add(SifenObjectFactory.getFromNode(value, TrGesEve.class));
        }
    }

    public List<TrGesEve> getrGesEveList() {
        return rGesEveList;
    }

    public void setrGesEveList(List<TrGesEve> rGesEveList) {
        this.rGesEveList = rGesEveList;
    }
}