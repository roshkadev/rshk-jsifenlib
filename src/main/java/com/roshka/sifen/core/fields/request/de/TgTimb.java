package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.time.LocalDate;

public class TgTimb extends SifenObjectBase {
    private TTiDE iTiDE;        // este campo engloba a iTiDE y a dDesTiDE
    private int dNumTim;        // número de timbrado
    private String dEst;        // código de establecimiento: patron ej: 001
    private String dPunExp;     // punto de expedición: patron ej: 001
    private String dNumDoc;     // número de documento: patron ej: 0192312
    private String dSerieNum;   // número de serie del timbrado (opcional)
    private LocalDate dFeIniT;  // fecha de inicio de vigencia del timbrado

    public void setupSOAPElements(SOAPElement DE) throws SOAPException {
        SOAPElement gTimb = DE.addChildElement("gTimb");
        gTimb.addChildElement("iTiDE").setTextContent(String.valueOf(this.iTiDE.getVal()));
        gTimb.addChildElement("dDesTiDE").setTextContent(this.iTiDE.getDescripcion());
        gTimb.addChildElement("dNumTim").setTextContent(String.valueOf(this.dNumTim));
        gTimb.addChildElement("dEst").setTextContent(this.dEst);
        gTimb.addChildElement("dPunExp").setTextContent(this.dPunExp);
        gTimb.addChildElement("dNumDoc").setTextContent(this.dNumDoc);
        if (this.dSerieNum != null)
            gTimb.addChildElement("dSerieNum").setTextContent(this.dSerieNum);
        gTimb.addChildElement("dFeIniT").setTextContent(this.dFeIniT.toString());
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iTiDE":
                this.iTiDE = TTiDE.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dNumTim":
                this.dNumTim = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dEst":
                this.dEst = ResponseUtil.getTextValue(value);
                break;
            case "dPunExp":
                this.dPunExp = ResponseUtil.getTextValue(value);
                break;
            case "dNumDoc":
                this.dNumDoc = ResponseUtil.getTextValue(value);
                break;
            case "dSerieNum":
                this.dSerieNum = ResponseUtil.getTextValue(value);
                break;
            case "dFeIniT":
                this.dFeIniT = ResponseUtil.getDateValue(value);
                break;
        }
    }

    public TTiDE getiTiDE() {
        return iTiDE;
    }

    public void setiTiDE(TTiDE iTiDE) {
        this.iTiDE = iTiDE;
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

    public String getdNumDoc() {
        return dNumDoc;
    }

    public void setdNumDoc(String dNumDoc) {
        this.dNumDoc = SifenUtil.leftPad(String.valueOf(dNumDoc), '0', 7);
    }

    public String getdSerieNum() {
        return dSerieNum;
    }

    public void setdSerieNum(String dSerieNum) {
        this.dSerieNum = dSerieNum;
    }

    public LocalDate getdFeIniT() {
        return dFeIniT;
    }

    public void setdFeIniT(LocalDate dFeIniT) {
        this.dFeIniT = dFeIniT;
    }
}