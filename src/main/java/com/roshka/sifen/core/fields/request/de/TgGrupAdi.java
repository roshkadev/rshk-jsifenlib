package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TgGrupAdi extends SifenObjectBase {
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

        if (this.dVencPagList != null) {
            for (LocalDate dVencPag : this.dVencPagList) {
                gGrupAdi.addChildElement("dVencPag").setTextContent(dVencPag.toString());
            }
        }

        if (this.dContrato != null)
            gGrupAdi.addChildElement("dContrato").setTextContent(this.dContrato);

        if (this.dSalAnt != null)
            gGrupAdi.addChildElement("dSalAnt").setTextContent(String.valueOf(this.dSalAnt));
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dCiclo":
                this.dCiclo = ResponseUtil.getTextValue(value);
                break;
            case "dFecIniC":
                this.dFecIniC = ResponseUtil.getDateValue(value);
                break;
            case "dFecFinC":
                this.dFecFinC = ResponseUtil.getDateValue(value);
                break;
            case "dVencPag":
                if (this.dVencPagList == null) {
                    this.dVencPagList = new ArrayList<>();
                }
                this.dVencPagList.add(ResponseUtil.getDateValue(value));
                break;
            case "dContrato":
                this.dContrato = ResponseUtil.getTextValue(value);
                break;
            case "dSalAnt":
                this.dSalAnt = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
        }
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