package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.core.types.TiAfecIVA;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TgCamIVA extends SifenObjectBase {
    private TiAfecIVA iAfecIVA;
    private BigDecimal dPropIVA;
    private BigDecimal dTasaIVA;
    private BigDecimal dBasGravIVA;
    private BigDecimal dLiqIVAItem;

    public void setupSOAPElements(SOAPElement gCamItem, CMondT cMoneOpe, BigDecimal dTotOpeItem) throws SOAPException {
        SOAPElement gCamIVA = gCamItem.addChildElement("gCamIVA");
        gCamIVA.addChildElement("iAfecIVA").setTextContent(String.valueOf(this.iAfecIVA.getVal()));
        gCamIVA.addChildElement("dDesAfecIVA").setTextContent(this.iAfecIVA.getDescripcion());
        gCamIVA.addChildElement("dPropIVA").setTextContent(String.valueOf(this.dPropIVA));
        gCamIVA.addChildElement("dTasaIVA").setTextContent(String.valueOf(this.dTasaIVA));

        if (this.iAfecIVA.getVal() == 1 || this.iAfecIVA.getVal() == 4) {
            int scale = cMoneOpe.name().equals("PYG") ? 0 : 2;
            if (this.dTasaIVA.equals(BigDecimal.valueOf(10))) {
                this.dBasGravIVA = dTotOpeItem.multiply(this.dPropIVA.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)).divide(BigDecimal.valueOf(1.1), scale, RoundingMode.HALF_UP);
            } else if (this.dTasaIVA.equals(BigDecimal.valueOf(5))) {
                this.dBasGravIVA = dTotOpeItem.multiply(this.dPropIVA.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)).divide(BigDecimal.valueOf(1.05), scale, RoundingMode.HALF_UP);
            }

            this.dLiqIVAItem = this.dBasGravIVA.multiply(this.dTasaIVA.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)).setScale(0, RoundingMode.HALF_UP);
        } else {
            this.dBasGravIVA = BigDecimal.ZERO;
            this.dLiqIVAItem = BigDecimal.ZERO;
        }

        gCamIVA.addChildElement("dBasGravIVA").setTextContent(String.valueOf(this.dBasGravIVA));
        gCamIVA.addChildElement("dLiqIVAItem").setTextContent(String.valueOf(this.dLiqIVAItem));
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iAfecIVA":
                this.iAfecIVA = TiAfecIVA.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dPropIVA":
                this.dPropIVA = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTasaIVA":
                this.dTasaIVA = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dBasGravIVA":
                this.dBasGravIVA = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dLiqIVAItem":
                this.dLiqIVAItem = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
        }
    }

    public TiAfecIVA getiAfecIVA() {
        return iAfecIVA;
    }

    public void setiAfecIVA(TiAfecIVA iAfecIVA) {
        this.iAfecIVA = iAfecIVA;
    }

    public BigDecimal getdPropIVA() {
        return dPropIVA;
    }

    public void setdPropIVA(BigDecimal dPropIVA) {
        this.dPropIVA = dPropIVA;
    }

    public BigDecimal getdTasaIVA() {
        return dTasaIVA;
    }

    public void setdTasaIVA(BigDecimal dTasaIVA) {
        this.dTasaIVA = dTasaIVA;
    }

    public BigDecimal getdBasGravIVA() {
        return dBasGravIVA;
    }

    public BigDecimal getdLiqIVAItem() {
        return dLiqIVAItem;
    }
}
