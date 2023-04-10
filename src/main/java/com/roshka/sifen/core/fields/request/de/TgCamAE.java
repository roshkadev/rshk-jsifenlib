package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TiNatVen;
import com.roshka.sifen.core.types.TiTipDoc;
import com.roshka.sifen.core.types.TDepartamento;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

/**
 * AUTOFACTURA ELECTRÓNICA
 */
public class TgCamAE extends SifenObjectBase {
    private TiNatVen iNatVen;
    private TiTipDoc iTipIDVen;
    private String dNumIDVen;
    private String dNomVen;
    private String dDirVen;
    private int dNumCasVen;
    private TDepartamento cDepVen;
    private short cDisVen;
    private String dDesDisVen;
    private int cCiuVen;
    private String dDesCiuVen;
    private String dDirProv;
    private TDepartamento cDepProv;
    private short cDisProv;
    private String dDesDisProv;
    private int cCiuProv;
    private String dDesCiuProv;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SOAPElement gCamAE = gDtipDE.addChildElement("gCamAE");
        gCamAE.addChildElement("iNatVen").setTextContent(String.valueOf(this.iNatVen.getVal()));
        gCamAE.addChildElement("dDesNatVen").setTextContent(this.iNatVen.getDescripcion());
        gCamAE.addChildElement("iTipIDVen").setTextContent(String.valueOf(this.iTipIDVen.getVal()));
        gCamAE.addChildElement("dDTipIDVen").setTextContent(this.iTipIDVen.getDescripcion());
        gCamAE.addChildElement("dNumIDVen").setTextContent(this.dNumIDVen);
        gCamAE.addChildElement("dNomVen").setTextContent(this.dNomVen);
        gCamAE.addChildElement("dDirVen").setTextContent(this.dDirVen);
        gCamAE.addChildElement("dNumCasVen").setTextContent(String.valueOf(this.dNumCasVen));
        gCamAE.addChildElement("cDepVen").setTextContent(String.valueOf(this.cDepVen.getVal()));
        gCamAE.addChildElement("dDesDepVen").setTextContent(this.cDepVen.getDescripcion());

        if (this.cDisVen != 0) {
            gCamAE.addChildElement("cDisVen").setTextContent(String.valueOf(this.cDisVen));
            gCamAE.addChildElement("dDesDisVen").setTextContent(this.dDesDisVen);
        }

        gCamAE.addChildElement("cCiuVen").setTextContent(String.valueOf(this.cCiuVen));
        gCamAE.addChildElement("dDesCiuVen").setTextContent(this.dDesCiuVen);
        gCamAE.addChildElement("dDirProv").setTextContent(this.dDirProv);
        gCamAE.addChildElement("cDepProv").setTextContent(String.valueOf(this.cDepProv.getVal()));
        gCamAE.addChildElement("dDesDepProv").setTextContent(this.cDepProv.getDescripcion());

        if (this.cDisProv != 0) {
            gCamAE.addChildElement("cDisProv").setTextContent(String.valueOf(this.cDisProv));
            gCamAE.addChildElement("dDesDisProv").setTextContent(this.dDesDisProv);
        }

