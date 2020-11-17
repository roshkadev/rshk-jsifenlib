package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDate;

public class TgCompPub {
    private String dModCont;
    private int dEntCont;
    private short dAnoCont;
    private int dSecCont;
    private LocalDate dFeCodCont;

    public void setupSOAPElements(SOAPElement gCamFE) throws SOAPException {
        SOAPElement gCompPub = gCamFE.addChildElement("gCompPub");
        gCompPub.addChildElement("dModCont").setTextContent(this.dModCont);
        gCompPub.addChildElement("dEntCont").setTextContent(String.valueOf(this.dEntCont));
        gCompPub.addChildElement("dAnoCont").setTextContent(String.valueOf(this.dAnoCont));
        gCompPub.addChildElement("dSecCont").setTextContent(String.valueOf(this.dSecCont));
        gCompPub.addChildElement("dFeCodCont").setTextContent(this.dFeCodCont.toString());
    }

    public String getdModCont() {
        return dModCont;
    }

    public void setdModCont(String dModCont) {
        this.dModCont = dModCont;
    }

    public int getdEntCont() {
        return dEntCont;
    }

    public void setdEntCont(int dEntCont) {
        this.dEntCont = dEntCont;
    }

    public short getdAnoCont() {
        return dAnoCont;
    }

    public void setdAnoCont(short dAnoCont) {
        this.dAnoCont = dAnoCont;
    }

    public int getdSecCont() {
        return dSecCont;
    }

    public void setdSecCont(int dSecCont) {
        this.dSecCont = dSecCont;
    }

    public LocalDate getdFeCodCont() {
        return dFeCodCont;
    }

    public void setdFeCodCont(LocalDate dFeCodCont) {
        this.dFeCodCont = dFeCodCont;
    }
}
