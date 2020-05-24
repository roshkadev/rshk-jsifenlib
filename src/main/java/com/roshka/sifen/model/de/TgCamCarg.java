package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.extra.TiCarCarga;
import com.roshka.sifen.model.unidades_medida.TcUniMed;

import java.math.BigInteger;

public class TgCamCarg {

    private TcUniMed cUniMedTotVol;
    private BigInteger dTotVolMerc;
    private TcUniMed cUniMedTotPes;
    private BigInteger dTotPesMerc;
    private TiCarCarga iCarCarga;
    private String dDesCarCarga;

    public TcUniMed getcUniMedTotVol() {
        return cUniMedTotVol;
    }

    public void setcUniMedTotVol(TcUniMed cUniMedTotVol) {
        this.cUniMedTotVol = cUniMedTotVol;
    }

    public BigInteger getdTotVolMerc() {
        return dTotVolMerc;
    }

    public void setdTotVolMerc(BigInteger dTotVolMerc) {
        this.dTotVolMerc = dTotVolMerc;
    }

    public TcUniMed getcUniMedTotPes() {
        return cUniMedTotPes;
    }

    public void setcUniMedTotPes(TcUniMed cUniMedTotPes) {
        this.cUniMedTotPes = cUniMedTotPes;
    }

    public BigInteger getdTotPesMerc() {
        return dTotPesMerc;
    }

    public void setdTotPesMerc(BigInteger dTotPesMerc) {
        this.dTotPesMerc = dTotPesMerc;
    }

    public TiCarCarga getiCarCarga() {
        return iCarCarga;
    }

    public void setiCarCarga(TiCarCarga iCarCarga) {
        this.iCarCarga = iCarCarga;
    }

    public String getdDesCarCarga() {
        return dDesCarCarga;
    }

    public void setdDesCarCarga(String dDesCarCarga) {
        this.dDesCarCarga = dDesCarCarga;
    }

}
