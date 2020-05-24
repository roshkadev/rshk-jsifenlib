package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiCondCred;

import java.math.BigDecimal;
import java.util.List;

public class TgPagCred {

    private TiCondCred iCondCred;
    private String dPlazoCre;
    private Short dCuotas;
    private BigDecimal dMonEnt;
    private List<TgCuotas> gCoutas;

    public TiCondCred getiCondCred() {
        return iCondCred;
    }

    public void setiCondCred(TiCondCred iCondCred) {
        this.iCondCred = iCondCred;
    }

    public String getdPlazoCre() {
        return dPlazoCre;
    }

    public void setdPlazoCre(String dPlazoCre) {
        this.dPlazoCre = dPlazoCre;
    }

    public Short getdCuotas() {
        return dCuotas;
    }

    public void setdCuotas(Short dCuotas) {
        this.dCuotas = dCuotas;
    }

    public BigDecimal getdMonEnt() {
        return dMonEnt;
    }

    public void setdMonEnt(BigDecimal dMonEnt) {
        this.dMonEnt = dMonEnt;
    }

    public List<TgCuotas> getgCoutas() {
        return gCoutas;
    }

    public void setgCoutas(List<TgCuotas> gCoutas) {
        this.gCoutas = gCoutas;
    }
}
