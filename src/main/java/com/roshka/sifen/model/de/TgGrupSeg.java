package com.roshka.sifen.model.de;

import java.util.List;

public class TgGrupSeg {

    private String dCodEmpSeg;
    private List<TgGrupPolSeg> gGrupPolSeg;

    public String getdCodEmpSeg() {
        return dCodEmpSeg;
    }

    public void setdCodEmpSeg(String dCodEmpSeg) {
        this.dCodEmpSeg = dCodEmpSeg;
    }

    public List<TgGrupPolSeg> getgGrupPolSeg() {
        return gGrupPolSeg;
    }

    public void setgGrupPolSeg(List<TgGrupPolSeg> gGrupPolSeg) {
        this.gGrupPolSeg = gGrupPolSeg;
    }
}

