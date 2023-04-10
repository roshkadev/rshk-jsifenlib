package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.core.types.*;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgOpeCom extends SifenObjectBase {
    private TTipTra iTipTra;
    private TTImp iTImp;
    private CMondT cMoneOpe;
    private TdCondTiCam dCondTiCam; // opcional
    private BigDecimal dTiCam;
    private TiCondAnt iCondAnt;

    public void setupSOAPElements(SOAPElement gDatGralOpe, TTiDE iTiDE) throws SOAPException {
        SOAPElement gOpeCom = gDatGralOpe.addChildElement("gOpeCom");
        if (iTiDE.getVal() == 1 || iTiDE.getVal() == 4) {
            gOpeCom.addChildElement("iTipTra").setTextContent(String.valueOf(this.iTipTra.getVal()));
            gOpeCom.addChildElement("dDesTipTra").setTextContent(this.iTipTra.getDescripcion());
        }

        gOpeCom.addChildElement("iTImp").setTextContent(String.valueOf(this.iTImp.getVal()));
        gOpeCom.addChildElement("dDesTImp").setTextContent(this.iTImp.getDescripcion());
        gOpeCom.addChildElement("cMoneOpe").setTextContent(this.cMoneOpe.name());
        gOpeCom.addChildElement("dDesMoneOpe").setTextContent(this.cMoneOpe.getDescripcion());

        if (!this.cMoneOpe.name().equals("PYG")) {
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

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iTipTra":
                this.iTipTra = TTipTra.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "iTImp":
                this.iTImp = TTImp.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "cMoneOpe":
                this.cMoneOpe = CMondT.getByName(ResponseUtil.getTextValue(value));
                break;
            case "dCondTiCam":
                this.dCondTiCam = TdCondTiCam.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dTiCam":
                this.dTiCam = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "iCondAnt":
                this.iCondAnt = TiCondAnt.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
        }
    }

    public TTipTra getiTipTra() {
        return iTipTra;
    }

    public void setiTipTra(TTipTra iTipTra) {
        this.iTipTra = iTipTra;
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