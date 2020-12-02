package com.roshka.sifen.model.de;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TgGrupAdi {
    private String dCiclo;
    private LocalDate dFecIniC;
    private LocalDate dFecFinC;
    private List<LocalDate> dVencPagList;
    private String dContrato;
    private BigDecimal dSalAnt;

    public void setupSOAPElements(SOAPElement gCamEsp) throws SOAPException {
        SOAPElement gGrupAdi = gCamEsp.addChildElement("gGrupAdi");
        if (this.dCiclo != null) {
            gGrupAdi.addChildElement("dCiclo").setTextContent(this.dCiclo);
            gGrupAdi.addChildElement("dFecIniC").setTextContent(this.dFecIniC.toString());
            gGrupAdi.addChildElement("dFecFinC").setTextContent(this.dFecFinC.toString());
        }

        if (dVencPagList != null) {
            for (LocalDate dVencPag : dVencPagList) {
                gGrupAdi.addChildElement("dVencPag").setTextContent(dVencPag.toString());
            }
        }

        if (this.dContrato != null)
            gGrupAdi.addChildElement("dContrato").setTextContent(this.dContrato);

        if (this.dSalAnt != null)
            gGrupAdi.addChildElement("dSalAnt").setTextContent(String.valueOf(this.dSalAnt));
    }

    public String getdCiclo() {
        return dCiclo;
    }

    public void setdCiclo(String dCiclo) {
        this.dCiclo = dCiclo;
    }

    public LocalDate getdFecIniC() {
        return dFecIniC;
    }

    public void setdFecIniC(LocalDate dFecIniC) {
        this.dFecIniC = dFecIniC;
    }

    public LocalDate getdFecFinC() {
        return dFecFinC;
    }

    public void setdFecFinC(LocalDate dFecFinC) {
        this.dFecFinC = dFecFinC;
    }

    public List<LocalDate> getdVencPagList() {
        return dVencPagList;
    }

    public void setdVencPagList(List<LocalDate> dVencPagList) {
        this.dVencPagList = dVencPagList;
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
