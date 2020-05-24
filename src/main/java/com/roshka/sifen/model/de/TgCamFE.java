package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiIndPres;

import java.time.LocalDate;

public class TgCamFE {

    private TiIndPres iIndPres;
    private String dDesIndPres;
    private LocalDate dFecEmNR;
    private TgCompPub tgCompPub;

    public TiIndPres getiIndPres() {
        return iIndPres;
    }

    public void setiIndPres(TiIndPres iIndPres) {
        this.iIndPres = iIndPres;
    }

    public String getdDesIndPres() {
        return dDesIndPres;
    }

    public void setdDesIndPres(String dDesIndPres) {
        this.dDesIndPres = dDesIndPres;
    }

    public LocalDate getdFecEmNR() {
        return dFecEmNR;
    }

    public void setdFecEmNR(LocalDate dFecEmNR) {
        this.dFecEmNR = dFecEmNR;
    }

    public TgCompPub getTgCompPub() {
        return tgCompPub;
    }

    public void setTgCompPub(TgCompPub tgCompPub) {
        this.tgCompPub = tgCompPub;
    }
}
