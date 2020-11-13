package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TdCondTiCam;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgValorItem {
    private BigDecimal dPUniProSer;
    private BigDecimal dTiCamIt;
    private BigDecimal dTotBruOpeItem;
    private TgValorRestaItem gValorRestaItem;

    public void setupSOAPElements(SOAPElement gCamItem, TdCondTiCam dCondTiCam) throws SOAPException {
        SOAPElement gValorItem = gCamItem.addChildElement("gValorItem", Constants.SIFEN_NS_PREFIX);
        gValorItem.addChildElement("dPUniProSer", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dPUniProSer));

        if (dCondTiCam.getVal() == 2)
            gValorItem.addChildElement("dTiCamIt", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTiCamIt));

        gValorItem.addChildElement("dTotBruOpeItem", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotBruOpeItem));

        this.gValorRestaItem.setupSOAPElements(gValorItem, this.dTiCamIt);
    }

    public BigDecimal getdPUniProSer() {
        return dPUniProSer;
    }

    public void setdPUniProSer(BigDecimal dPUniProSer) {
        this.dPUniProSer = dPUniProSer;
    }

    public BigDecimal getdTiCamIt() {
        return dTiCamIt;
    }

    public void setdTiCamIt(BigDecimal dTiCamIt) {
        this.dTiCamIt = dTiCamIt;
    }

    public BigDecimal getdTotBruOpeItem() {
        return dTotBruOpeItem;
    }

    public void setdTotBruOpeItem(BigDecimal dTotBruOpeItem) {
        this.dTotBruOpeItem = dTotBruOpeItem;
    }

    public TgValorRestaItem getgValorRestaItem() {
        return gValorRestaItem;
    }

    public void setgValorRestaItem(TgValorRestaItem gValorRestaItem) {
        this.gValorRestaItem = gValorRestaItem;
    }
}
