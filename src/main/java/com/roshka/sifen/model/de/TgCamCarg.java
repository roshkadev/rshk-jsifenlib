package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.extra.TiCarCarga;
import com.roshka.sifen.model.unidades_medida.TcUniMed;
import com.roshka.sifen.util.SifenUtil;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigInteger;

public class TgCamCarg {
    private TcUniMed cUniMedTotVol;
    private BigInteger dTotVolMerc;
    private TcUniMed cUniMedTotPes;
    private BigInteger dTotPesMerc;
    private TiCarCarga iCarCarga;
    private String dDesCarCarga;

    public void setupSOAPElements(SOAPElement gCamGen) throws SOAPException {
        SOAPElement gCamCarg = gCamGen.addChildElement("gCamCarg");
        if (this.cUniMedTotVol != null) {
            gCamCarg.addChildElement("cUniMedTotVol").setTextContent(String.valueOf(this.cUniMedTotVol.getVal()));
            gCamCarg.addChildElement("dDesUniMedTotVol").setTextContent(this.cUniMedTotVol.getDescripcion());
        }
        if (this.dTotVolMerc != null)
            gCamCarg.addChildElement("dTotVolMerc").setTextContent(String.valueOf(this.dTotVolMerc));

        if (this.cUniMedTotPes != null) {
            gCamCarg.addChildElement("cUniMedTotPes").setTextContent(String.valueOf(this.cUniMedTotPes.getVal()));
            gCamCarg.addChildElement("dDesUniMedTotPes").setTextContent(this.cUniMedTotPes.getDescripcion());
        }
        if (this.dTotPesMerc != null)
            gCamCarg.addChildElement("dTotPesMerc").setTextContent(String.valueOf(this.dTotPesMerc));

        if (this.iCarCarga != null) {
            gCamCarg.addChildElement("iCarCarga").setTextContent(String.valueOf(this.iCarCarga.getVal()));
            gCamCarg.addChildElement("dDesCarCarga").setTextContent(SifenUtil.coalesce(this.iCarCarga.getDescripcion(), this.dDesCarCarga));
        }
    }

    public TcUniMed getcUniMedTotVol() {
        return cUniMedTotVol;
    }

    public void setcUniMedTotVol(TcUniMed cUniMedTotVol) {
        this.cUniMedTotVol = cUniMedTotVol;
    }

    public BigInteger getdTotVolMerc() {
        return dTotVolMerc;
    }

    public void setdTotVolMerc(BigInteger dTotVolMerc) {
        this.dTotVolMerc = dTotVolMerc;
    }

    public TcUniMed getcUniMedTotPes() {
        return cUniMedTotPes;
    }

    public void setcUniMedTotPes(TcUniMed cUniMedTotPes) {
        this.cUniMedTotPes = cUniMedTotPes;
    }

    public BigInteger getdTotPesMerc() {
        return dTotPesMerc;
    }

    public void setdTotPesMerc(BigInteger dTotPesMerc) {
        this.dTotPesMerc = dTotPesMerc;
    }

    public TiCarCarga getiCarCarga() {
        return iCarCarga;
    }

    public void setiCarCarga(TiCarCarga iCarCarga) {
        this.iCarCarga = iCarCarga;
    }

    public String getdDesCarCarga() {
        return dDesCarCarga;
    }

    public void setdDesCarCarga(String dDesCarCarga) {
        this.dDesCarCarga = dDesCarCarga;
    }

}
