package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.TiDenTarj;
import com.roshka.sifen.model.de.types.TiForProPa;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgPagTarCD {
    private TiDenTarj iDenTarj;
    private String dDesDenTarj;
    private String dRSProTar;
    private String dRUCProTar;
    private short dDVProTar;
    private TiForProPa iForProPa;
    private int dCodAuOpe;
    private String dNomTit;
    private short dNumTarj;

    public void setupSOAPElements(SOAPElement gPaConEIni) throws SOAPException {
        SOAPElement gPagTarCD = gPaConEIni.addChildElement("gPagTarCD", NamespacesConstants.SIFEN_NS_PREFIX);
        gPagTarCD.addChildElement("iDenTarj", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iDenTarj.getVal()));
        gPagTarCD.addChildElement("dDesDenTarj", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.iDenTarj.getDescripcion());

        if (this.dRSProTar != null)
            gPagTarCD.addChildElement("dRSProTar", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dRSProTar);

        if (this.dRUCProTar != null)
            gPagTarCD.addChildElement("dRUCProTar", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dRUCProTar);

        if (this.dDVProTar != 0)
            gPagTarCD.addChildElement("dDVProTar", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dDVProTar));

        gPagTarCD.addChildElement("iForProPa", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iForProPa.getVal()));

        if (this.dCodAuOpe != 0)
            gPagTarCD.addChildElement("dCodAuOpe", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dCodAuOpe));

        if (this.dNomTit != null)
            gPagTarCD.addChildElement("dNomTit", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNomTit);

        if (this.dNumTarj != 0)
            gPagTarCD.addChildElement("dNumTarj", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dNumTarj));
    }

    public TiDenTarj getiDenTarj() {
        return iDenTarj;
    }

    public void setiDenTarj(TiDenTarj iDenTarj) {
        this.iDenTarj = iDenTarj;
    }

    public String getdDesDenTarj() {
        return dDesDenTarj;
    }

    public void setdDesDenTarj(String dDesDenTarj) {
        this.dDesDenTarj = dDesDenTarj;
    }

    public String getdRSProTar() {
        return dRSProTar;
    }

    public void setdRSProTar(String dRSProTar) {
        this.dRSProTar = dRSProTar;
    }

    public String getdRUCProTar() {
        return dRUCProTar;
    }

    public void setdRUCProTar(String dRUCProTar) {
        this.dRUCProTar = dRUCProTar;
    }

    public short getdDVProTar() {
        return dDVProTar;
    }

    public void setdDVProTar(short dDVProTar) {
        this.dDVProTar = dDVProTar;
    }

    public TiForProPa getiForProPa() {
        return iForProPa;
    }

    public void setiForProPa(TiForProPa iForProPa) {
        this.iForProPa = iForProPa;
    }

    public int getdCodAuOpe() {
        return dCodAuOpe;
    }

    public void setdCodAuOpe(int dCodAuOpe) {
        this.dCodAuOpe = dCodAuOpe;
    }

    public String getdNomTit() {
        return dNomTit;
    }

    public void setdNomTit(String dNomTit) {
        this.dNomTit = dNomTit;
    }

    public short getdNumTarj() {
        return dNumTarj;
    }

    public void setdNumTarj(short dNumTarj) {
        this.dNumTarj = dNumTarj;
    }
}
