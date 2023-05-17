package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.util.RedondeoUtil;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.core.types.TTImp;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.core.types.TdCondTiCam;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.roshka.sifen.core.fields.util.FieldFormatUtil.formattdCRed;

public class TgTotSub extends SifenObjectBase {
    private BigDecimal dSubExe = BigDecimal.ZERO;
    private BigDecimal dSubExo = BigDecimal.ZERO;
    private BigDecimal dSub5 = BigDecimal.ZERO;
    private BigDecimal dSub10 = BigDecimal.ZERO;
    private BigDecimal dTotOpe = BigDecimal.ZERO;
    private BigDecimal dTotDesc = BigDecimal.ZERO;
    private BigDecimal dTotDescGlotem = BigDecimal.ZERO;
    private BigDecimal dTotAntItem = BigDecimal.ZERO;
    private BigDecimal dTotAnt = BigDecimal.ZERO;
    private BigDecimal dPorcDescTotal = BigDecimal.ZERO;
    private BigDecimal dDescTotal = BigDecimal.ZERO;
    private BigDecimal dAnticipo = BigDecimal.ZERO;
    private BigDecimal dRedon = BigDecimal.ZERO;
    private BigDecimal dComi = BigDecimal.ZERO;
    private BigDecimal dTotGralOpe = BigDecimal.ZERO;
    private BigDecimal dIVA5 = BigDecimal.ZERO;
    private BigDecimal dIVA10 = BigDecimal.ZERO;
    private BigDecimal dLiqTotIVA5 = BigDecimal.ZERO;
    private BigDecimal dLiqTotIVA10 = BigDecimal.ZERO;
    private BigDecimal dIVAComi = BigDecimal.ZERO;
    private BigDecimal dTotIVA = BigDecimal.ZERO;
    private BigDecimal dBaseGrav5 = BigDecimal.ZERO;
    private BigDecimal dBaseGrav10 = BigDecimal.ZERO;
    private BigDecimal dTBasGraIVA = BigDecimal.ZERO;
    private BigDecimal dTotalGs = BigDecimal.ZERO;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE, TgDtipDE gDtipDE, TgOpeCom gOpeCom) throws SOAPException {
        resetValues();

        TTImp iTImp = gOpeCom.getiTImp();
        CMondT cMoneOpe = gOpeCom.getcMoneOpe();
        TdCondTiCam dCondTiCam = gOpeCom.getdCondTiCam();
        BigDecimal dTiCam = gOpeCom.getdTiCam();

        int scale = cMoneOpe == CMondT.PYG ? 0 : 2;

        SOAPElement gTotSub = DE.addChildElement("gTotSub");

        // CALCULOS
        for (TgCamItem gCamItem : gDtipDE.getgCamItemList()) {
            TgCamIVA gCamIVA = gCamItem.getgCamIVA();
            BigDecimal dTotOpeItem = gCamItem.getgValorItem().getgValorRestaItem().getdTotOpeItem();

            dTotOpeItem = dTotOpeItem.setScale(scale, RoundingMode.HALF_UP);
            if (gCamIVA != null) {
                BigDecimal dLiqIVAItem = gCamIVA.getdLiqIVAItem();
                BigDecimal dBasGravIVA = gCamIVA.getdBasGravIVA();

                if (gCamIVA.getiAfecIVA().getVal() == 1 || gCamIVA.getiAfecIVA().getVal() == 4) {
                    if (gCamIVA.getdTasaIVA().equals(BigDecimal.valueOf(10))) {
                        this.dSub10 = this.dSub10.add(dTotOpeItem);
                        this.dIVA10 = this.dIVA10.add(dLiqIVAItem);
                        this.dBaseGrav10 = this.dBaseGrav10.add(dBasGravIVA);
                        this.dLiqTotIVA10 = BigDecimal.ZERO;
                    } else if (gCamIVA.getdTasaIVA().equals(BigDecimal.valueOf(5))) {
                        this.dSub5 = this.dSub5.add(dTotOpeItem);
                        this.dIVA5 = this.dIVA5.add(dLiqIVAItem);
                        this.dBaseGrav5 = this.dBaseGrav5.add(dBasGravIVA);
                        this.dLiqTotIVA5 = BigDecimal.ZERO;
                    }
                } else if (gCamIVA.getiAfecIVA().getVal() == 2) {
                    this.dSubExo = this.dSubExo.add(dTotOpeItem);
                } else if (gCamIVA.getiAfecIVA().getVal() == 3) {
                    this.dSubExe = this.dSubExe.add(dTotOpeItem);
                }
            }

            if (iTiDE.getVal() == 4)
                this.dTotOpe = this.dTotOpe.add(dTotOpeItem);

            this.dTotDesc = this.dTotDesc.add(SifenUtil.coalesce(gCamItem.getgValorItem().getgValorRestaItem().getdDescItem().multiply(gCamItem.getdCantProSer()), BigDecimal.ZERO));
            this.dTotDescGlotem = this.dTotDescGlotem.add(SifenUtil.coalesce(gCamItem.getgValorItem().getgValorRestaItem().getdDescGloItem(), BigDecimal.ZERO));
            this.dTotAntItem = this.dTotAntItem.add(SifenUtil.coalesce(gCamItem.getgValorItem().getgValorRestaItem().getdAntPreUniIt(), BigDecimal.ZERO));
            this.dTotAnt = this.dTotAnt.add(SifenUtil.coalesce(gCamItem.getgValorItem().getgValorRestaItem().getdAntGloPreUniIt(), BigDecimal.ZERO));

            if (!cMoneOpe.name().equals("PYG") && dCondTiCam.getVal() == 2)
                this.dTotalGs = this.dTotalGs.add(gCamItem.getgValorItem().getgValorRestaItem().getdTotOpeGs());
        }

        if (iTiDE.getVal() != 4 && (iTImp.getVal() == 1 || iTImp.getVal() == 3 || iTImp.getVal() == 4 || iTImp.getVal() == 5)) {
            this.dTotOpe = this.dSub10.add(this.dSub5).add(this.dSubExo).add(this.dSubExe);
        }
        this.dDescTotal = this.dTotDesc.add(this.dTotDescGlotem);
        this.dPorcDescTotal = this.dDescTotal.multiply(BigDecimal.valueOf(100)).divide(this.dTotOpe.add(this.dDescTotal), 2, RoundingMode.HALF_UP);
        this.dAnticipo = this.dTotAntItem.add(this.dTotAnt);

        this.dRedon = RedondeoUtil.redondeoOficialSET(cMoneOpe, this.dTotOpe);
        this.dTotGralOpe = this.dTotOpe.subtract(this.dRedon).add(SifenUtil.coalesce(this.dComi, BigDecimal.ZERO));
        this.dRedon = this.dRedon.abs();

        if (this.dComi != null) {
            this.dIVAComi = this.dComi.divide(BigDecimal.valueOf(1.1), scale, RoundingMode.HALF_UP);
        }

        this.dTotIVA = this.dIVA5.add(this.dIVA10).subtract(this.dLiqTotIVA5).subtract(this.dLiqTotIVA10).add(this.dIVAComi);
        this.dTBasGraIVA = this.dBaseGrav5.add(this.dBaseGrav10);

        if (!cMoneOpe.name().equals("PYG") && dCondTiCam.getVal() == 1)
            this.dTotalGs = this.dTotGralOpe.multiply(dTiCam);

        // INSERCIONES
        if (iTiDE.getVal() != 4) {
            gTotSub.addChildElement("dSubExe").setTextContent(String.valueOf(this.dSubExe));
            gTotSub.addChildElement("dSubExo").setTextContent(String.valueOf(this.dSubExo));

            if (iTImp.getVal() == 1 || iTImp.getVal() == 5) {
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
        // se agregó control para formatear con 4 decimales
        gTotSub.addChildElement("dRedon").setTextContent(formattdCRed(this.dRedon));

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

            if (!cMoneOpe.name().equals("PYG"))
                gTotSub.addChildElement("dTotalGs").setTextContent(String.valueOf(this.dTotalGs));
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dSubExe":
                this.dSubExe = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dSubExo":
                this.dSubExo = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dSub5":
                this.dSub5 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dSub10":
                this.dSub10 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotOpe":
                this.dTotOpe = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotDesc":
                this.dTotDesc = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotDescGlotem":
                this.dTotDescGlotem = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotAntItem":
                this.dTotAntItem = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotAnt":
                this.dTotAnt = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dPorcDescTotal":
                this.dPorcDescTotal = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dDescTotal":
                this.dDescTotal = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dAnticipo":
                this.dAnticipo = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dRedon":
                this.dRedon = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dComi":
                this.dComi = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotGralOpe":
                this.dTotGralOpe = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dIVA5":
                this.dIVA5 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dIVA10":
                this.dIVA10 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dLiqTotIVA5":
                this.dLiqTotIVA5 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dLiqTotIVA10":
                this.dLiqTotIVA10 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dIVAComi":
                this.dIVAComi = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotIVA":
                this.dTotIVA = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dBaseGrav5":
                this.dBaseGrav5 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dBaseGrav10":
                this.dBaseGrav10 = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTBasGraIVA":
                this.dTBasGraIVA = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dTotalGs":
                this.dTotalGs = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
        }
    }

    private void resetValues() {
        dSubExe = BigDecimal.ZERO;
        dSubExo = BigDecimal.ZERO;
        dSub5 = BigDecimal.ZERO;
        dSub10 = BigDecimal.ZERO;
        dTotOpe = BigDecimal.ZERO;
        dTotDesc = BigDecimal.ZERO;
        dTotDescGlotem = BigDecimal.ZERO;
        dTotAntItem = BigDecimal.ZERO;
        dTotAnt = BigDecimal.ZERO;
        dPorcDescTotal = BigDecimal.ZERO;
        dDescTotal = BigDecimal.ZERO;
        dAnticipo = BigDecimal.ZERO;
        dRedon = BigDecimal.ZERO;
        dComi = BigDecimal.ZERO;
        dTotGralOpe = BigDecimal.ZERO;
        dIVA5 = BigDecimal.ZERO;
        dIVA10 = BigDecimal.ZERO;
        dLiqTotIVA5 = BigDecimal.ZERO;
        dLiqTotIVA10 = BigDecimal.ZERO;
        dIVAComi = BigDecimal.ZERO;
        dTotIVA = BigDecimal.ZERO;
        dBaseGrav5 = BigDecimal.ZERO;
        dBaseGrav10 = BigDecimal.ZERO;
        dTBasGraIVA = BigDecimal.ZERO;
        dTotalGs = BigDecimal.ZERO;
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
