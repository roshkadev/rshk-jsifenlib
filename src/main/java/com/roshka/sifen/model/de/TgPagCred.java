package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiCondCred;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.util.List;

public class TgPagCred {
    private TiCondCred iCondCred;
    private String dPlazoCre;
    private short dCuotas;
    private BigDecimal dMonEnt;
    private List<TgCuotas> gCuotasList;

    public void setupSOAPElements(SOAPElement gCamCond) throws SOAPException {
        SOAPElement gPagCred = gCamCond.addChildElement("gPagCred");
        gPagCred.addChildElement("iCondCred").setTextContent(String.valueOf(this.iCondCred.getVal()));
        gPagCred.addChildElement("dDCondCred").setTextContent(this.iCondCred.getDescripcion());

        if (this.dPlazoCre != null || this.iCondCred.getVal() == 1)
            gPagCred.addChildElement("dPlazoCre").setTextContent(this.dPlazoCre);

        if (this.dCuotas != 0 || this.iCondCred.getVal() == 2)
            gPagCred.addChildElement("dCuotas").setTextContent(String.valueOf(this.dCuotas));

        if (this.dMonEnt != null)
            gPagCred.addChildElement("dMonEnt").setTextContent(String.valueOf(this.dMonEnt));

        if (this.gCuotasList != null) {
            for (TgCuotas gCuotas : this.gCuotasList) {
                gCuotas.setupSOAPElements(gPagCred);
            }
        }
    }

    public TiCondCred getiCondCred() {
        return iCondCred;
    }

    public void setiCondCred(TiCondCred iCondCred) {
        this.iCondCred = iCondCred;
    }

    public String getdPlazoCre() {
        return dPlazoCre;
    }

    public void setdPlazoCre(String dPlazoCre) {
        this.dPlazoCre = dPlazoCre;
    }

    public short getdCuotas() {
        return dCuotas;
    }

    public void setdCuotas(short dCuotas) {
        this.dCuotas = dCuotas;
    }

    public BigDecimal getdMonEnt() {
        return dMonEnt;
    }

    public void setdMonEnt(BigDecimal dMonEnt) {
        this.dMonEnt = dMonEnt;
    }

    public List<TgCuotas> getgCuotasList() {
        return gCuotasList;
    }

    public void setgCuotasList(List<TgCuotas> gCuotasList) {
        this.gCuotasList = gCuotasList;
    }
}
