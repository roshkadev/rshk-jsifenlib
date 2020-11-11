package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.TTiDE;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TgDTim {
    private TTiDE tiDE;         // este campo engloba a iTiDE y a dDesTiDE
    private int dNumTim;        // número de timbrado
    private short dEst;         // código de establecimiento: patron ej: 001
    private short dPunExp;      // punto de expedición: patron ej: 001
    private short dNumDoc;      // número de documento: patron ej: 0192312
    private String dSerieNum;   // número de serie del timbrado (opcional)
    private LocalDate dFeIniT;  // fecha de inicio de vigencia del timbrado
    private LocalDate dFeFinT;  // fecha de fin de vigencia del timbrado

    public void setupSOAPElements(SOAPElement DE) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SOAPElement gTimb = DE.addChildElement("gTimb", NamespacesConstants.SIFEN_NS_PREFIX);
        gTimb.addChildElement("iTiDE", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.tiDE.getVal()));
        gTimb.addChildElement("dDesTiDE", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.tiDE.getDescripcion());
        gTimb.addChildElement("dNumTim", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dNumTim));
        gTimb.addChildElement("dEst", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dEst));
        gTimb.addChildElement("dPunExp", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dPunExp));
        gTimb.addChildElement("dNumDoc", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dNumDoc));
        if (this.dSerieNum != null)
            gTimb.addChildElement("dSerieNum", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dSerieNum);
        gTimb.addChildElement("dFeIniT", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFeIniT));
        gTimb.addChildElement("dFeFinT", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFeFinT));
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

    public short getdEst() {
        return dEst;
    }

    public void setdEst(short dEst) {
        this.dEst = dEst;
    }

    public short getdPunExp() {
        return dPunExp;
    }

    public void setdPunExp(short dPunExp) {
        this.dPunExp = dPunExp;
    }

    public short getdNumDoc() {
        return dNumDoc;
    }

    public void setdNumDoc(short dNumDoc) {
        this.dNumDoc = dNumDoc;
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

    public LocalDate getdFeFinT() {
        return dFeFinT;
    }

    public void setdFeFinT(LocalDate dFeFinT) {
        this.dFeFinT = dFeFinT;
    }
}
