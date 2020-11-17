package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgCamEsp {
    private TgGrupEner gGrupEner;
    private TgGrupSeg gGrupSeg;
    private TgGrupSup gGrupSup;
    private TgGrupAdi gGrupAdi;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SOAPElement gCamEsp = gDtipDE.addChildElement("gCamEsp");
        if (this.gGrupEner != null)
            this.gGrupEner.setupSOAPElements(gCamEsp);

        if (this.gGrupSeg != null)
            this.gGrupSeg.setupSOAPElements(gCamEsp);

        if (this.gGrupSup != null)
            this.gGrupSup.setupSOAPElements(gCamEsp);

        if (this.gGrupAdi != null)
            this.gGrupAdi.setupSOAPElements(gCamEsp);
    }

    public TgGrupEner getgGrupEner() {
        return gGrupEner;
    }

    public void setgGrupEner(TgGrupEner gGrupEner) {
        this.gGrupEner = gGrupEner;
    }

    public TgGrupSeg getgGrupSeg() {
        return gGrupSeg;
    }

    public void setgGrupSeg(TgGrupSeg gGrupSeg) {
        this.gGrupSeg = gGrupSeg;
    }

    public TgGrupSup getgGrupSup() {
        return gGrupSup;
    }

    public void setgGrupSup(TgGrupSup gGrupSup) {
        this.gGrupSup = gGrupSup;
    }

    public TgGrupAdi getgGrupAdi() {
        return gGrupAdi;
    }

    public void setgGrupAdi(TgGrupAdi gGrupAdi) {
        this.gGrupAdi = gGrupAdi;
    }
}
