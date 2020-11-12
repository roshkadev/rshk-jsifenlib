package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class TgGrupPolSeg {
    private String dPoliza;
    private String dUnidVig;
    private BigDecimal dVigencia;
    private String dNumPoliza;
    private LocalDateTime dFecIniVig;
    private LocalDateTime dFecFinVig;
    private String dCodInt;

    public void setupSOAPElements(SOAPElement gGrupSeg) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement gGrupPolSeg = gGrupSeg.addChildElement("gGrupPolSeg", NamespacesConstants.SIFEN_NS_PREFIX);
        gGrupPolSeg.addChildElement("dPoliza", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dPoliza);
        gGrupPolSeg.addChildElement("dUnidVig", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dUnidVig);
        gGrupPolSeg.addChildElement("dVigencia", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dVigencia));
        gGrupPolSeg.addChildElement("dNumPoliza", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumPoliza);

        if (this.dFecIniVig != null)
            gGrupPolSeg.addChildElement("dFecIniVig", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFecIniVig));

        if (this.dFecFinVig != null)
            gGrupPolSeg.addChildElement("dFecFinVig", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFecFinVig));

        if (this.dCodInt != null)
            gGrupPolSeg.addChildElement("dCodInt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dCodInt);
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
