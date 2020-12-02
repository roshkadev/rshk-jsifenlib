package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiModTrans;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgVehTras {
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
