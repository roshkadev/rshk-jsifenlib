package com.roshka.sifen.model.de;

import com.roshka.sifen.model.monedas.CMondT;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TgCuotas {

    private CMondT cMoneCuo;
    private BigDecimal dMonCuota;
    private LocalDate dVencCuo;

    public CMondT getcMoneCuo() {
        return cMoneCuo;
    }

    public void setcMoneCuo(CMondT cMoneCuo) {
        this.cMoneCuo = cMoneCuo;
    }

    public BigDecimal getdMonCuota() {
        return dMonCuota;
    }

    public void setdMonCuota(BigDecimal dMonCuota) {
        this.dMonCuota = dMonCuota;
    }

    public LocalDate getdVencCuo() {
        return dVencCuo;
    }

    public void setdVencCuo(LocalDate dVencCuo) {
        this.dVencCuo = dVencCuo;
    }
}
