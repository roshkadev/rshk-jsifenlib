package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.TiNatVen;
import com.roshka.sifen.model.de.types.TiTipDoc;
import com.roshka.sifen.model.departamentos.TDepartamento;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

/**
 * AUTOFACTURA ELECTRÓNICA
 */
public class TgCamAE {
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
        SOAPElement gCamAE = gDtipDE.addChildElement("gCamAE", NamespacesConstants.SIFEN_NS_PREFIX);
        gCamAE.addChildElement("iNatVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iNatVen.getVal()));
        gCamAE.addChildElement("dDesNatVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.iNatVen.getDescripcion());
        gCamAE.addChildElement("iTipIDVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iTipIDVen.getVal()));
        gCamAE.addChildElement("dDTipIDVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.iTipIDVen.getDescripcion());
        gCamAE.addChildElement("dNumIDVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumIDVen);
        gCamAE.addChildElement("dNomVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNomVen);
        gCamAE.addChildElement("dDirVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDirVen);
        gCamAE.addChildElement("dNumCasVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dNumCasVen));
        gCamAE.addChildElement("cDepVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDepVen.getVal()));
        gCamAE.addChildElement("dDesDepVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.cDepVen.getDescripcion());

        if (this.cDisVen != 0) {
            gCamAE.addChildElement("cDisVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDisVen));
            gCamAE.addChildElement("dDesDisVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesDisVen);
        }

        gCamAE.addChildElement("cCiuVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cCiuVen));
        gCamAE.addChildElement("dDesCiuVen", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesCiuVen);
        gCamAE.addChildElement("dDirProv", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDirProv);
        gCamAE.addChildElement("cDepProv", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDepProv.getVal()));
        gCamAE.addChildElement("dDesDepProv", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.cDepProv.getDescripcion());

        if (this.cDisProv != 0) {
            gCamAE.addChildElement("cDisProv", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDisProv));
            gCamAE.addChildElement("dDesDisProv", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesDisProv);
        }

        gCamAE.addChildElement("cCiuProv", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cCiuProv));
        gCamAE.addChildElement("dDesCiuProv", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesCiuProv);
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
