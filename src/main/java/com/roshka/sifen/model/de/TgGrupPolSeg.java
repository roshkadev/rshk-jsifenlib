package com.roshka.sifen.model.de;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TgGrupPolSeg {

    private String dPoliza;
    private String dUnidVig;
    private BigDecimal dVigencia;
    private String dNumPoliza;
    private LocalDateTime dFecIniVig;
    private LocalDateTime dFecFinVig;
    private String dCodInt;

    public String getdPoliza() {
        return dPoliza;
    }

    public void setdPoliza(String dPoliza) {
        this.dPoliza = dPoliza;
    }

    public String getdUnidVig() {
        return dUnidVig;
    }

    public void setdUnidVig(String dUnidVig) {
        this.dUnidVig = dUnidVig;
    }

    public BigDecimal getdVigencia() {
        return dVigencia;
    }

    public void setdVigencia(BigDecimal dVigencia) {
        this.dVigencia = dVigencia;
    }

    public String getdNumPoliza() {
        return dNumPoliza;
    }

    public void setdNumPoliza(String dNumPoliza) {
        this.dNumPoliza = dNumPoliza;
    }

    public LocalDateTime getdFecIniVig() {
        return dFecIniVig;
    }

    public void setdFecIniVig(LocalDateTime dFecIniVig) {
        this.dFecIniVig = dFecIniVig;
    }

    public LocalDateTime getdFecFinVig() {
        return dFecFinVig;
    }

    public void setdFecFinVig(LocalDateTime dFecFinVig) {
        this.dFecFinVig = dFecFinVig;
    }

    public String getdCodInt() {
        return dCodInt;
    }

    public void setdCodInt(String dCodInt) {
        this.dCodInt = dCodInt;
    }

}
