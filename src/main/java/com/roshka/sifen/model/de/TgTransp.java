package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.*;
import com.roshka.sifen.model.paises.PaisType;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDate;
import java.util.List;

public class TgTransp {
    private TiTTrans iTipTrans;
    private TiModTrans iModTrans;
    private TiRespFlete iRespFlete;
    private TcCondNeg cCondNeg;
    private String dNuManif;
    private String dNuDespImp;
    private LocalDate dIniTras;
    private LocalDate dFinTras;
    private PaisType cPaisDest;
    private TgCamSal gCamSal;
    private List<TgCamEnt> gCamEntList;
    private List<TgVehTras> gVehTrasList;
    private TgCamTrans gCamTrans;

    public void setupSOAPElements(SOAPElement gDtipDE, TTiDE iTiDE, TiMotivTras iMotEmiNR) throws SOAPException {
        SOAPElement gTransp = gDtipDE.addChildElement("gTransp");

        if (iTiDE.getVal() == 7 || this.iTipTrans != null) {
            gTransp.addChildElement("iTipTrans").setTextContent(String.valueOf(this.iTipTrans.getVal()));
            gTransp.addChildElement("dDesTipTrans").setTextContent(this.iTipTrans.getDescripcion());
        }

        gTransp.addChildElement("iModTrans").setTextContent(String.valueOf(this.iModTrans.getVal()));
        gTransp.addChildElement("dDesModTrans").setTextContent(this.iModTrans.getDescripcion());
        gTransp.addChildElement("iRespFlete").setTextContent(String.valueOf(this.iRespFlete.getVal()));

        if (this.cCondNeg != null)
            gTransp.addChildElement("cCondNeg").setTextContent(this.cCondNeg.toString());

        if (this.dNuManif != null)
            gTransp.addChildElement("dNuManif").setTextContent(this.dNuManif);

        if (iMotEmiNR.getVal() == 5)
            gTransp.addChildElement("dNuDespImp").setTextContent(this.dNuDespImp);

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() == 1 && this.dIniTras != null)) {
            gTransp.addChildElement("dIniTras").setTextContent(this.dIniTras.toString());
            gTransp.addChildElement("dFinTras").setTextContent(this.dFinTras.toString());
        }

        if (this.cPaisDest != null) {
            gTransp.addChildElement("cPaisDest").setTextContent(this.cPaisDest.toString());
            gTransp.addChildElement("dDesPaisDest").setTextContent(this.cPaisDest.getNombre());
        }

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() == 1 && this.gCamSal != null))
            this.gCamSal.setupSOAPElements(gTransp);

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() != 4 && iTiDE.getVal() != 5 && iTiDE.getVal() != 6 && this.gCamEntList != null)) {
            for (TgCamEnt gCamEnt : gCamEntList) {
                gCamEnt.setupSOAPElements(gTransp);
            }
        }

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() != 4 && iTiDE.getVal() != 5 && iTiDE.getVal() != 6 && this.gVehTrasList != null)) {
            for (TgVehTras gVehTras : gVehTrasList) {
                gVehTras.setupSOAPElements(gTransp, this.iModTrans);
            }
        }

        if (iTiDE.getVal() == 7 || (this.iModTrans.getVal() == 1 && this.gCamTrans != null)) {
            this.gCamTrans.setupSOAPElements(gTransp);
        }
    }

    public TiTTrans getiTipTrans() {
        return iTipTrans;
    }

    public void setiTipTrans(TiTTrans iTipTrans) {
        this.iTipTrans = iTipTrans;
    }

    public TiModTrans getiModTrans() {
        return iModTrans;
    }

    public void setiModTrans(TiModTrans iModTrans) {
        this.iModTrans = iModTrans;
    }

    public TiRespFlete getiRespFlete() {
        return iRespFlete;
    }

    public void setiRespFlete(TiRespFlete iRespFlete) {
        this.iRespFlete = iRespFlete;
    }

    public TcCondNeg getcCondNeg() {
        return cCondNeg;
    }

    public void setcCondNeg(TcCondNeg cCondNeg) {
        this.cCondNeg = cCondNeg;
    }

    public String getdNuManif() {
        return dNuManif;
    }

    public void setdNuManif(String dNuManif) {
        this.dNuManif = dNuManif;
    }

    public String getdNuDespImp() {
        return dNuDespImp;
    }

    public void setdNuDespImp(String dNuDespImp) {
        this.dNuDespImp = dNuDespImp;
    }

    public LocalDate getdIniTras() {
        return dIniTras;
    }

    public void setdIniTras(LocalDate dIniTras) {
        this.dIniTras = dIniTras;
    }

    public LocalDate getdFinTras() {
        return dFinTras;
    }

    public void setdFinTras(LocalDate dFinTras) {
        this.dFinTras = dFinTras;
    }

    public PaisType getcPaisDest() {
        return cPaisDest;
    }

    public void setcPaisDest(PaisType cPaisDest) {
        this.cPaisDest = cPaisDest;
    }

    public TgCamSal getgCamSal() {
        return gCamSal;
    }

    public void setgCamSal(TgCamSal gCamSal) {
        this.gCamSal = gCamSal;
    }

    public List<TgCamEnt> getgCamEntList() {
        return gCamEntList;
    }

    public void setgCamEntList(List<TgCamEnt> gCamEntList) {
        this.gCamEntList = gCamEntList;
    }

    public List<TgVehTras> getgVehTrasList() {
        return gVehTrasList;
    }

    public void setgVehTrasList(List<TgVehTras> gVehTrasList) {
        this.gVehTrasList = gVehTrasList;
    }

    public TgCamTrans getgCamTrans() {
        return gCamTrans;
    }

    public void setgCamTrans(TgCamTrans gCamTrans) {
        this.gCamTrans = gCamTrans;
    }

}
