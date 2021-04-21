package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.core.types.TiTiPago;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgPaConEIni extends SifenObjectBase {
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
        gPaConEIni.addChildElement("cMoneTiPag").setTextContent(this.cMoneTiPag.name());
        gPaConEIni.addChildElement("dDMoneTiPag").setTextContent(this.cMoneTiPag.getDescripcion());

        if (this.dTiCamTiPag != null || !this.cMoneTiPag.name().equals("PYG"))
            gPaConEIni.addChildElement("dTiCamTiPag").setTextContent(String.valueOf(this.dTiCamTiPag));

        if (this.iTiPago.getVal() == 3 || this.iTiPago.getVal() == 4)
            this.gPagTarCD.setupSOAPElements(gPaConEIni);
        else if (this.iTiPago.getVal() == 2)
            this.gPagCheq.setupSOAPElements(gPaConEIni);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iTiPago":
                this.iTiPago = TiTiPago.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dDesTiPag":
                this.dDesTiPag = ResponseUtil.getTextValue(value);
                break;
            case "dMonTiPag":
                this.dMonTiPag = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "cMoneTiPag":
                this.cMoneTiPag = CMondT.getByName(ResponseUtil.getTextValue(value));
                break;
            case "dTiCamTiPag":
                this.dTiCamTiPag = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "gPagTarCD":
                this.gPagTarCD = SifenObjectFactory.getFromNode(value, TgPagTarCD.class);
                break;
            case "gPagCheq":
                this.gPagCheq = SifenObjectFactory.getFromNode(value, TgPagCheq.class);
                break;
        }
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