package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TTiDE;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgCamGen {
    private String dOrdCompra;
    private String dOrdVta;
    private String dAsiento;
    private TgCamCarg gCamCarg;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE) throws SOAPException {
        SOAPElement gCamGen = DE.addChildElement("gCamGen", Constants.SIFEN_NS_PREFIX);
        if (this.dOrdCompra != null)
            gCamGen.addChildElement("dOrdCompra", Constants.SIFEN_NS_PREFIX).setTextContent(this.dOrdCompra);

        if (this.dOrdVta != null)
            gCamGen.addChildElement("dOrdVta", Constants.SIFEN_NS_PREFIX).setTextContent(this.dOrdVta);

        if (this.dAsiento != null)
            gCamGen.addChildElement("dAsiento", Constants.SIFEN_NS_PREFIX).setTextContent(this.dAsiento);

        if ((iTiDE.getVal() == 1 || iTiDE.getVal() == 7) && this.gCamCarg != null)
            this.gCamCarg.setupSOAPElements(gCamGen);
    }

    public String getdOrdCompra() {
        return dOrdCompra;
    }

    public void setdOrdCompra(String dOrdCompra) {
        this.dOrdCompra = dOrdCompra;
    }

    public String getdOrdVta() {
        return dOrdVta;
    }

    public void setdOrdVta(String dOrdVta) {
        this.dOrdVta = dOrdVta;
    }

    public String getdAsiento() {
        return dAsiento;
    }

    public void setdAsiento(String dAsiento) {
        this.dAsiento = dAsiento;
    }

    public TgCamCarg getgCamCarg() {
        return gCamCarg;
    }

    public void setgCamCarg(TgCamCarg gCamCarg) {
        this.gCamCarg = gCamCarg;
    }
}
