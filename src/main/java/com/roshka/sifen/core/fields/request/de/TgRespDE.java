package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TiTipIDRespDE;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgRespDE extends SifenObjectBase {
    private TiTipIDRespDE iTipIDRespDE;
    private String dDTipIDRespDE;
    private String dNumIDRespDE;
    private String dNomRespDE;
    private String dCarRespDE;

    public void setupSOAPElements(SOAPElement gEmis) throws SOAPException {
        SOAPElement gRespDE = gEmis.addChildElement("gRespDE");
        gRespDE.addChildElement("iTipIDRespDE").setTextContent(String.valueOf(this.iTipIDRespDE.getVal()));
        gRespDE.addChildElement("dDTipIDRespDE").setTextContent(SifenUtil.coalesce(this.iTipIDRespDE.getDescripcion(), this.dDTipIDRespDE));
        gRespDE.addChildElement("dNumIDRespDE").setTextContent(this.dNumIDRespDE);
        gRespDE.addChildElement("dNomRespDE").setTextContent(this.dNomRespDE);
        gRespDE.addChildElement("dCarRespDE").setTextContent(this.dCarRespDE);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iTipIDRespDE":
                this.iTipIDRespDE = TiTipIDRespDE.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dDTipIDRespDE":
                this.dDTipIDRespDE = ResponseUtil.getTextValue(value);
                break;
            case "dNumIDRespDE":
                this.dNumIDRespDE = ResponseUtil.getTextValue(value);
                break;
            case "dNomRespDE":
                this.dNomRespDE = ResponseUtil.getTextValue(value);
                break;
            case "dCarRespDE":
                this.dCarRespDE = ResponseUtil.getTextValue(value);
                break;
        }
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