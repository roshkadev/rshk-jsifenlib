package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.types.*;
import com.roshka.sifen.model.departamentos.TDepartamento;
import com.roshka.sifen.model.paises.PaisType;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgDatRec {
    private TiNatRec iNatRec;
    private TiTiOpe iTiOpe;
    private PaisType cPaisRec;
    private TiTipCont iTiContRec;
    private String dRucRec;
    private short dDVRec;
    private TiTipDocRec iTipIDRec;
    private String dDTipIDRec;
    private String dNumIDRec;
    private String dNomRec;
    private String dNomFanRec;
    private String dDirRec;
    private int dNumCasRec;
    private TDepartamento cDepRec;
    private short cDisRec;
    private String dDesDisRec;
    private int cCiuRec;
    private String dDesCiuRec;
    private String dTelRec;
    private String dCelRec;
    private String dEmailRec;
    private String dCodCliente;

    public void setupSOAPElements(SOAPElement gDatGralOpe, TTiDE iTiDE) throws SOAPException {
        SOAPElement gDatRec = gDatGralOpe.addChildElement("gDatRec", NamespacesConstants.SIFEN_NS_PREFIX);
        gDatRec.addChildElement("iNatRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iNatRec.getVal()));
        gDatRec.addChildElement("iTiOpe", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iTiOpe.getVal()));
        gDatRec.addChildElement("cPaisRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.cPaisRec.toString());
        gDatRec.addChildElement("dDesPaisRe", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.cPaisRec.getNombre());

        if (this.iNatRec.getVal() == 1) {
            gDatRec.addChildElement("iTiContRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iTiContRec.getVal()));
            gDatRec.addChildElement("dRucRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dRucRec);
            gDatRec.addChildElement("dDVRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dDVRec));
        }

        if (this.iNatRec.getVal() == 2 && this.iTiOpe.getVal() != 4) {
            gDatRec.addChildElement("iTipIDRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.iTipIDRec.getVal()));
            gDatRec.addChildElement("dDTipIDRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(
                    this.iTipIDRec.getDescripcion() != null ? this.iTipIDRec.getDescripcion() : this.dDTipIDRec
            );
            gDatRec.addChildElement("dNumIDRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumIDRec);
        }

        gDatRec.addChildElement("dNomRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNomRec);
        if (this.dNomFanRec != null)
            gDatRec.addChildElement("dNomFanRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNomFanRec);
        if (this.dDirRec != null || iTiDE.getVal() == 7 || this.iTiOpe.getVal() == 4) {
            gDatRec.addChildElement("dDirRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDirRec);
            gDatRec.addChildElement("dNumCasRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dNumCasRec));

            if (this.iTiOpe.getVal() != 4) {
                gDatRec.addChildElement("cDepRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDepRec.getVal()));
                gDatRec.addChildElement("dDesDepRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.cDepRec.getDescripcion());
                gDatRec.addChildElement("cCiuRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cCiuRec));
                gDatRec.addChildElement("dDesCiuRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesCiuRec);
            }
        }
        if (this.cDisRec != 0) {
            gDatRec.addChildElement("cDisRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDisRec));
            gDatRec.addChildElement("dDesDisRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesDisRec);
        }
        if (this.dTelRec != null)
            gDatRec.addChildElement("dTelRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dTelRec);
        if (this.dCelRec != null)
            gDatRec.addChildElement("dCelRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dCelRec);
        if (this.dEmailRec != null)
            gDatRec.addChildElement("dEmailRec", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dEmailRec);
        if (this.dCodCliente != null)
            gDatRec.addChildElement("dCodCliente", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dCodCliente);
    }

    public TiNatRec getiNatRec() {
        return iNatRec;
    }

    public void setiNatRec(TiNatRec iNatRec) {
        this.iNatRec = iNatRec;
    }

    public TiTiOpe getiTiOpe() {
        return iTiOpe;
    }

    public void setiTiOpe(TiTiOpe iTiOpe) {
        this.iTiOpe = iTiOpe;
    }

    public PaisType getcPaisRec() {
        return cPaisRec;
    }

    public void setcPaisRec(PaisType cPaisRec) {
        this.cPaisRec = cPaisRec;
    }

    public TiTipCont getiTiContRec() {
        return iTiContRec;
    }

    public void setiTiContRec(TiTipCont iTiContRec) {
        this.iTiContRec = iTiContRec;
    }

    public String getdRucRec() {
        return dRucRec;
    }

    public void setdRucRec(String dRucRec) {
        this.dRucRec = dRucRec;
    }

    public short getdDVRec() {
        return dDVRec;
    }

    public void setdDVRec(short dDVRec) {
        this.dDVRec = dDVRec;
    }

    public TiTipDocRec getiTipIDRec() {
        return iTipIDRec;
    }

    public void setiTipIDRec(TiTipDocRec iTipIDRec) {
        this.iTipIDRec = iTipIDRec;
    }

    public String getdDTipIDRec() {
        return dDTipIDRec;
    }

    public void setdDTipIDRec(String dDTipIDRec) {
        this.dDTipIDRec = dDTipIDRec;
    }

    public String getdNumIDRec() {
        return dNumIDRec;
    }

    public void setdNumIDRec(String dNumIDRec) {
        this.dNumIDRec = dNumIDRec;
    }

    public String getdNomRec() {
        return dNomRec;
    }

    public void setdNomRec(String dNomRec) {
        this.dNomRec = dNomRec;
    }

    public String getdNomFanRec() {
        return dNomFanRec;
    }

    public void setdNomFanRec(String dNomFanRec) {
        this.dNomFanRec = dNomFanRec;
    }

    public String getdDirRec() {
        return dDirRec;
    }

    public void setdDirRec(String dDirRec) {
        this.dDirRec = dDirRec;
    }

    public int getdNumCasRec() {
        return dNumCasRec;
    }

    public void setdNumCasRec(int dNumCasRec) {
        this.dNumCasRec = dNumCasRec;
    }

    public TDepartamento getcDepRec() {
        return cDepRec;
    }

    public void setcDepRec(TDepartamento cDepRec) {
        this.cDepRec = cDepRec;
    }

    public short getcDisRec() {
        return cDisRec;
    }

    public void setcDisRec(short cDisRec) {
        this.cDisRec = cDisRec;
    }

    public String getdDesDisRec() {
        return dDesDisRec;
    }

    public void setdDesDisRec(String dDesDisRec) {
        this.dDesDisRec = dDesDisRec;
    }

    public int getcCiuRec() {
        return cCiuRec;
    }

    public void setcCiuRec(int cCiuRec) {
        this.cCiuRec = cCiuRec;
    }

    public String getdDesCiuRec() {
        return dDesCiuRec;
    }

    public void setdDesCiuRec(String dDesCiuRec) {
        this.dDesCiuRec = dDesCiuRec;
    }

    public String getdTelRec() {
        return dTelRec;
    }

    public void setdTelRec(String dTelRec) {
        this.dTelRec = dTelRec;
    }

    public String getdCelRec() {
        return dCelRec;
    }

    public void setdCelRec(String dCelRec) {
        this.dCelRec = dCelRec;
    }

    public String getdEmailRec() {
        return dEmailRec;
    }

    public void setdEmailRec(String dEmailRec) {
        this.dEmailRec = dEmailRec;
    }

    public String getdCodCliente() {
        return dCodCliente;
    }

    public void setdCodCliente(String dCodCliente) {
        this.dCodCliente = dCodCliente;
    }
}
