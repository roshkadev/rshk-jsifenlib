package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.TiMotEmi;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgCamNCDE {
    private TiMotEmi iMotEmi;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SOAPElement gCamNCDE = gDtipDE.addChildElement("gCamNCDE", NamespacesConstants.SIFEN_NS_PREFIX);
        gCamNCDE.addChildElement("iMotEmi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iMotEmi.getVal()));
        gCamNCDE.addChildElement("dDesMotEmi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.iMotEmi.getDescripcion());
    }

    public TiMotEmi getiMotEmi() {
        return iMotEmi;
    }

    public void setiMotEmi(TiMotEmi iMotEmi) {
        this.iMotEmi = iMotEmi;
    }
}
