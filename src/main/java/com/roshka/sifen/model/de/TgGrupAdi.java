package com.roshka.sifen.model.de;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TgGrupAdi {

    private String dCiclo;
    private LocalDateTime dFecIniC;
    private LocalDate dFecFinC;
    private List<LocalDate> dVencPag;
    private String dContrato;
    private BigDecimal dSalAnt;

    public String getdCiclo() {
        return dCiclo;
    }

    public void setdCiclo(String dCiclo) {
        this.dCiclo = dCiclo;
    }

    public LocalDateTime getdFecIniC() {
        return dFecIniC;
    }

    public void setdFecIniC(LocalDateTime dFecIniC) {
        this.dFecIniC = dFecIniC;
    }

    public LocalDate getdFecFinC() {
        return dFecFinC;
    }

    public void setdFecFinC(LocalDate dFecFinC) {
        this.dFecFinC = dFecFinC;
    }

    public List<LocalDate> getdVencPag() {
        return dVencPag;
    }

    public void setdVencPag(List<LocalDate> dVencPag) {
        this.dVencPag = dVencPag;
    }

    public String getdContrato() {
        return dContrato;
    }

    public void setdContrato(String dContrato) {
        this.dContrato = dContrato;
    }

    public BigDecimal getdSalAnt() {
        return dSalAnt;
    }

    public void setdSalAnt(BigDecimal dSalAnt) {
        this.dSalAnt = dSalAnt;
    }

}
