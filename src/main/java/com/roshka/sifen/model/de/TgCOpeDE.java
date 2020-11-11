package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.model.de.types.TTipEmi;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgCOpeDE {
    private TTipEmi iTipEmi;
    private int dCodSeg;            // número aleatorio entre 1 y 999999999 generado por el emisor
    private String dInfoEmi;        // información de interés del emisor (opcional). Entre 1 y 3000 carácteres
    private String dInfoFisc;       // información de interés del fisco (opcional). Entre 1 y 3000 carácteres

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE) throws SOAPException {
        SOAPElement gOpeDE = DE.addChildElement("gOpeDE", NamespacesConstants.SIFEN_NS_PREFIX);
        gOpeDE.addChildElement("iTipEmi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iTipEmi.getVal()));
        gOpeDE.addChildElement("dDesTipEmi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.iTipEmi.getDescripcion());
        gOpeDE.addChildElement("dCodSeg", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dCodSeg));
        if (this.dInfoEmi != null)
            gOpeDE.addChildElement("dInfoEmi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dInfoEmi);
        if (this.dInfoFisc != null || iTiDE.getVal() == 7)
            gOpeDE.addChildElement("dInfoFisc", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dInfoFisc);
    }

    public TTipEmi getiTipEmi() {
        return iTipEmi;
    }

    public void setiTipEmi(TTipEmi iTipEmi) {
        this.iTipEmi = iTipEmi;
    }

    public int getdCodSeg() {
        return dCodSeg;
    }

    public void setdCodSeg(int dCodSeg) {
        this.dCodSeg = dCodSeg;
    }

    public String getdInfoEmi() {
        return dInfoEmi;
    }

    public void setdInfoEmi(String dInfoEmi) {
        this.dInfoEmi = dInfoEmi;
    }

    public String getdInfoFisc() {
        return dInfoFisc;
    }

    public void setdInfoFisc(String dInfoFisc) {
        this.dInfoFisc = dInfoFisc;
    }
}
