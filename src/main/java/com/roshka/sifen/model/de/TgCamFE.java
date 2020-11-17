package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TiIndPres;
import com.roshka.sifen.model.de.types.TiTiOpe;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDate;

public class TgCamFE {
    private TiIndPres iIndPres;
    private String dDesIndPres;
    private LocalDate dFecEmNR;
    private TgCompPub gCompPub;

    public void setupSOAPElements(SOAPElement gDtipDE, TiTiOpe iTiOpe) throws SOAPException {
        SOAPElement gCamFE = gDtipDE.addChildElement("gCamFE");
        gCamFE.addChildElement("iIndPres").setTextContent(String.valueOf(this.iIndPres.getVal()));
        gCamFE.addChildElement("dDesIndPres").setTextContent(
                this.iIndPres.getDescripcion() != null ? this.iIndPres.getDescripcion() : this.dDesIndPres
        );

        if (this.dFecEmNR != null)
            gCamFE.addChildElement("dFecEmNR").setTextContent(this.dFecEmNR.toString());

        if (iTiOpe.getVal() == 3)
            this.gCompPub.setupSOAPElements(gCamFE);
    }

    public TiIndPres getiIndPres() {
        return iIndPres;
    }

    public void setiIndPres(TiIndPres iIndPres) {
        this.iIndPres = iIndPres;
    }

    public String getdDesIndPres() {
        return dDesIndPres;
    }

    public void setdDesIndPres(String dDesIndPres) {
        this.dDesIndPres = dDesIndPres;
    }

    public LocalDate getdFecEmNR() {
        return dFecEmNR;
    }

    public void setdFecEmNR(LocalDate dFecEmNR) {
        this.dFecEmNR = dFecEmNR;
    }

    public TgCompPub getgCompPub() {
        return gCompPub;
    }

    public void setgCompPub(TgCompPub gCompPub) {
        this.gCompPub = gCompPub;
    }
}
