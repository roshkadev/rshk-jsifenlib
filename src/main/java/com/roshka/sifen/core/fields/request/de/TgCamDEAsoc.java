package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TTipTra;
import com.roshka.sifen.core.types.TdTipCons;
import com.roshka.sifen.core.types.TiTIpoDoc;
import com.roshka.sifen.core.types.TiTipDocAso;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigInteger;
import java.time.LocalDate;

public class TgCamDEAsoc extends SifenObjectBase {
    private TiTipDocAso iTipDocAso;
    private String dCdCDERef;
    private String dNTimDI;
    private String dEstDocAso;
    private String dPExpDocAso;
    private String dNumDocAso;
    private TiTIpoDoc iTipoDocAso;
    private LocalDate dFecEmiDI;
    private String dNumComRet;
    private String dNumResCF;
    private TdTipCons iTipCons;
    private BigInteger dNumCons;
    private String dNumControl;

    public void setupSOAPElements(SOAPElement DE, TTipTra iTipTra, boolean withholdingExists) throws SOAPException {
        SOAPElement gCamDEAsoc = DE.addChildElement("gCamDEAsoc");
        gCamDEAsoc.addChildElement("iTipDocAso").setTextContent(String.valueOf(this.iTipDocAso.getVal()));
        gCamDEAsoc.addChildElement("dDesTipDocAso").setTextContent(this.iTipDocAso.getDescripcion());

        if (this.iTipDocAso.getVal() == 1)
            gCamDEAsoc.addChildElement("dCdCDERef").setTextContent(this.dCdCDERef);
        else if (this.iTipDocAso.getVal() == 2) {
            gCamDEAsoc.addChildElement("dNTimDI").setTextContent(this.dNTimDI);
            gCamDEAsoc.addChildElement("dEstDocAso").setTextContent(this.dEstDocAso);
            gCamDEAsoc.addChildElement("dPExpDocAso").setTextContent(this.dPExpDocAso);
            gCamDEAsoc.addChildElement("dNumDocAso").setTextContent(this.dNumDocAso);
            gCamDEAsoc.addChildElement("iTipoDocAso").setTextContent(String.valueOf(this.iTipoDocAso.getVal()));
            gCamDEAsoc.addChildElement("dDTipoDocAso").setTextContent(this.iTipoDocAso.getDescripcion());
            gCamDEAsoc.addChildElement("dFecEmiDI").setTextContent(this.dFecEmiDI.toString());
        } else if (this.iTipDocAso.getVal() == 3) {
            gCamDEAsoc.addChildElement("iTipCons").setTextContent(String.valueOf(this.iTipCons.getVal()));
            gCamDEAsoc.addChildElement("dDesTipCons").setTextContent(this.iTipCons.getDescripcion());

            if (this.iTipCons.getVal() == 2) {
                gCamDEAsoc.addChildElement("dNumCons").setTextContent(String.valueOf(this.dNumCons));
                gCamDEAsoc.addChildElement("dNumControl").setTextContent(this.dNumControl);
            }
        }

        if (iTipTra != null && iTipTra.getVal() == 12)
            gCamDEAsoc.addChildElement("dNumResCF").setTextContent(this.dNumResCF);

        if (withholdingExists)
            gCamDEAsoc.addChildElement("dNumComRet").setTextContent(this.dNumComRet);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iTipDocAso":
                this.iTipDocAso = TiTipDocAso.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dCdCDERef":
                this.dCdCDERef = ResponseUtil.getTextValue(value);
                break;
            case "dNTimDI":
                this.dNTimDI = ResponseUtil.getTextValue(value);
                break;
            case "dEstDocAso":
                this.dEstDocAso = ResponseUtil.getTextValue(value);
                break;
            case "dPExpDocAso":
                this.dPExpDocAso = ResponseUtil.getTextValue(value);
                break;
            case "dNumDocAso":
                this.dNumDocAso = ResponseUtil.getTextValue(value);
                break;
            case "iTipoDocAso":
                this.iTipoDocAso = TiTIpoDoc.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dFecEmiDI":
                this.dFecEmiDI = ResponseUtil.getDateValue(value);
                break;
            case "iTipCons":
                this.iTipCons = TdTipCons.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dNumCons":
                this.dNumCons = new BigInteger(ResponseUtil.getTextValue(value));
                break;
            case "dNumControl":
                this.dNumControl = ResponseUtil.getTextValue(value);
                break;
            case "dNumResCF":
                this.dNumResCF = ResponseUtil.getTextValue(value);
                break;
            case "dNumComRet":
                this.dNumComRet = ResponseUtil.getTextValue(value);
                break;
        }
    }

    public TiTipDocAso getiTipDocAso() {
        return iTipDocAso;
    }

    public void setiTipDocAso(TiTipDocAso iTipDocAso) {
        this.iTipDocAso = iTipDocAso;
    }

    public String getdCdCDERef() {
        return dCdCDERef;
    }

    public void setdCdCDERef(String dCdCDERef) {
        this.dCdCDERef = dCdCDERef;
    }

    public String getdNTimDI() {
        return dNTimDI;
    }

    public void setdNTimDI(String dNTimDI) {
        this.dNTimDI = dNTimDI;
    }

    public String getdEstDocAso() {
        return dEstDocAso;
    }

    public void setdEstDocAso(String dEstDocAso) {
        this.dEstDocAso = SifenUtil.leftPad(dEstDocAso, '0', 3);
    }

    public String getdPExpDocAso() {
        return dPExpDocAso;
    }

    public void setdPExpDocAso(String dPExpDocAso) {
        this.dPExpDocAso = SifenUtil.leftPad(dPExpDocAso, '0', 3);
    }

    public String getdNumDocAso() {
        return dNumDocAso;
    }

    public void setdNumDocAso(String dNumDocAso) {
        this.dNumDocAso = SifenUtil.leftPad(dNumDocAso, '0', 7);
    }

    public TiTIpoDoc getiTipoDocAso() {
        return iTipoDocAso;
    }

    public void setiTipoDocAso(TiTIpoDoc iTipoDocAso) {
        this.iTipoDocAso = iTipoDocAso;
    }

    public LocalDate getdFecEmiDI() {
        return dFecEmiDI;
    }

    public void setdFecEmiDI(LocalDate dFecEmiDI) {
        this.dFecEmiDI = dFecEmiDI;
    }

    public String getdNumComRet() {
        return dNumComRet;
    }

    public void setdNumComRet(String dNumComRet) {
        this.dNumComRet = dNumComRet;
    }

    public String getdNumResCF() {
        return dNumResCF;
    }

    public void setdNumResCF(String dNumResCF) {
        this.dNumResCF = dNumResCF;
    }

    public TdTipCons getiTipCons() {
        return iTipCons;
    }

    public void setiTipCons(TdTipCons iTipCons) {
        this.iTipCons = iTipCons;
    }

    public BigInteger getdNumCons() {
        return dNumCons;
    }

    public void setdNumCons(BigInteger dNumCons) {
        this.dNumCons = dNumCons;
    }

    public String getdNumControl() {
        return dNumControl;
    }

    public void setdNumControl(String dNumControl) {
        this.dNumControl = dNumControl;
    }
}