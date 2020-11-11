package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.TiTipIDRespDE;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgRespDE {
    private TiTipIDRespDE iTipIDRespDE;
    private String dDTipIDRespDE;
    private String dNumIDRespDE;
    private String dNomRespDE;
    private String dCarRespDE;

    public void setupSOAPElements(SOAPElement gEmis) throws SOAPException {
        SOAPElement gRespDE = gEmis.addChildElement("gRespDE", NamespacesConstants.SIFEN_NS_PREFIX);
        gRespDE.addChildElement("iTipIDRespDE", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iTipIDRespDE.getVal()));
        gRespDE.addChildElement("dDTipIDRespDE", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(
                this.iTipIDRespDE.getDescripcion() != null ? this.iTipIDRespDE.getDescripcion() : this.dDTipIDRespDE
        );
        gRespDE.addChildElement("dNumIDRespDE", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumIDRespDE);
        gRespDE.addChildElement("dNomRespDE", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNomRespDE);
        gRespDE.addChildElement("dCarRespDE", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dCarRespDE);
    }

    public TiTipIDRespDE getiTipIDRespDE() {
        return iTipIDRespDE;
    }

    public void setiTipIDRespDE(TiTipIDRespDE iTipIDRespDE) {
        this.iTipIDRespDE = iTipIDRespDE;
    }

    public String getdDTipIDRespDE() {
        return dDTipIDRespDE;
    }

    public void setdDTipIDRespDE(String dDTipIDRespDE) {
        this.dDTipIDRespDE = dDTipIDRespDE;
    }

    public String getdNumIDRespDE() {
        return dNumIDRespDE;
    }

    public void setdNumIDRespDE(String dNumIDRespDE) {
        this.dNumIDRespDE = dNumIDRespDE;
    }

    public String getdNomRespDE() {
        return dNomRespDE;
    }

    public void setdNomRespDE(String dNomRespDE) {
        this.dNomRespDE = dNomRespDE;
    }

    public String getdCarRespDE() {
        return dCarRespDE;
    }

    public void setdCarRespDE(String dCarRespDE) {
        this.dCarRespDE = dCarRespDE;
    }
}
