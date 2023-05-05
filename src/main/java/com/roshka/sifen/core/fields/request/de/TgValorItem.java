package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TTImp;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.core.types.TdCondTiCam;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TgValorItem extends SifenObjectBase {
    private BigDecimal dPUniProSer;
    private BigDecimal dTiCamIt;
    private BigDecimal dTotBruOpeItem;
    private TgValorRestaItem gValorRestaItem;

    public void setupSOAPElements(SOAPElement gCamItem, TTiDE iTiDE, TdCondTiCam dCondTiCam, TTImp iTImp, BigDecimal dCantProSer, CMondT cMoneOpe) throws SOAPException {
        SOAPElement gValorItem = gCamItem.addChildElement("gValorItem");
        gValorItem.addChildElement("dPUniProSer").setTextContent(String.valueOf(this.dPUniProSer));

        if (dCondTiCam != null && dCondTiCam.getVal() == 2)
            gValorItem.addChildElement("dTiCamIt").setTextContent(String.valueOf(this.dTiCamIt));

        this.dTotBruOpeItem = this.dPUniProSer.multiply(dCantProSer);
        // am2811
        int scale = cMoneOpe.name().equals("PYG") ? 0 : 2;
        this.dTotBruOpeItem = this.dTotBruOpeItem.setScale(scale, RoundingMode.HALF_UP);
        gValorItem.addChildElement("dTotBruOpeItem").setTextContent(String.valueOf(this.dTotBruOpeItem));

        this.gValorRestaItem.setupSOAPElements(gValorItem, iTiDE, iTImp, dCondTiCam, this.dTiCamIt, this.dPUniProSer, dCantProSer, cMoneOpe);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dPUniProSer":
                this.dPUniProSer = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTiCamIt":
                this.dTiCamIt = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotBruOpeItem":
                this.dTotBruOpeItem = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "gValorRestaItem":
                this.gValorRestaItem = SifenObjectFactory.getFromNode(value, TgValorRestaItem.class);
                break;
        }
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
