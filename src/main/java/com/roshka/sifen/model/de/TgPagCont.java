package com.roshka.sifen.model.de;

import com.roshka.sifen.model.monedas.CMondT;
import com.roshka.sifen.model.de.types.TiTiPago;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgPagCont {
    private TiTiPago iTiPago;
    private String dDesTiPag;
    private BigDecimal dMonTiPag;
    private CMondT cMoneTiPag;
    private BigDecimal dTiCamTiPag;
    private TgPagTarCD gPagTarCD;
    private TgPagCheq gPagCheq;

    public void setupSOAPElements(SOAPElement gCamCond) throws SOAPException {
        SOAPElement gPaConEIni = gCamCond.addChildElement("gPaConEIni");
        gPaConEIni.addChildElement("iTiPago").setTextContent(String.valueOf(this.iTiPago.getVal()));
        gPaConEIni.addChildElement("dDesTiPag").setTextContent(this.iTiPago.getDescripcion());
        gPaConEIni.addChildElement("dMonTiPag").setTextContent(String.valueOf(this.dMonTiPag));
        gPaConEIni.addChildElement("cMoneTiPag").setTextContent(this.cMoneTiPag.toString());
        gPaConEIni.addChildElement("dDMoneTiPag").setTextContent(this.cMoneTiPag.getDescripcion());

        if (this.dTiCamTiPag != null || !this.cMoneTiPag.toString().equals("PYG"))
            gPaConEIni.addChildElement("dTiCamTiPag").setTextContent(String.valueOf(this.dTiCamTiPag));

        if (this.iTiPago.getVal() == 3 || this.iTiPago.getVal() == 4)
            this.gPagTarCD.setupSOAPElements(gPaConEIni);
        else if (this.iTiPago.getVal() == 2)
            this.gPagCheq.setupSOAPElements(gPaConEIni);
    }

    public TiTiPago getiTiPago() {
        return iTiPago;
    }

    public void setiTiPago(TiTiPago iTiPago) {
        this.iTiPago = iTiPago;
    }

    public String getdDesTiPag() {
        return dDesTiPag;
    }

    public void setdDesTiPag(String dDesTiPag) {
        this.dDesTiPag = dDesTiPag;
    }

    public BigDecimal getdMonTiPag() {
        return dMonTiPag;
    }

    public void setdMonTiPag(BigDecimal dMonTiPag) {
        this.dMonTiPag = dMonTiPag;
    }

    public CMondT getcMoneTiPag() {
        return cMoneTiPag;
    }

    public void setcMoneTiPag(CMondT cMoneTiPag) {
        this.cMoneTiPag = cMoneTiPag;
    }

    public BigDecimal getdTiCamTiPag() {
        return dTiCamTiPag;
    }

    public void setdTiCamTiPag(BigDecimal dTiCamTiPag) {
        this.dTiCamTiPag = dTiCamTiPag;
    }

    public TgPagTarCD getgPagTarCD() {
        return gPagTarCD;
    }

    public void setgPagTarCD(TgPagTarCD gPagTarCD) {
        this.gPagTarCD = gPagTarCD;
    }

    public TgPagCheq getgPagCheq() {
        return gPagCheq;
    }

    public void setgPagCheq(TgPagCheq gPagCheq) {
        this.gPagCheq = gPagCheq;
    }
}
