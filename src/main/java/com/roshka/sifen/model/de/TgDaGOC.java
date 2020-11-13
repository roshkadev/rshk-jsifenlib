package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TTiDE;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class TgDaGOC {
    private LocalDateTime dFeEmiDE;
    private TGOpeCom gOpeCom;
    private TgEmis gEmis;
    private TgDatRec gDatRec;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement gDatGralOpe = DE.addChildElement("gDatGralOpe", Constants.SIFEN_NS_PREFIX);
        gDatGralOpe.addChildElement("dFeEmiDE", Constants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFeEmiDE));
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

    public TGOpeCom getgOpeCom() {
        return gOpeCom;
    }

    public void setgOpeCom(TGOpeCom gOpeCom) {
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
