package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiDenTarj;
import com.roshka.sifen.model.de.types.TiForProPa;

public class TgPagTarCD {

    private TiDenTarj iDenTarj;
    private String dDesDenTarj;
    private String dRSProTar;
    private String dRUCProTar;
    private Short dDVProTar;
    private TiForProPa iForProPa;
    private Integer dCodAuOpe;
    private String dNomTit;
    private Short dNumTarj;

    public TiDenTarj getiDenTarj() {
        return iDenTarj;
    }

    public void setiDenTarj(TiDenTarj iDenTarj) {
        this.iDenTarj = iDenTarj;
    }

    public String getdDesDenTarj() {
        return dDesDenTarj;
    }

    public void setdDesDenTarj(String dDesDenTarj) {
        this.dDesDenTarj = dDesDenTarj;
    }

    public String getdRSProTar() {
        return dRSProTar;
    }

    public void setdRSProTar(String dRSProTar) {
        this.dRSProTar = dRSProTar;
    }

    public String getdRUCProTar() {
        return dRUCProTar;
    }

    public void setdRUCProTar(String dRUCProTar) {
        this.dRUCProTar = dRUCProTar;
    }

    public Short getdDVProTar() {
        return dDVProTar;
    }

    public void setdDVProTar(Short dDVProTar) {
        this.dDVProTar = dDVProTar;
    }

    public TiForProPa getiForProPa() {
        return iForProPa;
    }

    public void setiForProPa(TiForProPa iForProPa) {
        this.iForProPa = iForProPa;
    }

    public Integer getdCodAuOpe() {
        return dCodAuOpe;
    }

    public void setdCodAuOpe(Integer dCodAuOpe) {
        this.dCodAuOpe = dCodAuOpe;
    }

    public String getdNomTit() {
        return dNomTit;
    }

    public void setdNomTit(String dNomTit) {
        this.dNomTit = dNomTit;
    }

    public Short getdNumTarj() {
        return dNumTarj;
    }

    public void setdNumTarj(Short dNumTarj) {
        this.dNumTarj = dNumTarj;
    }
}
