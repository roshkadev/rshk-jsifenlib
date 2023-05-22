package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.core.types.TiAfecIVA;
import com.roshka.sifen.internal.ctx.GenerationCtx;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
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
    private BigDecimal dBasExe;

    public void setupSOAPElements(GenerationCtx generationCtx, SOAPElement gCamItem, CMondT cMoneOpe, BigDecimal dTotOpeItem) throws SOAPException {
        SOAPElement gCamIVA = gCamItem.addChildElement("gCamIVA");
        gCamIVA.addChildElement("iAfecIVA").setTextContent(String.valueOf(this.iAfecIVA.getVal()));
        gCamIVA.addChildElement("dDesAfecIVA").setTextContent(this.iAfecIVA.getDescripcion());
        gCamIVA.addChildElement("dPropIVA").setTextContent(String.valueOf(this.dPropIVA));
        gCamIVA.addChildElement("dTasaIVA").setTextContent(String.valueOf(this.dTasaIVA));
        int scale = cMoneOpe.name().equals("PYG") ? 0 : 2;
        dTotOpeItem = dTotOpeItem.setScale(scale, RoundingMode.HALF_UP);

        BigDecimal hundred = BigDecimal.valueOf(100);
        BigDecimal propIVA = this.dPropIVA.divide(hundred, scale, RoundingMode.HALF_UP);
        if (this.iAfecIVA.getVal() == 1 || this.iAfecIVA.getVal() == 4) {
            if (this.dTasaIVA.equals(BigDecimal.valueOf(10))) {
                this.dBasGravIVA = dTotOpeItem.multiply(propIVA).divide(BigDecimal.valueOf(1.1), scale, RoundingMode.HALF_UP);
                this.dLiqIVAItem = dTotOpeItem.multiply(propIVA).divide(BigDecimal.valueOf(11), scale, RoundingMode.HALF_UP);
            } else if (this.dTasaIVA.equals(BigDecimal.valueOf(5))) {
                this.dBasGravIVA = dTotOpeItem.multiply(propIVA).divide(BigDecimal.valueOf(1.05), scale, RoundingMode.HALF_UP);
                this.dLiqIVAItem = dTotOpeItem.multiply(propIVA).divide(BigDecimal.valueOf(21), scale, RoundingMode.HALF_UP);
            }
        } else {
            this.dBasGravIVA = BigDecimal.ZERO;
            this.dLiqIVAItem = BigDecimal.ZERO;
        }


        gCamIVA.addChildElement("dBasGravIVA").setTextContent(String.valueOf(this.dBasGravIVA));
        gCamIVA.addChildElement("dLiqIVAItem").setTextContent(String.valueOf(this.dLiqIVAItem));

        if (generationCtx.isHabilitarNotaTecnica13()) {
            if (this.iAfecIVA.getVal() == 4) {
                // Actualización: https://ekuatia.set.gov.py/portal/ekuatia/detail?content-id=/repository/collaboration/sites/ekuatia/documents/documentacion/documentacion-tecnica/NT_E_KUATIA_013_MT_V150.pdf
                // E737 = [100 * EA008 * (100 – E733)] / [10000 + (E734 * E733)]
                this.dBasExe = (dTotOpeItem.multiply(hundred.subtract(dPropIVA)).multiply(hundred)).divide((this.dTasaIVA.multiply(dPropIVA)).add(BigDecimal.valueOf(10000)), scale, RoundingMode.HALF_UP);
            } else {
                this.dBasExe = BigDecimal.valueOf(0);
            }
            gCamIVA.addChildElement("dBasExe").setTextContent(String.valueOf(this.dBasExe));
        }

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
            case "dBasExe":
                this.dBasExe = new BigDecimal(ResponseUtil.getTextValue(value));
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

    public void setdBasGravIVA(BigDecimal dBasGravIVA) {
        this.dBasGravIVA = dBasGravIVA;
    }

    public BigDecimal getdLiqIVAItem() {
        return dLiqIVAItem;
    }

    public void setdLiqIVAItem(BigDecimal dLiqIVAItem) {
        this.dLiqIVAItem = dLiqIVAItem;
    }

    public BigDecimal getdBasExe() {
        return dBasExe;
    }

    public void setdBasExe(BigDecimal dBasExe) {
        this.dBasExe = dBasExe;
    }

}