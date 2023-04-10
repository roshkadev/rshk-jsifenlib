package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgGrupSup extends SifenObjectBase {
    private String dNomCaj;
    private BigDecimal dEfectivo;
    private BigDecimal dVuelto;
    private BigDecimal dDonac;
    private String dDesDonac;

    public void setupSOAPElements(SOAPElement gCamEsp) throws SOAPException {
        SOAPElement gGrupSup = gCamEsp.addChildElement("gGrupSup");
        if (this.dNomCaj != null)
            gGrupSup.addChildElement("dNomCaj").setTextContent(this.dNomCaj);

        if (this.dEfectivo != null)
            gGrupSup.addChildElement("dEfectivo").setTextContent(String.valueOf(this.dEfectivo));

        if (this.dVuelto != null)
            gGrupSup.addChildElement("dVuelto").setTextContent(String.valueOf(this.dVuelto));

        if (this.dDonac != null)
            gGrupSup.addChildElement("dDonac").setTextContent(String.valueOf(this.dDonac));

        if (this.dDesDonac != null)
            gGrupSup.addChildElement("dDesDonac").setTextContent(this.dDesDonac);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dNomCaj":
                this.dNomCaj = ResponseUtil.getTextValue(value);
                break;
            case "dEfectivo":
                this.dEfectivo = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dVuelto":
                this.dVuelto = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dDonac":
                this.dDonac = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dDesDonac":
                this.dDesDonac = ResponseUtil.getTextValue(value);
                break;
        }
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