package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.*;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TgCamIVA {
    private TiAfecIVA iAfecIVA;
    private BigDecimal dPropIVA;
    private BigDecimal dTasaIVA;
    private BigDecimal dBasGravIVA;
    private BigDecimal dLiqIVAItem;

    public void setupSOAPElements(SOAPElement gCamItem, BigDecimal dTotOpeItem) throws SOAPException {
        SOAPElement gCamIVA = gCamItem.addChildElement("gCamIVA");
        gCamIVA.addChildElement("iAfecIVA").setTextContent(String.valueOf(this.iAfecIVA.getVal()));
        gCamIVA.addChildElement("dDesAfecIVA").setTextContent(this.iAfecIVA.getDescripcion());
        gCamIVA.addChildElement("dPropIVA").setTextContent(String.valueOf(this.dPropIVA));
        gCamIVA.addChildElement("dTasaIVA").setTextContent(String.valueOf(this.dTasaIVA));

        if (this.iAfecIVA.getVal() == 1 || this.iAfecIVA.getVal() == 4) {
            if (this.dTasaIVA.equals(BigDecimal.valueOf(10))) {
                this.dBasGravIVA = (dTotOpeItem.multiply(this.dPropIVA.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))).divide(BigDecimal.valueOf(1.1), RoundingMode.HALF_UP);
            } else if (this.dTasaIVA.equals(BigDecimal.valueOf(5))) {
                this.dBasGravIVA = (dTotOpeItem.multiply(this.dPropIVA.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))).divide(BigDecimal.valueOf(1.05), RoundingMode.HALF_UP);
            }

            this.dLiqIVAItem = this.dBasGravIVA.multiply(this.dTasaIVA.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        } else {
            this.dBasGravIVA = BigDecimal.ZERO;
            this.dLiqIVAItem = BigDecimal.ZERO;
        }

        gCamIVA.addChildElement("dBasGravIVA").setTextContent(String.valueOf(this.dBasGravIVA));
        gCamIVA.addChildElement("dLiqIVAItem").setTextContent(String.valueOf(this.dLiqIVAItem));
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

    public void setdLiqIVAItem(BigDecimal dLiqIVAItem) {
        this.dLiqIVAItem = dLiqIVAItem;
    }
}
