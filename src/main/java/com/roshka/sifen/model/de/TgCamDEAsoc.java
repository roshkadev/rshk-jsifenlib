package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TTipTra;
import com.roshka.sifen.model.de.types.TdTipCons;
import com.roshka.sifen.model.de.types.TiTIpoDoc;
import com.roshka.sifen.model.de.types.TiTipDocAso;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigInteger;
import java.time.LocalDate;

public class TgCamDEAsoc {
    private TiTipDocAso iTipDocAso;
    private String dCdCDERef;
    private String dNTimDI;
    private String dEstDocAso;
    private String dPExpDocAso;
    private String dNumDocAso;
    private TiTIpoDoc iTipoDocAso;
    private LocalDate dFecEmiDI;
    private String dNumComRet;
    private String dNumResCF;
    private TdTipCons iTipCons;
    private BigInteger dNumCons;
    private String dNumControl;

    public void setupSOAPElements(SOAPElement DE, TTipTra iTipTra, boolean retencionExists) throws SOAPException {
        SOAPElement gOpeDE = DE.addChildElement("gOpeDE");
        gOpeDE.addChildElement("iTipDocAso").setTextContent(String.valueOf(this.iTipDocAso.getVal()));
        gOpeDE.addChildElement("dDesTipDocAso").setTextContent(this.iTipDocAso.getDescripcion());

        if (this.iTipDocAso.getVal() == 1)
            gOpeDE.addChildElement("dCdCDERef").setTextContent(this.dCdCDERef);
        else if (this.iTipDocAso.getVal() == 2) {
            gOpeDE.addChildElement("dNTimDI").setTextContent(this.dNTimDI);
            gOpeDE.addChildElement("dEstDocAso").setTextContent(this.dEstDocAso);
            gOpeDE.addChildElement("dPExpDocAso").setTextContent(this.dPExpDocAso);
            gOpeDE.addChildElement("dNumDocAso").setTextContent(this.dNumDocAso);
            gOpeDE.addChildElement("iTipoDocAso").setTextContent(String.valueOf(this.iTipoDocAso.getVal()));
            gOpeDE.addChildElement("dDTipoDocAso").setTextContent(this.iTipoDocAso.getDescripcion());
            gOpeDE.addChildElement("dFecEmiDI").setTextContent(this.dFecEmiDI.toString());
        } else if (this.iTipDocAso.getVal() == 3) {
            gOpeDE.addChildElement("iTipCons").setTextContent(String.valueOf(this.iTipCons.getVal()));
            gOpeDE.addChildElement("dDesTipCons").setTextContent(this.iTipCons.getDescripcion());

            if (this.iTipCons.getVal() == 2) {
                gOpeDE.addChildElement("dNumCons").setTextContent(String.valueOf(this.dNumCons));
                gOpeDE.addChildElement("dNumControl").setTextContent(this.dNumControl);
            }
        }

        if (iTipTra.getVal() == 12)
            gOpeDE.addChildElement("dNumResCF").setTextContent(this.dNumResCF);

        if (retencionExists)
            gOpeDE.addChildElement("dNumComRet").setTextContent(this.dNumComRet);
    }

    public TiTipDocAso getiTipDocAso() {
        return iTipDocAso;
    }

    public void setiTipDocAso(TiTipDocAso iTipDocAso) {
        this.iTipDocAso = iTipDocAso;
    }

    public String getdCdCDERef() {
        return dCdCDERef;
    }

    public void setdCdCDERef(String dCdCDERef) {
        this.dCdCDERef = dCdCDERef;
    }

    public String getdNTimDI() {
        return dNTimDI;
    }

    public void setdNTimDI(String dNTimDI) {
        this.dNTimDI = dNTimDI;
    }

    public String getdEstDocAso() {
        return dEstDocAso;
    }

    public void setdEstDocAso(String dEstDocAso) {
        this.dEstDocAso = dEstDocAso;
    }

    public String getdPExpDocAso() {
        return dPExpDocAso;
    }

    public void setdPExpDocAso(String dPExpDocAso) {
        this.dPExpDocAso = dPExpDocAso;
    }

    public String getdNumDocAso() {
        return dNumDocAso;
    }

    public void setdNumDocAso(String dNumDocAso) {
        this.dNumDocAso = dNumDocAso;
    }

    public TiTIpoDoc getiTipoDocAso() {
        return iTipoDocAso;
    }

    public void setiTipoDocAso(TiTIpoDoc iTipoDocAso) {
        this.iTipoDocAso = iTipoDocAso;
    }

    public LocalDate getdFecEmiDI() {
        return dFecEmiDI;
    }

    public void setdFecEmiDI(LocalDate dFecEmiDI) {
        this.dFecEmiDI = dFecEmiDI;
    }

    public String getdNumComRet() {
        return dNumComRet;
    }

    public void setdNumComRet(String dNumComRet) {
        this.dNumComRet = dNumComRet;
    }

    public String getdNumResCF() {
        return dNumResCF;
    }

    public void setdNumResCF(String dNumResCF) {
        this.dNumResCF = dNumResCF;
    }

    public TdTipCons getiTipCons() {
        return iTipCons;
    }

    public void setiTipCons(TdTipCons iTipCons) {
        this.iTipCons = iTipCons;
    }

    public BigInteger getdNumCons() {
        return dNumCons;
    }

    public void setdNumCons(BigInteger dNumCons) {
        this.dNumCons = dNumCons;
    }

    public String getdNumControl() {
        return dNumControl;
    }

    public void setdNumControl(String dNumControl) {
        this.dNumControl = dNumControl;
    }

}
