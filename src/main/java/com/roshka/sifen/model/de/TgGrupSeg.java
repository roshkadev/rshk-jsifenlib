package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.List;

public class TgGrupSeg {
    private String dCodEmpSeg;
    private List<TgGrupPolSeg> gGrupPolSegList;

    public void setupSOAPElements(SOAPElement gCamEsp) throws SOAPException {
        SOAPElement gGrupSeg = gCamEsp.addChildElement("gGrupSeg", NamespacesConstants.SIFEN_NS_PREFIX);
        if (this.dCodEmpSeg != null)
            gGrupSeg.addChildElement("dCodEmpSeg", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dCodEmpSeg);

        for (TgGrupPolSeg gGrupPolSeg : gGrupPolSegList) {
            gGrupPolSeg.setupSOAPElements(gGrupSeg);
        }
    }

    public String getdCodEmpSeg() {
        return dCodEmpSeg;
    }

    public void setdCodEmpSeg(String dCodEmpSeg) {
        this.dCodEmpSeg = dCodEmpSeg;
    }

    public List<TgGrupPolSeg> getgGrupPolSegList() {
        return gGrupPolSegList;
    }

    public void setgGrupPolSegList(List<TgGrupPolSeg> gGrupPolSegList) {
        this.gGrupPolSegList = gGrupPolSegList;
    }
}

