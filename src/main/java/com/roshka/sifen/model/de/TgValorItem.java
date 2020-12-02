package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TTImp;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.model.de.types.TdCondTiCam;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgValorItem {
    private BigDecimal dPUniProSer;
    private BigDecimal dTiCamIt;
    private BigDecimal dTotBruOpeItem;
    private TgValorRestaItem gValorRestaItem;

    public void setupSOAPElements(SOAPElement gCamItem, TTiDE iTiDE, TdCondTiCam dCondTiCam, TTImp iTImp, BigDecimal dCantProSer) throws SOAPException {
        SOAPElement gValorItem = gCamItem.addChildElement("gValorItem");
        gValorItem.addChildElement("dPUniProSer").setTextContent(String.valueOf(this.dPUniProSer));

        if (dCondTiCam != null && dCondTiCam.getVal() == 2)
            gValorItem.addChildElement("dTiCamIt").setTextContent(String.valueOf(this.dTiCamIt));

        this.dTotBruOpeItem = this.dPUniProSer.multiply(dCantProSer);
        gValorItem.addChildElement("dTotBruOpeItem").setTextContent(String.valueOf(this.dTotBruOpeItem));

        this.gValorRestaItem.setupSOAPElements(gValorItem, iTiDE, iTImp, dCondTiCam, this.dTiCamIt, this.dPUniProSer, dCantProSer);
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

    public TgValorRestaItem getgValorRestaItem() {
        return gValorRestaItem;
    }

    public void setgValorRestaItem(TgValorRestaItem gValorRestaItem) {
        this.gValorRestaItem = gValorRestaItem;
    }
}
