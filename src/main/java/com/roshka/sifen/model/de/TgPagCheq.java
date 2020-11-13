package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgPagCheq {
    private String dNumCheq;
    private String dBcoEmi;

    public void setupSOAPElements(SOAPElement gPaConEIni) throws SOAPException {
        SOAPElement gPagCheq = gPaConEIni.addChildElement("gPagCheq", Constants.SIFEN_NS_PREFIX);
        gPagCheq.addChildElement("dNumCheq", Constants.SIFEN_NS_PREFIX).setTextContent(this.dNumCheq);
        gPagCheq.addChildElement("dBcoEmi", Constants.SIFEN_NS_PREFIX).setTextContent(this.dBcoEmi);
    }

    public String getdNumCheq() {
        return dNumCheq;
    }

    public void setdNumCheq(String dNumCheq) {
        this.dNumCheq = dNumCheq;
    }

    public String getdBcoEmi() {
        return dBcoEmi;
    }

    public void setdBcoEmi(String dBcoEmi) {
        this.dBcoEmi = dBcoEmi;
    }
}
