package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.util.SifenUtil;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDate;

public class TgTimb {
    private TTiDE tiDE;         // este campo engloba a iTiDE y a dDesTiDE
    private int dNumTim;        // número de timbrado
    private String dEst;         // código de establecimiento: patron ej: 001
    private String dPunExp;      // punto de expedición: patron ej: 001
    private String dNumDoc;      // número de documento: patron ej: 0192312
    private String dSerieNum;   // número de serie del timbrado (opcional)
    private LocalDate dFeIniT;  // fecha de inicio de vigencia del timbrado

    public void setupSOAPElements(SOAPElement DE) throws SOAPException {
        SOAPElement gTimb = DE.addChildElement("gTimb");
        gTimb.addChildElement("iTiDE").setTextContent(String.valueOf(this.tiDE.getVal()));
        gTimb.addChildElement("dDesTiDE").setTextContent(this.tiDE.getDescripcion());
        gTimb.addChildElement("dNumTim").setTextContent(String.valueOf(this.dNumTim));
        gTimb.addChildElement("dEst").setTextContent(this.dEst);
        gTimb.addChildElement("dPunExp").setTextContent(this.dPunExp);
        gTimb.addChildElement("dNumDoc").setTextContent(this.dNumDoc);
        if (this.dSerieNum != null)
            gTimb.addChildElement("dSerieNum").setTextContent(this.dSerieNum);
        gTimb.addChildElement("dFeIniT").setTextContent(this.dFeIniT.toString());
    }

    public TTiDE getTiDE() {
        return tiDE;
    }

    public void setTiDE(TTiDE tiDE) {
        this.tiDE = tiDE;
    }

    public int getdNumTim() {
        return dNumTim;
    }

    public void setdNumTim(int dNumTim) {
        this.dNumTim = dNumTim;
    }

    public String getdEst() {
        return dEst;
    }

    public void setdEst(String dEst) {
        this.dEst = SifenUtil.leftPad(String.valueOf(dEst), '0', 3);
    }

    public String getdPunExp() {
        return dPunExp;
    }

    public void setdPunExp(String dPunExp) {
        this.dPunExp = SifenUtil.leftPad(String.valueOf(dPunExp), '0', 3);
    }

    public String getdNumDoc() {
        return dNumDoc;
    }

    public void setdNumDoc(String dNumDoc) {
        this.dNumDoc = SifenUtil.leftPad(String.valueOf(dNumDoc), '0', 7);
    }

    public String getdSerieNum() {
        return dSerieNum;
    }

    public void setdSerieNum(String dSerieNum) {
        this.dSerieNum = dSerieNum;
    }

    public LocalDate getdFeIniT() {
        return dFeIniT;
    }

    public void setdFeIniT(LocalDate dFeIniT) {
        this.dFeIniT = dFeIniT;
    }
}
