package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TdTipCons;
import com.roshka.sifen.model.de.types.TiTIpoDoc;
import com.roshka.sifen.model.de.types.TiTipDocAso;

import java.math.BigInteger;
import java.time.LocalDate;

public class TgCamDEAsoc {

    private TiTipDocAso iTipDocAso;
    private String dCdCDERef;
    private String dNTimDI;
    private String dEstDocAso;
    private String dPExpDocAso;
    private String dNumDocAso;
    private TiTIpoDoc iTipoDocAso;
    private LocalDate dFecEmiDI;
    private String dNumComRet;
    private String dNumResCF;
    private TdTipCons iTipCons;
    private BigInteger dNumCons;
    private String dNumControl;

    public TiTipDocAso getiTipDocAso() {
        return iTipDocAso;
    }

    public void setiTipDocAso(TiTipDocAso iTipDocAso) {
        this.iTipDocAso = iTipDocAso;
    }

    public String getdCdCDERef() {
        return dCdCDERef;
    }

    public void setdCdCDERef(String dCdCDERef) {
        this.dCdCDERef = dCdCDERef;
    }

    public String getdNTimDI() {
        return dNTimDI;
    }

    public void setdNTimDI(String dNTimDI) {
        this.dNTimDI = dNTimDI;
    }

    public String getdEstDocAso() {
        return dEstDocAso;
    }

    public void setdEstDocAso(String dEstDocAso) {
        this.dEstDocAso = dEstDocAso;
    }

    public String getdPExpDocAso() {
        return dPExpDocAso;
    }

    public void setdPExpDocAso(String dPExpDocAso) {
        this.dPExpDocAso = dPExpDocAso;
    }

    public String getdNumDocAso() {
        return dNumDocAso;
    }

    public void setdNumDocAso(String dNumDocAso) {
        this.dNumDocAso = dNumDocAso;
    }

    public TiTIpoDoc getiTipoDocAso() {
        return iTipoDocAso;
    }

    public void setiTipoDocAso(TiTIpoDoc iTipoDocAso) {
        this.iTipoDocAso = iTipoDocAso;
    }

    public LocalDate getdFecEmiDI() {
        return dFecEmiDI;
    }

    public void setdFecEmiDI(LocalDate dFecEmiDI) {
        this.dFecEmiDI = dFecEmiDI;
    }

    public String getdNumComRet() {
        return dNumComRet;
    }

    public void setdNumComRet(String dNumComRet) {
        this.dNumComRet = dNumComRet;
    }

    public String getdNumResCF() {
        return dNumResCF;
    }

    public void setdNumResCF(String dNumResCF) {
        this.dNumResCF = dNumResCF;
    }

    public TdTipCons getiTipCons() {
        return iTipCons;
    }

    public void setiTipCons(TdTipCons iTipCons) {
        this.iTipCons = iTipCons;
    }

    public BigInteger getdNumCons() {
        return dNumCons;
    }

    public void setdNumCons(BigInteger dNumCons) {
        this.dNumCons = dNumCons;
    }

    public String getdNumControl() {
        return dNumControl;
    }

    public void setdNumControl(String dNumControl) {
        this.dNumControl = dNumControl;
    }

}
