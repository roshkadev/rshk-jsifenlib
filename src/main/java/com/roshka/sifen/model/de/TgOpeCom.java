package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.*;
import com.roshka.sifen.model.monedas.CMondT;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgOpeCom {
    private TTipTra tipTra;
    private TTImp iTImp;
    private CMondT cMoneOpe;
    private TdCondTiCam dCondTiCam; // opcional
    private BigDecimal dTiCam;
    private TiCondAnt iCondAnt;

    public void setupSOAPElements(SOAPElement gDatGralOpe, TTiDE iTiDE) throws SOAPException {
        SOAPElement gOpeCom = gDatGralOpe.addChildElement("gOpeCom");
        if (iTiDE.getVal() == 1 || iTiDE.getVal() == 4) {
            gOpeCom.addChildElement("iTipTra").setTextContent(String.valueOf(this.tipTra.getVal()));
            gOpeCom.addChildElement("dDesTipTra").setTextContent(this.tipTra.getDescripcion());
        }

        gOpeCom.addChildElement("iTImp").setTextContent(String.valueOf(this.iTImp.getVal()));
        gOpeCom.addChildElement("dDesTImp").setTextContent(this.iTImp.getDescripcion());
        gOpeCom.addChildElement("cMoneOpe").setTextContent(this.cMoneOpe.toString());
        gOpeCom.addChildElement("dDesMoneOpe").setTextContent(this.cMoneOpe.getDescripcion());

        if (!this.cMoneOpe.toString().equals("PYG")) {
            gOpeCom.addChildElement("dCondTiCam").setTextContent(String.valueOf(this.dCondTiCam.getVal()));
            if (this.dCondTiCam.getVal() == 1) {
                gOpeCom.addChildElement("dTiCam").setTextContent(String.valueOf(this.dTiCam));
            }
        }

        if (this.iCondAnt != null) {
            gOpeCom.addChildElement("iCondAnt").setTextContent(String.valueOf(this.iCondAnt.getVal()));
            gOpeCom.addChildElement("dDesCondAnt").setTextContent(this.iCondAnt.getDescripcion());
        }
    }

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
