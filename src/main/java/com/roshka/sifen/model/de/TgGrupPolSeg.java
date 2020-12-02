package com.roshka.sifen.model.de;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TgGrupPolSeg {
    private String dPoliza;
    private String dUnidVig;
    private BigDecimal dVigencia;
    private String dNumPoliza;
    private LocalDateTime dFecIniVig;
    private LocalDateTime dFecFinVig;
    private String dCodInt;

    public void setupSOAPElements(SOAPElement gGrupSeg) throws SOAPException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement gGrupPolSeg = gGrupSeg.addChildElement("gGrupPolSeg");
        gGrupPolSeg.addChildElement("dPoliza").setTextContent(this.dPoliza);
        gGrupPolSeg.addChildElement("dUnidVig").setTextContent(this.dUnidVig);
        gGrupPolSeg.addChildElement("dVigencia").setTextContent(String.valueOf(this.dVigencia));
        gGrupPolSeg.addChildElement("dNumPoliza").setTextContent(this.dNumPoliza);

        if (this.dFecIniVig != null)
            gGrupPolSeg.addChildElement("dFecIniVig").setTextContent(this.dFecIniVig.format(formatter));

        if (this.dFecFinVig != null)
            gGrupPolSeg.addChildElement("dFecFinVig").setTextContent(this.dFecFinVig.format(formatter));

        if (this.dCodInt != null)
            gGrupPolSeg.addChildElement("dCodInt").setTextContent(this.dCodInt);
    }

    public String getdPoliza() {
        return dPoliza;
    }

    public void setdPoliza(String dPoliza) {
        this.dPoliza = dPoliza;
    }

    public String getdUnidVig() {
        return dUnidVig;
    }

    public void setdUnidVig(String dUnidVig) {
        this.dUnidVig = dUnidVig;
    }

    public BigDecimal getdVigencia() {
        return dVigencia;
    }

    public void setdVigencia(BigDecimal dVigencia) {
        this.dVigencia = dVigencia;
    }

    public String getdNumPoliza() {
        return dNumPoliza;
    }

    public void setdNumPoliza(String dNumPoliza) {
        this.dNumPoliza = dNumPoliza;
    }

    public LocalDateTime getdFecIniVig() {
        return dFecIniVig;
    }

    public void setdFecIniVig(LocalDateTime dFecIniVig) {
        this.dFecIniVig = dFecIniVig;
    }

    public LocalDateTime getdFecFinVig() {
        return dFecFinVig;
    }

    public void setdFecFinVig(LocalDateTime dFecFinVig) {
        this.dFecFinVig = dFecFinVig;
    }

    public String getdCodInt() {
        return dCodInt;
    }

    public void setdCodInt(String dCodInt) {
        this.dCodInt = dCodInt;
    }

}
