package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiCondOpe;

import java.util.List;

public class TgCamCond {

    private TiCondOpe iCondOpe;
    private List<TgPagCont> gPaCondEIni;
    private TgPagCred gPagCred;

    public TiCondOpe getiCondOpe() {
        return iCondOpe;
    }

    public void setiCondOpe(TiCondOpe iCondOpe) {
        this.iCondOpe = iCondOpe;
    }

    public List<TgPagCont> getgPaCondEIni() {
        return gPaCondEIni;
    }

    public void setgPaCondEIni(List<TgPagCont> gPaCondEIni) {
        this.gPaCondEIni = gPaCondEIni;
    }

    public TgPagCred getgPagCred() {
        return gPagCred;
    }

    public void setgPagCred(TgPagCred gPagCred) {
        this.gPagCred = gPagCred;
    }

}
