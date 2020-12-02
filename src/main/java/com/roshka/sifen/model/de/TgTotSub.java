package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TTImp;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.model.de.types.TdCondTiCam;
import com.roshka.sifen.model.monedas.CMondT;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TgTotSub {
    private BigDecimal dSubExe = BigDecimal.ZERO;
    private BigDecimal dSubExo = BigDecimal.ZERO;
    private BigDecimal dSub5 = BigDecimal.ZERO;
    private BigDecimal dSub10 = BigDecimal.ZERO;
    private BigDecimal dTotOpe = BigDecimal.ZERO;
    private BigDecimal dTotDesc = BigDecimal.ZERO;
    private BigDecimal dTotDescGlotem = BigDecimal.ZERO;
    private BigDecimal dTotAntItem = BigDecimal.ZERO;
    private BigDecimal dTotAnt = BigDecimal.ZERO;
    private BigDecimal dPorcDescTotal;
    private BigDecimal dDescTotal;
    private BigDecimal dAnticipo;
    private BigDecimal dRedon;
    private BigDecimal dComi;
    private BigDecimal dTotGralOpe;
    private BigDecimal dIVA5 = BigDecimal.ZERO;
    private BigDecimal dIVA10 = BigDecimal.ZERO;
    private BigDecimal dLiqTotIVA5 = BigDecimal.ZERO;
    private BigDecimal dLiqTotIVA10 = BigDecimal.ZERO;
    private BigDecimal dIVAComi = BigDecimal.ZERO;
    private BigDecimal dTotIVA;
    private BigDecimal dBaseGrav5 = BigDecimal.ZERO;
    private BigDecimal dBaseGrav10 = BigDecimal.ZERO;
    private BigDecimal dTBasGraIVA;
    private BigDecimal dTotalGs = BigDecimal.ZERO;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE, TgDtipDE gDtipDE, TgOpeCom gOpeCom) throws SOAPException {
        TTImp iTImp = gOpeCom.getiTImp();
        CMondT cMoneOpe = gOpeCom.getcMoneOpe();
        TdCondTiCam dCondTiCam = gOpeCom.getdCondTiCam();
        BigDecimal dTiCam = gOpeCom.getdTiCam();

        SOAPElement gTotSub = DE.addChildElement("gTotSub");

        // CALCULOS
        for (TgCamItem gCamItem : gDtipDE.getgCamItemList()) {
            BigDecimal dTotOpeItem = gCamItem.getgValorItem().getgValorRestaItem().getdTotOpeItem();
            BigDecimal dLiqIVAItem = gCamItem.getgCamIVA().getdLiqIVAItem();
            BigDecimal dBasGravIVA = gCamItem.getgCamIVA().getdBasGravIVA();

            if (gCamItem.getgCamIVA().getiAfecIVA().getVal() == 1 || gCamItem.getgCamIVA().getiAfecIVA().getVal() == 4) {
                if (gCamItem.getgCamIVA().getdTasaIVA().equals(BigDecimal.valueOf(10))) {
                    this.dSub10 = this.dSub10.add(dTotOpeItem);
                    this.dIVA10 = this.dIVA10.add(dLiqIVAItem);
                    this.dBaseGrav10 = this.dBaseGrav10.add(dBasGravIVA);
                } else if (gCamItem.getgCamIVA().getdTasaIVA().equals(BigDecimal.valueOf(5))) {
                    this.dSub5 = this.dSub5.add(dTotOpeItem);
                    this.dIVA5 = this.dIVA5.add(dLiqIVAItem);
                    this.dBaseGrav5 = this.dBaseGrav5.add(dBasGravIVA);
                }
            } else if (gCamItem.getgCamIVA().getiAfecIVA().getVal() == 2) {
                this.dSubExo = this.dSubExo.add(dTotOpeItem);
            } else if (gCamItem.getgCamIVA().getiAfecIVA().getVal() == 3) {
                this.dSubExe = this.dSubExe.add(dTotOpeItem);
            }

            if (iTiDE.getVal() == 4)
                this.dTotOpe = this.dTotOpe.add(dTotOpeItem);

            this.dTotDesc = this.dTotDesc.add(gCamItem.getgValorItem().getgValorRestaItem().getdDescItem());
            this.dTotDescGlotem = this.dTotDescGlotem.add(gCamItem.getgValorItem().getgValorRestaItem().getdDescGloItem());
            this.dTotAntItem = this.dTotAntItem.add(gCamItem.getgValorItem().getgValorRestaItem().getdAntPreUniIt());
            this.dTotAnt = this.dTotAnt.add(gCamItem.getgValorItem().getgValorRestaItem().getdAntGloPreUniIt());

            if (!cMoneOpe.toString().equals("PYG") && dCondTiCam.getVal() == 2)
                this.dTotalGs = this.dTotalGs.add(gCamItem.getgValorItem().getgValorRestaItem().getdTotOpeGs());
        }

        if (iTiDE.getVal() != 4 && (iTImp.getVal() == 1 || iTImp.getVal() == 3 || iTImp.getVal() == 4 || iTImp.getVal() == 5)) {
            this.dTotOpe = this.dSub10.add(this.dSub5).add(this.dSubExo).add(this.dSubExe);
        }
        this.dDescTotal = this.dTotDesc.add(this.dTotDescGlotem);
        this.dPorcDescTotal = this.dDescTotal.multiply(BigDecimal.valueOf(100)).divide(this.dTotOpe.add(this.dDescTotal), RoundingMode.HALF_UP);
        this.dAnticipo = this.dTotAntItem.add(this.dTotAnt);
        this.dRedon = this.dTotOpe.subtract(BigDecimal.valueOf(Math.round(this.dTotOpe.doubleValue() * 50) / 50));
        this.dTotGralOpe = this.dTotOpe.subtract(this.dRedon).add(this.dComi != null ? this.dComi : BigDecimal.ZERO);

        if (this.dComi != null)
            this.dIVAComi = this.dComi.divide(BigDecimal.valueOf(1.1), RoundingMode.HALF_UP);

        this.dTotIVA = this.dIVA5.add(this.dIVA10).subtract(this.dLiqTotIVA5).subtract(this.dLiqTotIVA10).add(this.dIVAComi);
        this.dTBasGraIVA = this.dBaseGrav5.add(this.dBaseGrav10);

        if (!cMoneOpe.toString().equals("PYG") && dCondTiCam.getVal() == 1)
            this.dTotalGs = this.dTotGralOpe.multiply(dTiCam);

        // INSERCIONES
        if (iTiDE.getVal() != 4) {
            gTotSub.addChildElement("dSubExe").setTextContent(String.valueOf(this.dSubExe));
            gTotSub.addChildElement("dSubExo").setTextContent(String.valueOf(this.dSubExo));

            if (iTImp.getVal() == 1) {
                gTotSub.addChildElement("dSub5").setTextContent(String.valueOf(this.dSub5));
                gTotSub.addChildElement("dSub10").setTextContent(String.valueOf(this.dSub10));
            }
        }

        gTotSub.addChildElement("dTotOpe").setTextContent(String.valueOf(this.dTotOpe));
        gTotSub.addChildElement("dTotDesc").setTextContent(String.valueOf(this.dTotDesc));
        gTotSub.addChildElement("dTotDescGlotem").setTextContent(String.valueOf(this.dTotDescGlotem));
        gTotSub.addChildElement("dTotAntItem").setTextContent(String.valueOf(this.dTotAntItem));
        gTotSub.addChildElement("dTotAnt").setTextContent(String.valueOf(this.dTotAnt));
        gTotSub.addChildElement("dPorcDescTotal").setTextContent(String.valueOf(this.dPorcDescTotal));
        gTotSub.addChildElement("dDescTotal").setTextContent(String.valueOf(this.dDescTotal));
        gTotSub.addChildElement("dAnticipo").setTextContent(String.valueOf(this.dAnticipo));
        gTotSub.addChildElement("dRedon").setTextContent(String.valueOf(this.dRedon));

        if (iTiDE.getVal() != 4) {
            if (this.dComi != null)
                gTotSub.addChildElement("dComi").setTextContent(String.valueOf(this.dComi));
        }

        gTotSub.addChildElement("dTotGralOpe").setTextContent(String.valueOf(this.dTotGralOpe));

        if (iTiDE.getVal() != 4) {
            if (iTImp.getVal() == 1 || iTImp.getVal() == 5) {
                gTotSub.addChildElement("dIVA5").setTextContent(String.valueOf(this.dIVA5));
                gTotSub.addChildElement("dIVA10").setTextContent(String.valueOf(this.dIVA10));
            }
        }

        if (iTImp.getVal() == 1 || iTImp.getVal() == 5) {
            gTotSub.addChildElement("dLiqTotIVA5").setTextContent(String.valueOf(this.dLiqTotIVA5));
            gTotSub.addChildElement("dLiqTotIVA10").setTextContent(String.valueOf(this.dLiqTotIVA10));
        }

        if (iTiDE.getVal() != 4) {
            if (this.dComi != null)
                gTotSub.addChildElement("dIVAComi").setTextContent(String.valueOf(this.dIVAComi));

            if (iTImp.getVal() == 1 || iTImp.getVal() == 5) {
                gTotSub.addChildElement("dTotIVA").setTextContent(String.valueOf(this.dTotIVA));
                gTotSub.addChildElement("dBaseGrav5").setTextContent(String.valueOf(this.dBaseGrav5));
                gTotSub.addChildElement("dBaseGrav10").setTextContent(String.valueOf(this.dBaseGrav10));
                gTotSub.addChildElement("dTBasGraIVA").setTextContent(String.valueOf(this.dTBasGraIVA));
            }

            if (!cMoneOpe.toString().equals("PYG"))
                gTotSub.addChildElement("dTotalGs").setTextContent(String.valueOf(this.dTotalGs));
        }
    }

    public BigDecimal getdSubExe() {
        return dSubExe;
    }

    public BigDecimal getdSubExo() {
        return dSubExo;
    }

    public BigDecimal getdSub5() {
        return dSub5;
    }

    public BigDecimal getdSub10() {
        return dSub10;
    }

    public BigDecimal getdTotOpe() {
        return dTotOpe;
    }

    public BigDecimal getdTotDesc() {
        return dTotDesc;
    }

    public BigDecimal getdTotDescGlotem() {
        return dTotDescGlotem;
    }

    public BigDecimal getdTotAntItem() {
        return dTotAntItem;
    }

    public BigDecimal getdTotAnt() {
        return dTotAnt;
    }

    public BigDecimal getdPorcDescTotal() {
        return dPorcDescTotal;
    }

    public BigDecimal getdDescTotal() {
        return dDescTotal;
    }

    public BigDecimal getdAnticipo() {
        return dAnticipo;
    }

    public BigDecimal getdRedon() {
        return dRedon;
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

    public BigDecimal getdIVA5() {
        return dIVA5;
    }

    public BigDecimal getdIVA10() {
        return dIVA10;
    }

    public BigDecimal getdLiqTotIVA5() {
        return dLiqTotIVA5;
    }

    public BigDecimal getdLiqTotIVA10() {
        return dLiqTotIVA10;
    }

    public BigDecimal getdIVAComi() {
        return dIVAComi;
    }

    public BigDecimal getdTotIVA() {
        return dTotIVA;
    }

    public BigDecimal getdBaseGrav5() {
        return dBaseGrav5;
    }

    public BigDecimal getdBaseGrav10() {
        return dBaseGrav10;
    }

    public BigDecimal getdTBasGraIVA() {
        return dTBasGraIVA;
    }

    public BigDecimal getdTotalGs() {
        return dTotalGs;
    }
}