        gCamAE.addChildElement("cCiuProv").setTextContent(String.valueOf(this.cCiuProv));
        gCamAE.addChildElement("dDesCiuProv").setTextContent(this.dDesCiuProv);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iNatVen":
                this.iNatVen = TiNatVen.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "iTipIDVen":
                this.iTipIDVen = TiTipDoc.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dNumIDVen":
                this.dNumIDVen = ResponseUtil.getTextValue(value);
                break;
            case "dNomVen":
                this.dNomVen = ResponseUtil.getTextValue(value);
                break;
            case "dDirVen":
                this.dDirVen = ResponseUtil.getTextValue(value);
                break;
            case "dNumCasVen":
                this.dNumCasVen = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "cDepVen":
                this.cDepVen = TDepartamento.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "cDisVen":
                this.cDisVen = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dDesDisVen":
                this.dDesDisVen = ResponseUtil.getTextValue(value);
                break;
            case "cCiuVen":
                this.cCiuVen = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dDesCiuVen":
                this.dDesCiuVen = ResponseUtil.getTextValue(value);
                break;
            case "dDirProv":
                this.dDirProv = ResponseUtil.getTextValue(value);
                break;
            case "cDepProv":
                this.cDepProv = TDepartamento.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "cDisProv":
                this.cDisProv = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dDesDisProv":
                this.dDesDisProv = ResponseUtil.getTextValue(value);
                break;
            case "cCiuProv":
                this.cCiuProv = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dDesCiuProv":
                this.dDesCiuProv = ResponseUtil.getTextValue(value);
                break;
        }
    }

    public TiNatVen getiNatVen() {
        return iNatVen;
    }

    public void setiNatVen(TiNatVen iNatVen) {
        this.iNatVen = iNatVen;
    }

    public TiTipDoc getiTipIDVen() {
        return iTipIDVen;
    }

    public void setiTipIDVen(TiTipDoc iTipIDVen) {
        this.iTipIDVen = iTipIDVen;
    }

    public String getdNumIDVen() {
        return dNumIDVen;
    }

    public void setdNumIDVen(String dNumIDVen) {
        this.dNumIDVen = dNumIDVen;
    }

    public String getdNomVen() {
        return dNomVen;
    }

    public void setdNomVen(String dNomVen) {
        this.dNomVen = dNomVen;
    }

    public String getdDirVen() {
        return dDirVen;
    }

    public void setdDirVen(String dDirVen) {
        this.dDirVen = dDirVen;
    }

    public int getdNumCasVen() {
        return dNumCasVen;
    }

    public void setdNumCasVen(int dNumCasVen) {
        this.dNumCasVen = dNumCasVen;
    }

    public TDepartamento getcDepVen() {
        return cDepVen;
    }

    public void setcDepVen(TDepartamento cDepVen) {
        this.cDepVen = cDepVen;
    }

    public short getcDisVen() {
        return cDisVen;
    }

    public void setcDisVen(short cDisVen) {
        this.cDisVen = cDisVen;
    }

    public String getdDesDisVen() {
        return dDesDisVen;
    }

    public void setdDesDisVen(String dDesDisVen) {
        this.dDesDisVen = dDesDisVen;
    }

    public int getcCiuVen() {
        return cCiuVen;
    }

    public void setcCiuVen(int cCiuVen) {
        this.cCiuVen = cCiuVen;
    }

    public String getdDesCiuVen() {
        return dDesCiuVen;
    }

    public void setdDesCiuVen(String dDesCiuVen) {
        this.dDesCiuVen = dDesCiuVen;
    }

    public String getdDirProv() {
        return dDirProv;
    }

    public void setdDirProv(String dDirProv) {
        this.dDirProv = dDirProv;
    }

    public TDepartamento getcDepProv() {
        return cDepProv;
    }

    public void setcDepProv(TDepartamento cDepProv) {
        this.cDepProv = cDepProv;
    }

    public short getcDisProv() {
        return cDisProv;
    }

    public void setcDisProv(short cDisProv) {
        this.cDisProv = cDisProv;
    }

    public int getcCiuProv() {
        return cCiuProv;
    }

    public void setcCiuProv(int cCiuProv) {
        this.cCiuProv = cCiuProv;
    }

    public String getdDesCiuProv() {
        return dDesCiuProv;
    }

    public void setdDesCiuProv(String dDesCiuProv) {
        this.dDesCiuProv = dDesCiuProv;
    }

    public String getdDesDisProv() {
        return dDesDisProv;
    }

    public void setdDesDisProv(String dDesDisProv) {
        this.dDesDisProv = dDesDisProv;
    }
}
