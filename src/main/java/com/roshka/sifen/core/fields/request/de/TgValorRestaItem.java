package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TTImp;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.core.types.TdCondTiCam;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TgValorRestaItem extends SifenObjectBase {
    private BigDecimal dDescItem;
    private BigDecimal dPorcDesIt;
    private BigDecimal dDescGloItem;
    private BigDecimal dAntPreUniIt;
    private BigDecimal dAntGloPreUniIt;
    private BigDecimal dTotOpeItem;
    private BigDecimal dTotOpeGs;

    public TgValorRestaItem() {
        this.dDescItem = BigDecimal.ZERO;
    }

    public void setupSOAPElements(SOAPElement gValorItem, TTiDE iTiDE, TTImp iTImp, TdCondTiCam dCondTiCam, BigDecimal dTiCamIt,
                                  BigDecimal dPUniProSer, BigDecimal dCantProSer, CMondT cMoneOpe) throws SOAPException {
        SOAPElement gValorRestaItem = gValorItem.addChildElement("gValorRestaItem");

        if (this.dDescItem != null) {
            gValorRestaItem.addChildElement("dDescItem").setTextContent(String.valueOf(this.dDescItem));

            this.dPorcDesIt = this.dDescItem.multiply(BigDecimal.valueOf(100)).divide(dPUniProSer, 2, RoundingMode.HALF_UP);
            gValorRestaItem.addChildElement("dPorcDesIt").setTextContent(String.valueOf(this.dPorcDesIt));
        }

        if (this.dDescGloItem != null)
            gValorRestaItem.addChildElement("dDescGloItem").setTextContent(String.valueOf(this.dDescGloItem));

        gValorRestaItem.addChildElement("dAntPreUniIt").setTextContent(String.valueOf(SifenUtil.coalesce(this.dAntPreUniIt, BigDecimal.ZERO)));
        gValorRestaItem.addChildElement("dAntGloPreUniIt").setTextContent(String.valueOf(SifenUtil.coalesce(this.dAntGloPreUniIt, BigDecimal.ZERO)));

        if (iTiDE.getVal() == 4) {
            this.dTotOpeItem = dPUniProSer.multiply(dCantProSer);
        } else if (iTImp != null && (iTImp.getVal() == 1 || iTImp.getVal() == 3 || iTImp.getVal() == 4 || iTImp.getVal() == 5)) {
            this.dTotOpeItem = (dPUniProSer.subtract(SifenUtil.coalesce(this.dDescItem, BigDecimal.ZERO))
                    .subtract(SifenUtil.coalesce(this.dDescGloItem, BigDecimal.ZERO))
                    .subtract(SifenUtil.coalesce(this.dAntPreUniIt, BigDecimal.ZERO))
                    .subtract(SifenUtil.coalesce(this.dAntGloPreUniIt, BigDecimal.ZERO)))
                    .multiply(dCantProSer);
        }
//        gValorRestaItem.addChildElement("dTotOpeItem").setTextContent(String.valueOf(this.dTotOpeItem));
        //am 22_11
        int scale = cMoneOpe.name().equals("PYG") ? 0 : 2;
        gValorRestaItem.addChildElement("dTotOpeItem").setTextContent(String.valueOf(this.dTotOpeItem.setScale(scale, RoundingMode.HALF_UP)));

        if (dCondTiCam != null && dCondTiCam.getVal() == 2) {
            this.dTotOpeGs = this.dTotOpeItem.multiply(dTiCamIt);
            gValorRestaItem.addChildElement("dTotOpeGs").setTextContent(String.valueOf(this.dTotOpeGs));
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dDescItem":
                this.dDescItem = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dPorcDesIt":
                this.dPorcDesIt = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dDescGloItem":
                this.dDescGloItem = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dAntPreUniIt":
                this.dAntPreUniIt = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dAntGloPreUniIt":
                this.dAntGloPreUniIt = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotOpeItem":
                this.dTotOpeItem = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotOpeGs":
                this.dTotOpeGs = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
        }
    }

    public BigDecimal getdDescItem() {
        return dDescItem;
    }

    public void setdDescItem(BigDecimal dDescItem) {
        this.dDescItem = dDescItem;
    }

    public BigDecimal getdPorcDesIt() {
        return dPorcDesIt;
    }

    public BigDecimal getdDescGloItem() {
        return dDescGloItem;
    }

    public void setdDescGloItem(BigDecimal dDescGloItem) {
        this.dDescGloItem = dDescGloItem;
    }

    public BigDecimal getdAntPreUniIt() {
        return dAntPreUniIt;
    }

    public void setdAntPreUniIt(BigDecimal dAntPreUniIt) {
        this.dAntPreUniIt = dAntPreUniIt;
    }

    public BigDecimal getdAntGloPreUniIt() {
        return dAntGloPreUniIt;
    }

    public void setdAntGloPreUniIt(BigDecimal dAntGloPreUniIt) {
        this.dAntGloPreUniIt = dAntGloPreUniIt;
    }

    public BigDecimal getdTotOpeItem() {
        return dTotOpeItem;
    }

    public BigDecimal getdTotOpeGs() {
        return dTotOpeGs;
    }
}
