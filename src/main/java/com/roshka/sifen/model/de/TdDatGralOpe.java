package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TTiDE;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TdDatGralOpe {
    private LocalDateTime dFeEmiDE;
    private TgOpeCom gOpeCom;
    private TgEmis gEmis;
    private TgDatRec gDatRec;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE) throws SOAPException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement gDatGralOpe = DE.addChildElement("gDatGralOpe");
        gDatGralOpe.addChildElement("dFeEmiDE").setTextContent(this.dFeEmiDE.format(formatter));
        if (iTiDE.getVal() != 7) this.gOpeCom.setupSOAPElements(gDatGralOpe, iTiDE);
        this.gEmis.setupSOAPElements(gDatGralOpe);
        this.gDatRec.setupSOAPElements(gDatGralOpe, iTiDE);
    }

    public LocalDateTime getdFeEmiDE() {
        return dFeEmiDE;
    }

    public void setdFeEmiDE(LocalDateTime dFeEmiDE) {
        this.dFeEmiDE = dFeEmiDE;
    }

    public TgOpeCom getgOpeCom() {
        return gOpeCom;
    }

    public void setgOpeCom(TgOpeCom gOpeCom) {
        this.gOpeCom = gOpeCom;
    }

    public TgEmis getgEmis() {
        return gEmis;
    }

    public void setgEmis(TgEmis gEmis) {
        this.gEmis = gEmis;
    }

    public TgDatRec getgDatRec() {
        return gDatRec;
    }

    public void setgDatRec(TgDatRec gDatRec) {
        this.gDatRec = gDatRec;
    }
}
