package com.roshka.sifen.model.de;

import java.time.LocalDateTime;

public class TgDaGOC {

    private LocalDateTime dFeEmiDE;
    private TGOpeCom gOpeCom;
    private TgEmis gEmis;
    private TgDatRec gDatRec;

    public LocalDateTime getdFeEmiDE() {
        return dFeEmiDE;
    }

    public void setdFeEmiDE(LocalDateTime dFeEmiDE) {
        this.dFeEmiDE = dFeEmiDE;
    }

    public TGOpeCom getgOpeCom() {
        return gOpeCom;
    }

    public void setgOpeCom(TGOpeCom gOpeCom) {
        this.gOpeCom = gOpeCom;
    }

    public TgEmis getgEmis() {
        return gEmis;
    }

    public void setgEmis(TgEmis gEmis) {
        this.gEmis = gEmis;
    }

    public TgDatRec getgDatRec() {
        return gDatRec;
    }

    public void setgDatRec(TgDatRec gDatRec) {
        this.gDatRec = gDatRec;
    }

}
