package com.roshka.sifen.model.de;

import java.math.BigDecimal;

public class TgGrupEner {

    private String dNroMed;
    private Short dActiv;
    private String dCateg;
    private BigDecimal dLecAnt;
    private BigDecimal dLecAct;
    private BigDecimal dConKwh;

    public String getdNroMed() {
        return dNroMed;
    }

    public void setdNroMed(String dNroMed) {
        this.dNroMed = dNroMed;
    }

    public Short getdActiv() {
        return dActiv;
    }

    public void setdActiv(Short dActiv) {
        this.dActiv = dActiv;
    }

    public String getdCateg() {
        return dCateg;
    }

    public void setdCateg(String dCateg) {
        this.dCateg = dCateg;
    }

    public BigDecimal getdLecAnt() {
        return dLecAnt;
    }

    public void setdLecAnt(BigDecimal dLecAnt) {
        this.dLecAnt = dLecAnt;
    }

    public BigDecimal getdLecAct() {
        return dLecAct;
    }

    public void setdLecAct(BigDecimal dLecAct) {
        this.dLecAct = dLecAct;
    }

    public BigDecimal getdConKwh() {
        return dConKwh;
    }

    public void setdConKwh(BigDecimal dConKwh) {
        this.dConKwh = dConKwh;
    }

}
