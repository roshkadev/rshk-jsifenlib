package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class TrGeVeInu extends SifenObjectBase {
    private int dNumTim;
    private String dEst;
    private String dPunExp;
    private String dNumIn;
    private String dNumFin;
    private TTiDE iTiDE;
    private String mOtEve;

    public void setupSOAPElements(SOAPElement gGroupTiEvt) throws SOAPException {
        SOAPElement rGeVeInu = gGroupTiEvt.addChildElement("rGeVeInu");

        rGeVeInu.addChildElement("dNumTim").setTextContent(String.valueOf(this.dNumTim));
        rGeVeInu.addChildElement("dEst").setTextContent(this.dEst);
        rGeVeInu.addChildElement("dPunExp").setTextContent(this.dPunExp);
        rGeVeInu.addChildElement("dNumIn").setTextContent(this.dNumIn);
        rGeVeInu.addChildElement("dNumFin").setTextContent(this.dNumFin);
        rGeVeInu.addChildElement("iTiDE").setTextContent(String.valueOf(this.iTiDE.getVal()));
        rGeVeInu.addChildElement("mOtEve").setTextContent(this.mOtEve);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dNumTim":
                this.dNumTim = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dEst":
                this.dEst = ResponseUtil.getTextValue(value);
                break;
            case "dPunExp":
                this.dPunExp = ResponseUtil.getTextValue(value);
                break;
            case "dNumIn":
                this.dNumIn = ResponseUtil.getTextValue(value);
                break;
            case "dNumFin":
                this.dNumFin = ResponseUtil.getTextValue(value);
                break;
            case "iTiDE":
                this.iTiDE = TTiDE.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "mOtEve":
                this.mOtEve = ResponseUtil.getTextValue(value);
                break;
        }
    }

    public int getdNumTim() {
        return dNumTim;
    }

    public void setdNumTim(int dNumTim) {
        this.dNumTim = dNumTim;
    }

    public String getdEst() {
        return dEst;
    }

    public void setdEst(String dEst) {
        this.dEst = SifenUtil.leftPad(String.valueOf(dEst), '0', 3);
    }

    public String getdPunExp() {
        return dPunExp;
    }

    public void setdPunExp(String dPunExp) {
        this.dPunExp = SifenUtil.leftPad(String.valueOf(dPunExp), '0', 3);
    }

    public String getdNumIn() {
        return dNumIn;
    }

    public void setdNumIn(String dNumIn) {
        this.dNumIn = SifenUtil.leftPad(String.valueOf(dNumIn), '0', 7);
    }

    public String getdNumFin() {
        return dNumFin;
    }

    public void setdNumFin(String dNumFin) {
        this.dNumFin = SifenUtil.leftPad(String.valueOf(dNumFin), '0', 7);
    }

    public TTiDE getiTiDE() {
        return iTiDE;
    }

    public void setiTiDE(TTiDE iTiDE) {
        this.iTiDE = iTiDE;
    }

    public String getmOtEve() {
        return mOtEve;
    }

    public void setmOtEve(String mOtEve) {
        this.mOtEve = mOtEve;
    }
}