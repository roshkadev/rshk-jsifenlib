package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiMotivTras;
import com.roshka.sifen.model.de.types.TiRespEmiNR;

import java.time.LocalDate;

/**
 * Campos de la nota de remisión electrónica
 */
public class TgCamNRE {

    private TiMotivTras iMotEmiNR;
    private TiRespEmiNR irespEmiNR;
    private Integer dKmR;
    private LocalDate dFecEm;

    public TiMotivTras getiMotEmiNR() {
        return iMotEmiNR;
    }

    public void setiMotEmiNR(TiMotivTras iMotEmiNR) {
        this.iMotEmiNR = iMotEmiNR;
    }

    public TiRespEmiNR getIrespEmiNR() {
        return irespEmiNR;
    }

    public void setIrespEmiNR(TiRespEmiNR irespEmiNR) {
        this.irespEmiNR = irespEmiNR;
    }

    public Integer getdKmR() {
        return dKmR;
    }

    public void setdKmR(Integer dKmR) {
        this.dKmR = dKmR;
    }

    public LocalDate getdFecEm() {
        return dFecEm;
    }

    public void setdFecEm(LocalDate dFecEm) {
        this.dFecEm = dFecEm;
    }

}
