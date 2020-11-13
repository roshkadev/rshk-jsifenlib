package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgValorRestaItem {
    private BigDecimal dDescItem;
    private BigDecimal dPorcDesIt;
    private BigDecimal dDescGloItem;
    private BigDecimal dAntPreUniIt;
    private BigDecimal dAntGloPreUniIt;
    private BigDecimal dTotOpeItem;
    private BigDecimal dTotOpeGs;

    public void setupSOAPElements(SOAPElement gValorItem, BigDecimal dTiCamIt) throws SOAPException {
        SOAPElement gValorRestaItem = gValorItem.addChildElement("gValorRestaItem", Constants.SIFEN_NS_PREFIX);

        if (this.dDescItem != null) {
            gValorRestaItem.addChildElement("dDescItem", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dDescItem));
            gValorRestaItem.addChildElement("dPorcDesIt", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dPorcDesIt));
        }

        if (this.dDescGloItem != null)
            gValorRestaItem.addChildElement("dDescGloItem", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dDescGloItem));

        gValorRestaItem.addChildElement("dAntPreUniIt", Constants.SIFEN_NS_PREFIX).setTextContent(
                this.dAntPreUniIt != null ? String.valueOf(this.dAntPreUniIt) : "0"
        );

        gValorRestaItem.addChildElement("dAntGloPreUniIt", Constants.SIFEN_NS_PREFIX).setTextContent(
                this.dAntGloPreUniIt != null ? String.valueOf(this.dAntGloPreUniIt) : "0"
        );

        gValorRestaItem.addChildElement("dTotOpeItem", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotOpeItem));

        if (dTiCamIt != null)
            gValorRestaItem.addChildElement("dTotOpeGs", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotOpeGs));
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

    public void setdPorcDesIt(BigDecimal dPorcDesIt) {
        this.dPorcDesIt = dPorcDesIt;
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

    public void setdTotOpeItem(BigDecimal dTotOpeItem) {
        this.dTotOpeItem = dTotOpeItem;
    }

    public BigDecimal getdTotOpeGs() {
        return dTotOpeGs;
    }

    public void setdTotOpeGs(BigDecimal dTotOpeGs) {
        this.dTotOpeGs = dTotOpeGs;
    }
}
