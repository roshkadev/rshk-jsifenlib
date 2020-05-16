package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TTiDE;

import java.time.LocalDate;

public class TgDTim {

    private TTiDE tiDE;         // este campo engloba a iTiDE y a dDesTiDE
    private int dNumTim;        // número de timbrado
    private short dEst;         // código de establecimiento: patron ej: 001
    private short dPunExp;      // punto de expedición: patron ej: 001
    private short dNumDoc;      // número de documento: patron ej: 0192312
    private String dSerieNum;   // número de serie del timbrado (opcional)
    private LocalDate dFeIniT;  // fecha de inicio de vigencia del timbrado

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
}
