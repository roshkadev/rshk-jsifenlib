package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TcCondNeg;
import com.roshka.sifen.model.de.types.TiModTrans;
import com.roshka.sifen.model.de.types.TiRespFlete;
import com.roshka.sifen.model.de.types.TiTTrans;
import com.roshka.sifen.model.paises.PaisType;

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
    private List<TgCamEnt> gCamEnt;
    private List<TgVehTras> gVehTras;
    private TgCamTrans gCamTrans;

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

    public List<TgCamEnt> getgCamEnt() {
        return gCamEnt;
    }

    public void setgCamEnt(List<TgCamEnt> gCamEnt) {
        this.gCamEnt = gCamEnt;
    }

    public List<TgVehTras> getgVehTras() {
        return gVehTras;
    }

    public void setgVehTras(List<TgVehTras> gVehTras) {
        this.gVehTras = gVehTras;
    }

    public TgCamTrans getgCamTrans() {
        return gCamTrans;
    }

    public void setgCamTrans(TgCamTrans gCamTrans) {
        this.gCamTrans = gCamTrans;
    }

}
