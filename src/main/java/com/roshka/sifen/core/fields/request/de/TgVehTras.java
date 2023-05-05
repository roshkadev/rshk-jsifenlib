package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TiModTrans;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgVehTras extends SifenObjectBase {
    private String dTiVehTras;
    private String dMarVeh;
    private short dTipIdenVeh;
    private String dNroIDVeh;
    private String dAdicVeh;
    private String dNroMatVeh;
    private String dNroVuelo;

    public void setupSOAPElements(SOAPElement gTransp, TiModTrans iModTrans) throws SOAPException {
        SOAPElement gVehTras = gTransp.addChildElement("gVehTras");
        gVehTras.addChildElement("dTiVehTras").setTextContent(this.dTiVehTras);
        gVehTras.addChildElement("dMarVeh").setTextContent(this.dMarVeh);
        gVehTras.addChildElement("dTipIdenVeh").setTextContent(String.valueOf(this.dTipIdenVeh));

        if (this.dTipIdenVeh == 1)
            gVehTras.addChildElement("dNroIDVeh").setTextContent(this.dNroIDVeh);

        if (this.dAdicVeh != null)
            gVehTras.addChildElement("dAdicVeh").setTextContent(this.dAdicVeh);

        if (this.dTipIdenVeh == 2)
            gVehTras.addChildElement("dNroMatVeh").setTextContent(this.dNroMatVeh);

        if (iModTrans.getVal() == 3)
            gVehTras.addChildElement("dNroVuelo").setTextContent(this.dNroVuelo);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dTiVehTras":
                this.dTiVehTras = ResponseUtil.getTextValue(value);
                break;
            case "dMarVeh":
                this.dMarVeh = ResponseUtil.getTextValue(value);
                break;
            case "dTipIdenVeh":
                this.dTipIdenVeh = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dNroIDVeh":
                this.dNroIDVeh = ResponseUtil.getTextValue(value);
                break;
            case "dAdicVeh":
                this.dAdicVeh = ResponseUtil.getTextValue(value);
                break;
            case "dNroMatVeh":
                this.dNroMatVeh = ResponseUtil.getTextValue(value);
                break;
            case "dNroVuelo":
                this.dNroVuelo = ResponseUtil.getTextValue(value);
                break;
        }
    }

    public String getdTiVehTras() {
        return dTiVehTras;
    }

    public void setdTiVehTras(String dTiVehTras) {
        this.dTiVehTras = dTiVehTras;
    }

    public String getdMarVeh() {
        return dMarVeh;
    }

    public void setdMarVeh(String dMarVeh) {
        this.dMarVeh = dMarVeh;
    }

    public short getdTipIdenVeh() {
        return dTipIdenVeh;
    }

    public void setdTipIdenVeh(short dTipIdenVeh) {
        this.dTipIdenVeh = dTipIdenVeh;
    }

    public String getdNroIDVeh() {
        return dNroIDVeh;
    }

    public void setdNroIDVeh(String dNroIDVeh) {
        this.dNroIDVeh = dNroIDVeh;
    }

    public String getdAdicVeh() {
        return dAdicVeh;
    }

    public void setdAdicVeh(String dAdicVeh) {
        this.dAdicVeh = dAdicVeh;
    }

    public String getdNroMatVeh() {
        return dNroMatVeh;
    }

    public void setdNroMatVeh(String dNroMatVeh) {
        this.dNroMatVeh = dNroMatVeh;
    }

    public String getdNroVuelo() {
        return dNroVuelo;
    }

    public void setdNroVuelo(String dNroVuelo) {
        this.dNroVuelo = dNroVuelo;
    }
}
