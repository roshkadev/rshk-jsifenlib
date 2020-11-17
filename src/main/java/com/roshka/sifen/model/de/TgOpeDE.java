package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.model.de.types.TTipEmi;
import com.roshka.sifen.util.SifenUtil;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgOpeDE {
    private TTipEmi iTipEmi;
    private final String dCodSeg;   // número aleatorio entre 1 y 999999999 generado por el emisor
    private String dInfoEmi;        // información de interés del emisor (opcional). Entre 1 y 3000 carácteres
    private String dInfoFisc;       // información de interés del fisco (opcional). Entre 1 y 3000 carácteres

    public TgOpeDE() {
        this.dCodSeg = SifenUtil.generateRandomNumber();
    }

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE) throws SOAPException {
        SOAPElement gOpeDE = DE.addChildElement("gOpeDE");
        gOpeDE.addChildElement("iTipEmi").setTextContent(String.valueOf(this.iTipEmi.getVal()));
        gOpeDE.addChildElement("dDesTipEmi").setTextContent(this.iTipEmi.getDescripcion());
        gOpeDE.addChildElement("dCodSeg").setTextContent(this.dCodSeg);
        if (this.dInfoEmi != null)
            gOpeDE.addChildElement("dInfoEmi").setTextContent(this.dInfoEmi);
        if (this.dInfoFisc != null || iTiDE.getVal() == 7)
            gOpeDE.addChildElement("dInfoFisc").setTextContent(this.dInfoFisc);
    }

    public TTipEmi getiTipEmi() {
        return iTipEmi;
    }

    public void setiTipEmi(TTipEmi iTipEmi) {
        this.iTipEmi = iTipEmi;
    }

    public String getdCodSeg() {
        return dCodSeg;
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
