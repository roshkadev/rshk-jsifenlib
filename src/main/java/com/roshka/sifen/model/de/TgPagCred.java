package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
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
    private List<TgCuotas> gCoutasList;

    public void setupSOAPElements(SOAPElement gCamCond) throws SOAPException {
        SOAPElement gPagCred = gCamCond.addChildElement("gPagCred", NamespacesConstants.SIFEN_NS_PREFIX);
        gPagCred.addChildElement("iCondCred", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iCondCred.getVal()));
        gPagCred.addChildElement("dDCondCred", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.iCondCred.getDescripcion());

        if (this.dPlazoCre != null || this.iCondCred.getVal() == 1)
            gPagCred.addChildElement("dPlazoCre", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dPlazoCre);

        if (this.dCuotas != 0 || this.iCondCred.getVal() == 2)
            gPagCred.addChildElement("dCuotas", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dCuotas));

        if (this.dMonEnt != null)
            gPagCred.addChildElement("dMonEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dMonEnt));

        if (this.iCondCred.getVal() == 2) {
            for (TgCuotas gCuotas : gCoutasList) {
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

    public List<TgCuotas> getgCoutasList() {
        return gCoutasList;
    }

    public void setgCoutasList(List<TgCuotas> gCoutasList) {
        this.gCoutasList = gCoutasList;
    }
}
