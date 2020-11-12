package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.departamentos.TDepartamento;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgCamEnt {
    private String dDirLocEnt;
    private short dNumCasEnt;
    private String dComp1Ent;
    private String dComp2Ent;
    private TDepartamento cDepEnt;
    private short cDisEnt;
    private String dDesDisEnt;
    private int cCiuEnt;
    private String dDesCiuEnt;
    private String dTelEnt;

    public void setupSOAPElements(SOAPElement gTransp) throws SOAPException {
        SOAPElement gCamEnt = gTransp.addChildElement("gCamEnt", NamespacesConstants.SIFEN_NS_PREFIX);
        gCamEnt.addChildElement("dDirLocEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDirLocEnt);
        gCamEnt.addChildElement("dNumCasEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dNumCasEnt));

        if (this.dComp1Ent != null)
            gCamEnt.addChildElement("dComp1Ent", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dComp1Ent);

        if (this.dComp2Ent != null)
            gCamEnt.addChildElement("dComp2Ent", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dComp2Ent);

        gCamEnt.addChildElement("cDepEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDepEnt.getVal()));
        gCamEnt.addChildElement("dDesDepEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.cDepEnt.getDescripcion());

        if (this.cDisEnt != 0) {
            gCamEnt.addChildElement("cDisEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cDisEnt));
            gCamEnt.addChildElement("dDesDisEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesDisEnt);
        }

        gCamEnt.addChildElement("cCiuEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cCiuEnt));
        gCamEnt.addChildElement("dDesCiuEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDesCiuEnt);

        if (this.dTelEnt != null)
            gCamEnt.addChildElement("dTelEnt", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dTelEnt);
    }

    public String getdDirLocEnt() {
        return dDirLocEnt;
    }

    public void setdDirLocEnt(String dDirLocEnt) {
        this.dDirLocEnt = dDirLocEnt;
    }

    public short getdNumCasEnt() {
        return dNumCasEnt;
    }

    public void setdNumCasEnt(short dNumCasEnt) {
        this.dNumCasEnt = dNumCasEnt;
    }

    public String getdComp1Ent() {
        return dComp1Ent;
    }

    public void setdComp1Ent(String dComp1Ent) {
        this.dComp1Ent = dComp1Ent;
    }

    public String getdComp2Ent() {
        return dComp2Ent;
    }

    public void setdComp2Ent(String dComp2Ent) {
        this.dComp2Ent = dComp2Ent;
    }

    public TDepartamento getcDepEnt() {
        return cDepEnt;
    }

    public void setcDepEnt(TDepartamento cDepEnt) {
        this.cDepEnt = cDepEnt;
    }

    public short getcDisEnt() {
        return cDisEnt;
    }

    public void setcDisEnt(short cDisEnt) {
        this.cDisEnt = cDisEnt;
    }

    public String getdDesDisEnt() {
        return dDesDisEnt;
    }

    public void setdDesDisEnt(String dDesDisEnt) {
        this.dDesDisEnt = dDesDisEnt;
    }

    public int getcCiuEnt() {
        return cCiuEnt;
    }

    public void setcCiuEnt(int cCiuEnt) {
        this.cCiuEnt = cCiuEnt;
    }

    public String getdDesCiuEnt() {
        return dDesCiuEnt;
    }

    public void setdDesCiuEnt(String dDesCiuEnt) {
        this.dDesCiuEnt = dDesCiuEnt;
    }

    public String getdTelEnt() {
        return dTelEnt;
    }

    public void setdTelEnt(String dTelEnt) {
        this.dTelEnt = dTelEnt;
    }
}
