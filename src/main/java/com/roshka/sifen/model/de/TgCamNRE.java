package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.TiMotivTras;
import com.roshka.sifen.model.de.types.TiRespEmiNR;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Campos de la nota de remisión electrónica
 */
public class TgCamNRE {
    private TiMotivTras iMotEmiNR;
    private TiRespEmiNR iRespEmiNR;
    private int dKmR;
    private LocalDate dFecEm;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SOAPElement gCamNRE = gDtipDE.addChildElement("gCamNRE", Constants.SIFEN_NS_PREFIX);
        gCamNRE.addChildElement("iMotEmiNR", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iMotEmiNR.getVal()));
        gCamNRE.addChildElement("dDesMotEmiNR", Constants.SIFEN_NS_PREFIX).setTextContent(this.iMotEmiNR.getDescripcion());
        gCamNRE.addChildElement("iRespEmiNR", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iRespEmiNR.getVal()));
        gCamNRE.addChildElement("dDesRespEmiNR", Constants.SIFEN_NS_PREFIX).setTextContent(this.iRespEmiNR.getDescripcion());

        if (this.dKmR != 0)
            gCamNRE.addChildElement("dKmR", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dKmR));

        if (dFecEm != null)
            gCamNRE.addChildElement("dFecEm", Constants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dFecEm));
    }

    public TiMotivTras getiMotEmiNR() {
        return iMotEmiNR;
    }

    public void setiMotEmiNR(TiMotivTras iMotEmiNR) {
        this.iMotEmiNR = iMotEmiNR;
    }

    public TiRespEmiNR getiRespEmiNR() {
        return iRespEmiNR;
    }

    public void setiRespEmiNR(TiRespEmiNR iRespEmiNR) {
        this.iRespEmiNR = iRespEmiNR;
    }

    public int getdKmR() {
        return dKmR;
    }

    public void setdKmR(int dKmR) {
        this.dKmR = dKmR;
    }

    public LocalDate getdFecEm() {
        return dFecEm;
    }

    public void setdFecEm(LocalDate dFecEm) {
        this.dFecEm = dFecEm;
    }
}
