package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TTipEmi;

public class TgCOpeDE {

    private TTipEmi iTipEmi;
    private int dCodSeg;            // número aleatorio entre 1 y 999999999 generado por el emisor
    private String dInfoEmi;        // información de interés del emisor (opcional). Entre 1 y 3000 carácteres
    private String dInfoFisc;       // información de interés del fisco (opcional). Entre 1 y 3000 carácteres

    public TTipEmi getiTipEmi() {
        return iTipEmi;
    }

    public void setiTipEmi(TTipEmi iTipEmi) {
        this.iTipEmi = iTipEmi;
    }

    public int getdCodSeg() {
        return dCodSeg;
    }

    public void setdCodSeg(int dCodSeg) {
        this.dCodSeg = dCodSeg;
    }

    public String getdInfoEmi() {
        return dInfoEmi;
    }

    public void setdInfoEmi(String dInfoEmi) {
        this.dInfoEmi = dInfoEmi;
    }

    public String getdInfoFisc() {
        return dInfoFisc;
    }

    public void setdInfoFisc(String dInfoFisc) {
        this.dInfoFisc = dInfoFisc;
    }
}
