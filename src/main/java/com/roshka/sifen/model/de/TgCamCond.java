package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TiCondOpe;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.List;

public class TgCamCond {
    private TiCondOpe iCondOpe;
    private List<TgPagCont> gPaCondEIniList;
    private TgPagCred gPagCred;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SOAPElement gCamCond = gDtipDE.addChildElement("gCamCond", Constants.SIFEN_NS_PREFIX);
        gCamCond.addChildElement("iCondOpe", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iCondOpe.getVal()));
        gCamCond.addChildElement("dDCondOpe", Constants.SIFEN_NS_PREFIX).setTextContent(this.iCondOpe.getDescripcion());

        if (gPaCondEIniList.size() > 0 || this.iCondOpe.getVal() == 1 || this.gPagCred.getdMonEnt() != null) {
            for (TgPagCont gPaConEIni : gPaCondEIniList) {
                gPaConEIni.setupSOAPElements(gCamCond);
            }
        }

        if (this.iCondOpe.getVal() == 2)
            this.gPagCred.setupSOAPElements(gCamCond);
    }

    public TiCondOpe getiCondOpe() {
        return iCondOpe;
    }

    public void setiCondOpe(TiCondOpe iCondOpe) {
        this.iCondOpe = iCondOpe;
    }

    public TgPagCred getgPagCred() {
        return gPagCred;
    }

    public void setgPagCred(TgPagCred gPagCred) {
        this.gPagCred = gPagCred;
    }

    public List<TgPagCont> getgPaCondEIniList() {
        return gPaCondEIniList;
    }

    public void setgPaCondEIniList(List<TgPagCont> gPaCondEIniList) {
        this.gPaCondEIniList = gPaCondEIniList;
    }
}
