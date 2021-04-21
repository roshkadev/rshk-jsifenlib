package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TiCarCarga;
import com.roshka.sifen.core.types.TcUniMed;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigInteger;

public class TgCamCarg extends SifenObjectBase {
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
            gCamCarg.addChildElement("dDesUniMedTotVol").setTextContent(this.cUniMedTotVol.getAbreviatura());
        }
        if (this.dTotVolMerc != null)
            gCamCarg.addChildElement("dTotVolMerc").setTextContent(String.valueOf(this.dTotVolMerc));

        if (this.cUniMedTotPes != null) {
            gCamCarg.addChildElement("cUniMedTotPes").setTextContent(String.valueOf(this.cUniMedTotPes.getVal()));
            gCamCarg.addChildElement("dDesUniMedTotPes").setTextContent(this.cUniMedTotPes.getAbreviatura());
        }
        if (this.dTotPesMerc != null)
            gCamCarg.addChildElement("dTotPesMerc").setTextContent(String.valueOf(this.dTotPesMerc));

        if (this.iCarCarga != null) {
            gCamCarg.addChildElement("iCarCarga").setTextContent(String.valueOf(this.iCarCarga.getVal()));
            gCamCarg.addChildElement("dDesCarCarga").setTextContent(SifenUtil.coalesce(this.iCarCarga.getDescripcion(), this.dDesCarCarga));
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "cUniMedTotVol":
                this.cUniMedTotVol = TcUniMed.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dTotVolMerc":
                this.dTotVolMerc = new BigInteger(ResponseUtil.getTextValue(value));
                break;
            case "cUniMedTotPes":
                this.cUniMedTotPes = TcUniMed.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dTotPesMerc":
                this.dTotPesMerc = new BigInteger(ResponseUtil.getTextValue(value));
                break;
            case "iCarCarga":
                this.iCarCarga = TiCarCarga.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dDesCarCarga":
                this.dDesCarCarga = ResponseUtil.getTextValue(value);
                break;
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
