package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.*;

import java.math.BigDecimal;

public class TGOpeCom {

    private TTipTra tipTra;
    private TTImp iTImp;
    private CMondT cMoneOpe;
    private TdCondTiCam dCondTiCam; // opcional
    private BigDecimal dTiCam;
    private TiCondAnt iCondAnt;

    public TTipTra getTipTra() {
        return tipTra;
    }

    public void setTipTra(TTipTra tipTra) {
        this.tipTra = tipTra;
    }

    public TTImp getiTImp() {
        return iTImp;
    }

    public void setiTImp(TTImp iTImp) {
        this.iTImp = iTImp;
    }

    public CMondT getcMoneOpe() {
        return cMoneOpe;
    }

    public void setcMoneOpe(CMondT cMoneOpe) {
        this.cMoneOpe = cMoneOpe;
    }

    public TdCondTiCam getdCondTiCam() {
        return dCondTiCam;
    }

    public void setdCondTiCam(TdCondTiCam dCondTiCam) {
        this.dCondTiCam = dCondTiCam;
    }

    public BigDecimal getdTiCam() {
        return dTiCam;
    }

    public void setdTiCam(BigDecimal dTiCam) {
        this.dTiCam = dTiCam;
    }

    public TiCondAnt getiCondAnt() {
        return iCondAnt;
    }

    public void setiCondAnt(TiCondAnt iCondAnt) {
        this.iCondAnt = iCondAnt;
    }

}
