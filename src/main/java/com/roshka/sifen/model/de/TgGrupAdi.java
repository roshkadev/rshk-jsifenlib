package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TgGrupAdi {
    private String dCiclo;
    private LocalDateTime dFecIniC;
    private LocalDate dFecFinC;
    private List<LocalDate> dVencPagList;
    private String dContrato;
    private BigDecimal dSalAnt;

    public void setupSOAPElements(SOAPElement gCamEsp) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SOAPElement gGrupAdi = gCamEsp.addChildElement("gGrupAdi", NamespacesConstants.SIFEN_NS_PREFIX);
        if (this.dCiclo != null) {
            gGrupAdi.addChildElement("dCiclo", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dCiclo);
            gGrupAdi.addChildElement("dFecIniC", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFecIniC));
            gGrupAdi.addChildElement("dFecFinC", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFecFinC));
        }

        for (LocalDate dVencPag : dVencPagList) {
            gGrupAdi.addChildElement("dVencPag", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(dVencPag));
        }

        if (this.dContrato != null)
            gGrupAdi.addChildElement("dContrato", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dContrato);

        if (this.dSalAnt != null)
            gGrupAdi.addChildElement("dSalAnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dSalAnt));
    }

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
