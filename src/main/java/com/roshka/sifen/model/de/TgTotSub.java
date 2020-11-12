package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.TTImp;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.model.monedas.CMondT;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgTotSub {
    private BigDecimal dSubExe;
    private BigDecimal dSubExo;
    private BigDecimal dSub5;
    private BigDecimal dSub10;
    private BigDecimal dTotOpe;
    private BigDecimal dTotDesc;
    private BigDecimal dTotDescGlotem;
    private BigDecimal dTotAntItem;
    private BigDecimal dTotAnt;
    private BigDecimal dPorcDescTotal;
    private BigDecimal dDescTotal;
    private BigDecimal dAnticipo;
    private BigDecimal dRedon;
    private BigDecimal dComi;
    private BigDecimal dTotGralOpe;
    private BigDecimal dIVA5;
    private BigDecimal dIVA10;
    private BigDecimal dLiqTotIVA5;
    private BigDecimal dLiqTotIVA10;
    private BigDecimal dIVAComi;
    private BigDecimal dTotIVA;
    private BigDecimal dBaseGrav5;
    private BigDecimal dBaseGrav10;
    private BigDecimal dTBasGraIVA;
    private BigDecimal dTotalGs;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE, TGOpeCom gOpeCom) throws SOAPException {
        TTImp iTImp = gOpeCom.getiTImp();
        CMondT cMoneOpe = gOpeCom.getcMoneOpe();

        SOAPElement gTotSub = DE.addChildElement("gTotSub", NamespacesConstants.SIFEN_NS_PREFIX);
        if (iTiDE.getVal() != 4) {
            gTotSub.addChildElement("dSubExe", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dSubExe));
            gTotSub.addChildElement("dSubExo", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dSubExo));

            if (iTImp.getVal() == 1) {
                gTotSub.addChildElement("dSub5", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dSub5));
                gTotSub.addChildElement("dSub10", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dSub10));
            }

            if (iTImp.getVal() == 1 || iTImp.getVal() == 5) {
                gTotSub.addChildElement("dIVA5", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dIVA5));
                gTotSub.addChildElement("dIVA10", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dIVA10));
                gTotSub.addChildElement("dTotIVA", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotIVA));
                gTotSub.addChildElement("dBaseGrav5", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dBaseGrav5));
                gTotSub.addChildElement("dBaseGrav10", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dBaseGrav10));
                gTotSub.addChildElement("dTBasGraIVA", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTBasGraIVA));
            }

            if (!cMoneOpe.toString().equals("PYG"))
                gTotSub.addChildElement("dTotalGs", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotalGs));

            if (this.dComi != null)
                gTotSub.addChildElement("dComi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dComi));

            if (this.dIVAComi != null)
                gTotSub.addChildElement("dIVAComi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dIVAComi));
        }

        gTotSub.addChildElement("dTotOpe", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotOpe));
        gTotSub.addChildElement("dTotDesc", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotDesc));
        gTotSub.addChildElement("dTotDescGlotem", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotDescGlotem));
        gTotSub.addChildElement("dTotAntItem", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotAntItem));
        gTotSub.addChildElement("dTotAnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotAnt));
        gTotSub.addChildElement("dPorcDescTotal", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dPorcDescTotal));
        gTotSub.addChildElement("dDescTotal", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dDescTotal));
        gTotSub.addChildElement("dAnticipo", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dAnticipo));
        gTotSub.addChildElement("dRedon", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dRedon));
        gTotSub.addChildElement("dTotGralOpe", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dTotGralOpe));

        if (iTImp.getVal() == 1 || iTImp.getVal() == 5) {
            gTotSub.addChildElement("dLiqTotIVA5", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dLiqTotIVA5));
            gTotSub.addChildElement("dLiqTotIVA10", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dLiqTotIVA10));
        }
    }

    public BigDecimal getdSubExe() {
        return dSubExe;
    }

    public void setdSubExe(BigDecimal dSubExe) {
        this.dSubExe = dSubExe;
    }

    public BigDecimal getdSubExo() {
        return dSubExo;
    }

    public void setdSubExo(BigDecimal dSubExo) {
        this.dSubExo = dSubExo;
    }

    public BigDecimal getdSub5() {
        return dSub5;
    }

    public void setdSub5(BigDecimal dSub5) {
        this.dSub5 = dSub5;
    }

    public BigDecimal getdSub10() {
        return dSub10;
    }

    public void setdSub10(BigDecimal dSub10) {
        this.dSub10 = dSub10;
    }

    public BigDecimal getdTotOpe() {
        return dTotOpe;
    }

    public void setdTotOpe(BigDecimal dTotOpe) {
        this.dTotOpe = dTotOpe;
    }

    public BigDecimal getdTotDesc() {
        return dTotDesc;
    }

    public void setdTotDesc(BigDecimal dTotDesc) {
        this.dTotDesc = dTotDesc;
    }

    public BigDecimal getdTotDescGlotem() {
        return dTotDescGlotem;
    }

    public void setdTotDescGlotem(BigDecimal dTotDescGlotem) {
        this.dTotDescGlotem = dTotDescGlotem;
    }

    public BigDecimal getdTotAntItem() {
        return dTotAntItem;
    }

    public void setdTotAntItem(BigDecimal dTotAntItem) {
        this.dTotAntItem = dTotAntItem;
    }

    public BigDecimal getdTotAnt() {
        return dTotAnt;
    }

    public void setdTotAnt(BigDecimal dTotAnt) {
        this.dTotAnt = dTotAnt;
    }

    public BigDecimal getdPorcDescTotal() {
        return dPorcDescTotal;
    }

    public void setdPorcDescTotal(BigDecimal dPorcDescTotal) {
        this.dPorcDescTotal = dPorcDescTotal;
    }

    public BigDecimal getdDescTotal() {
        return dDescTotal;
    }

    public void setdDescTotal(BigDecimal dDescTotal) {
        this.dDescTotal = dDescTotal;
    }

    public BigDecimal getdAnticipo() {
        return dAnticipo;
    }

    public void setdAnticipo(BigDecimal dAnticipo) {
        this.dAnticipo = dAnticipo;
    }

    public BigDecimal getdRedon() {
        return dRedon;
    }

    public void setdRedon(BigDecimal dRedon) {
        this.dRedon = dRedon;
    }

    public BigDecimal getdComi() {
        return dComi;
    }

    public void setdComi(BigDecimal dComi) {
        this.dComi = dComi;
    }

    public BigDecimal getdTotGralOpe() {
        return dTotGralOpe;
    }

    public void setdTotGralOpe(BigDecimal dTotGralOpe) {
        this.dTotGralOpe = dTotGralOpe;
    }

    public BigDecimal getdIVA5() {
        return dIVA5;
    }

    public void setdIVA5(BigDecimal dIVA5) {
        this.dIVA5 = dIVA5;
    }

    public BigDecimal getdIVA10() {
        return dIVA10;
    }

    public void setdIVA10(BigDecimal dIVA10) {
        this.dIVA10 = dIVA10;
    }

    public BigDecimal getdLiqTotIVA5() {
        return dLiqTotIVA5;
    }

    public void setdLiqTotIVA5(BigDecimal dLiqTotIVA5) {
        this.dLiqTotIVA5 = dLiqTotIVA5;
    }

    public BigDecimal getdLiqTotIVA10() {
        return dLiqTotIVA10;
    }

    public void setdLiqTotIVA10(BigDecimal dLiqTotIVA10) {
        this.dLiqTotIVA10 = dLiqTotIVA10;
    }

    public BigDecimal getdIVAComi() {
        return dIVAComi;
    }

    public void setdIVAComi(BigDecimal dIVAComi) {
        this.dIVAComi = dIVAComi;
    }

    public BigDecimal getdTotIVA() {
        return dTotIVA;
    }

    public void setdTotIVA(BigDecimal dTotIVA) {
        this.dTotIVA = dTotIVA;
    }

    public BigDecimal getdBaseGrav5() {
        return dBaseGrav5;
    }

    public void setdBaseGrav5(BigDecimal dBaseGrav5) {
        this.dBaseGrav5 = dBaseGrav5;
    }

    public BigDecimal getdBaseGrav10() {
        return dBaseGrav10;
    }

    public void setdBaseGrav10(BigDecimal dBaseGrav10) {
        this.dBaseGrav10 = dBaseGrav10;
    }

    public BigDecimal getdTBasGraIVA() {
        return dTBasGraIVA;
    }

    public void setdTBasGraIVA(BigDecimal dTBasGraIVA) {
        this.dTBasGraIVA = dTBasGraIVA;
    }

    public BigDecimal getdTotalGs() {
        return dTotalGs;
    }

    public void setdTotalGs(BigDecimal dTotalGs) {
        this.dTotalGs = dTotalGs;
    }

}
