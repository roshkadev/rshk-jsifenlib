package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TgCompPub {
    private String dModCont;
    private int dEntCont;
    private short dAnoCont;
    private int dSecCont;
    private LocalDate dFeCodCont;

    public void setupSOAPElements(SOAPElement gCamFE) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SOAPElement gCompPub = gCamFE.addChildElement("gCompPub", Constants.SIFEN_NS_PREFIX);
        gCompPub.addChildElement("dModCont", Constants.SIFEN_NS_PREFIX).setTextContent(this.dModCont);
        gCompPub.addChildElement("dEntCont", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dEntCont));
        gCompPub.addChildElement("dAnoCont", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dAnoCont));
        gCompPub.addChildElement("dSecCont", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dSecCont));
        gCompPub.addChildElement("dFeCodCont", Constants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFeCodCont));
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
