package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiTipCom;
import com.roshka.sifen.model.de.types.TiTipOpVN;

import java.math.BigDecimal;

public class TgVehNuevo {

    private TiTipOpVN iTipOpVN;
    private String dChasis;
    private String dColor;
    private Short dPotencia;
    private Short dCapMot;
    private BigDecimal dpNet;
    private BigDecimal dpBruto;
    private TiTipCom iTipCom;
    private String dDesTipCom;
    private String dNroMotor;
    private BigDecimal dCapTracc;
    private Short dAnoFab;
    private String cTipVeh;
    private Short dCapac;
    private String dCilin;

    public TiTipOpVN getiTipOpVN() {
        return iTipOpVN;
    }

    public void setiTipOpVN(TiTipOpVN iTipOpVN) {
        this.iTipOpVN = iTipOpVN;
    }

    public String getdChasis() {
        return dChasis;
    }

    public void setdChasis(String dChasis) {
        this.dChasis = dChasis;
    }

    public String getdColor() {
        return dColor;
    }

    public void setdColor(String dColor) {
        this.dColor = dColor;
    }

    public Short getdPotencia() {
        return dPotencia;
    }

    public void setdPotencia(Short dPotencia) {
        this.dPotencia = dPotencia;
    }

    public Short getdCapMot() {
        return dCapMot;
    }

    public void setdCapMot(Short dCapMot) {
        this.dCapMot = dCapMot;
    }

    public BigDecimal getDpNet() {
        return dpNet;
    }

    public void setDpNet(BigDecimal dpNet) {
        this.dpNet = dpNet;
    }

    public BigDecimal getDpBruto() {
        return dpBruto;
    }

    public void setDpBruto(BigDecimal dpBruto) {
        this.dpBruto = dpBruto;
    }

    public TiTipCom getiTipCom() {
        return iTipCom;
    }

    public void setiTipCom(TiTipCom iTipCom) {
        this.iTipCom = iTipCom;
    }

    public String getdDesTipCom() {
        return dDesTipCom;
    }

    public void setdDesTipCom(String dDesTipCom) {
        this.dDesTipCom = dDesTipCom;
    }

    public String getdNroMotor() {
        return dNroMotor;
    }

    public void setdNroMotor(String dNroMotor) {
        this.dNroMotor = dNroMotor;
    }

    public BigDecimal getdCapTracc() {
        return dCapTracc;
    }

    public void setdCapTracc(BigDecimal dCapTracc) {
        this.dCapTracc = dCapTracc;
    }

    public Short getdAnoFab() {
        return dAnoFab;
    }

    public void setdAnoFab(Short dAnoFab) {
        this.dAnoFab = dAnoFab;
    }

    public String getcTipVeh() {
        return cTipVeh;
    }

    public void setcTipVeh(String cTipVeh) {
        this.cTipVeh = cTipVeh;
    }

    public Short getdCapac() {
        return dCapac;
    }

    public void setdCapac(Short dCapac) {
        this.dCapac = dCapac;
    }

    public String getdCilin() {
        return dCilin;
    }

    public void setdCilin(String dCilin) {
        this.dCilin = dCilin;
    }

}
