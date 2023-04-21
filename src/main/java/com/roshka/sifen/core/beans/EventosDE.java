package com.roshka.sifen.core.beans;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.event.TrGesEve;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un conjunto de Eventos de Documentos Electrónicos.
 */
public class EventosDE extends SifenObjectBase {
    List<TrGesEve> rGesEveList;

    /**
     * Método interno, no usar.
     * @param gGroupGesEve -
     * @param sifenConfig -
     * @throws SOAPException -
     * @throws SifenException -
     */
    public void setupSOAPElements(SOAPElement gGroupGesEve, SifenConfig sifenConfig) throws SOAPException, SifenException {
        for (TrGesEve trGesEve : this.rGesEveList) {
            trGesEve.setupSOAPElements(gGroupGesEve, sifenConfig);
        }
    }

    /**
     * Método interno, no usar.
     * @param value -
     * @throws SifenException -
     */
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