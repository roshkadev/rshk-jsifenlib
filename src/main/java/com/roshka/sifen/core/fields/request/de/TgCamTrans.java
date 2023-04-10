package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TiNatRec;
import com.roshka.sifen.core.types.TiTipDoc;
import com.roshka.sifen.core.types.PaisType;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class TgCamTrans extends SifenObjectBase {
    private TiNatRec iNatTrans;
    private String dNomTrans;
    private String dRucTrans;
    private short dDVTrans;
    private TiTipDoc iTipIDTrans;
    private String dNumIDTrans;
    private PaisType cNacTrans;
    private String dNumIDChof;
    private String dNomChof;
    private String dDomFisc;
    private String dDirChof;
    private String dNombAg;
    private String dRucAg;
    private short dDVAg;
    private String dDirAge;

    public void setupSOAPElements(SOAPElement gTransp) throws SOAPException {
        SOAPElement gCamTrans = gTransp.addChildElement("gCamTrans");
        gCamTrans.addChildElement("iNatTrans").setTextContent(String.valueOf(this.iNatTrans.getVal()));
        gCamTrans.addChildElement("dNomTrans").setTextContent(this.dNomTrans);

        if (this.iNatTrans.getVal() == 1) {
            gCamTrans.addChildElement("dRucTrans").setTextContent(this.dRucTrans);
            gCamTrans.addChildElement("dDVTrans").setTextContent(String.valueOf(this.dDVTrans));
        } else if (this.iNatTrans.getVal() == 2) {
            gCamTrans.addChildElement("iTipIDTrans").setTextContent(String.valueOf(this.iTipIDTrans.getVal()));
            gCamTrans.addChildElement("dDTipIDTrans").setTextContent(this.iTipIDTrans.getDescripcion());
            gCamTrans.addChildElement("dNumIDTrans").setTextContent(this.dNumIDTrans);
        }

        if (this.cNacTrans != null) {
            gCamTrans.addChildElement("cNacTrans").setTextContent(this.cNacTrans.name());
            gCamTrans.addChildElement("dDesNacTrans").setTextContent(this.cNacTrans.getNombre());
        }

        gCamTrans.addChildElement("dNumIDChof").setTextContent(this.dNumIDChof);
        gCamTrans.addChildElement("dNomChof").setTextContent(this.dNomChof);

        if (this.dDomFisc != null)
            gCamTrans.addChildElement("dDomFisc").setTextContent(this.dDomFisc);

        if (this.dDirChof != null)
            gCamTrans.addChildElement("dDirChof").setTextContent(this.dDirChof);

        if (this.dNombAg != null)
            gCamTrans.addChildElement("dNombAg").setTextContent(this.dNombAg);

        if (this.dRucAg != null) {
            gCamTrans.addChildElement("dRucAg").setTextContent(this.dRucAg);
            gCamTrans.addChildElement("dDVAg").setTextContent(String.valueOf(this.dDVAg));
        }

        if (this.dNombAg != null)
            gCamTrans.addChildElement("dDirAge").setTextContent(this.dDirAge);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iNatTrans":
                this.iNatTrans = TiNatRec.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dNomTrans":
                this.dNomTrans = ResponseUtil.getTextValue(value);
                break;
            case "dRucTrans":
                this.dRucTrans = ResponseUtil.getTextValue(value);
                break;
            case "dDVTrans":
                this.dDVTrans = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "iTipIDTrans":
                this.iTipIDTrans = TiTipDoc.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dNumIDTrans":
                this.dNumIDTrans = ResponseUtil.getTextValue(value);
                break;
            case "cNacTrans":
                this.cNacTrans = PaisType.getByName(ResponseUtil.getTextValue(value));
                break;
            case "dNumIDChof":
                this.dNumIDChof = ResponseUtil.getTextValue(value);
                break;
            case "dNomChof":
                this.dNomChof = ResponseUtil.getTextValue(value);
                break;
            case "dDomFisc":
                this.dDomFisc = ResponseUtil.getTextValue(value);
                break;
            case "dDirChof":
                this.dDirChof = ResponseUtil.getTextValue(value);
                break;
            case "dNombAg":
                this.dNombAg = ResponseUtil.getTextValue(value);
                break;
            case "dRucAg":
                this.dRucAg = ResponseUtil.getTextValue(value);
                break;
            case "dDVAg":
                this.dDVAg = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dDirAge":
                this.dDirAge = ResponseUtil.getTextValue(value);
                break;
        }
    }

    public TiNatRec getiNatTrans() {
        return iNatTrans;
    }

    public void setiNatTrans(TiNatRec iNatTrans) {
        this.iNatTrans = iNatTrans;
    }

    public String getdNomTrans() {
        return dNomTrans;
    }

    public void setdNomTrans(String dNomTrans) {
        this.dNomTrans = dNomTrans;
    }

    public String getdRucTrans() {
        return dRucTrans;
    }

    public void setdRucTrans(String dRucTrans) {
        this.dRucTrans = dRucTrans;
    }

    public short getdDVTrans() {
        return dDVTrans;
    }

    public void setdDVTrans(short dDVTrans) {
        this.dDVTrans = dDVTrans;
    }

    public TiTipDoc getiTipIDTrans() {
        return iTipIDTrans;
    }

    public void setiTipIDTrans(TiTipDoc iTipIDTrans) {
        this.iTipIDTrans = iTipIDTrans;
    }

    public String getdNumIDTrans() {
        return dNumIDTrans;
    }

    public void setdNumIDTrans(String dNumIDTrans) {
        this.dNumIDTrans = dNumIDTrans;
    }

    public PaisType getcNacTrans() {
        return cNacTrans;
    }

    public void setcNacTrans(PaisType cNacTrans) {
        this.cNacTrans = cNacTrans;
    }

    public String getdNumIDChof() {
        return dNumIDChof;
    }

    public void setdNumIDChof(String dNumIDChof) {
        this.dNumIDChof = dNumIDChof;
    }

    public String getdNomChof() {
        return dNomChof;
    }

    public void setdNomChof(String dNomChof) {
        this.dNomChof = dNomChof;
    }

    public String getdDomFisc() {
        return dDomFisc;
    }

    public void setdDomFisc(String dDomFisc) {
        this.dDomFisc = dDomFisc;
    }

    public String getdDirChof() {
        return dDirChof;
    }

    public void setdDirChof(String dDirChof) {
        this.dDirChof = dDirChof;
    }

    public String getdNombAg() {
        return dNombAg;
    }

    public void setdNombAg(String dNombAg) {
        this.dNombAg = dNombAg;
    }

    public String getdRucAg() {
        return dRucAg;
    }

    public void setdRucAg(String dRucAg) {
        this.dRucAg = dRucAg;
    }

    public short getdDVAg() {
        return dDVAg;
    }

    public void setdDVAg(short dDVAg) {
        this.dDVAg = dDVAg;
    }

    public String getdDirAge() {
        return dDirAge;
    }

    public void setdDirAge(String dDirAge) {
        this.dDirAge = dDirAge;
    }
}
