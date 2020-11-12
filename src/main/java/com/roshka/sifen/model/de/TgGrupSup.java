package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgGrupSup {
    private String dNomCaj;
    private BigDecimal dEfectivo;
    private BigDecimal dVuelto;
    private BigDecimal dDonac;
    private String dDesDonac;

    public void setupSOAPElements(SOAPElement gCamEsp) throws SOAPException {
        SOAPElement gGrupSup = gCamEsp.addChildElement("gGrupSup", NamespacesConstants.SIFEN_NS_PREFIX);
        if (this.dNomCaj != null)
            gGrupSup.addChildElement("dNomCaj", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNomCaj);

        if (this.dEfectivo != null)
            gGrupSup.addChildElement("dEfectivo", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dEfectivo));

        if (this.dVuelto != null)
            gGrupSup.addChildElement("dVuelto", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dVuelto));

        if (this.dDonac != null)
            gGrupSup.addChildElement("dDonac", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dDonac));

        if (this.dDesDonac != null)
            gGrupSup.addChildElement("dDesDonac", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesDonac);
    }

    public String getdNomCaj() {
        return dNomCaj;
    }

    public void setdNomCaj(String dNomCaj) {
        this.dNomCaj = dNomCaj;
    }

    public BigDecimal getdEfectivo() {
        return dEfectivo;
    }

    public void setdEfectivo(BigDecimal dEfectivo) {
        this.dEfectivo = dEfectivo;
    }

    public BigDecimal getdVuelto() {
        return dVuelto;
    }

    public void setdVuelto(BigDecimal dVuelto) {
        this.dVuelto = dVuelto;
    }

    public BigDecimal getdDonac() {
        return dDonac;
    }

    public void setdDonac(BigDecimal dDonac) {
        this.dDonac = dDonac;
    }

    public String getdDesDonac() {
        return dDesDonac;
    }

    public void setdDesDonac(String dDesDonac) {
        this.dDesDonac = dDesDonac;
    }

}
