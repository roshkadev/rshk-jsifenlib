package com.roshka.sifen.model.de;

import com.roshka.sifen.model.departamentos.TDepartamento;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgCamSal {
    private String dDirLocSal;
    private short dNumCasSal;
    private String dComp1Sal;
    private String dComp2Sal;
    private TDepartamento cDepSal;
    private short cDisSal;
    private String dDesDisSal;
    private int cCiuSal;
    private String dDesCiuSal;
    private String dTelSal;

    public void setupSOAPElements(SOAPElement gTransp) throws SOAPException {
        SOAPElement gCamSal = gTransp.addChildElement("gCamSal");
        gCamSal.addChildElement("dDirLocSal").setTextContent(this.dDirLocSal);
        gCamSal.addChildElement("dNumCasSal").setTextContent(String.valueOf(this.dNumCasSal));

        if (this.dComp1Sal != null)
            gCamSal.addChildElement("dComp1Sal").setTextContent(this.dComp1Sal);

        if (this.dComp2Sal != null)
            gCamSal.addChildElement("dComp2Sal").setTextContent(this.dComp2Sal);

        gCamSal.addChildElement("cDepSal").setTextContent(String.valueOf(this.cDepSal.getVal()));
        gCamSal.addChildElement("dDesDepSal").setTextContent(this.cDepSal.getDescripcion());

        if (this.cDisSal != 0) {
            gCamSal.addChildElement("cDisSal").setTextContent(String.valueOf(this.cDisSal));
            gCamSal.addChildElement("dDesDisSal").setTextContent(this.dDesDisSal);
        }

        gCamSal.addChildElement("cCiuSal").setTextContent(String.valueOf(this.cCiuSal));
        gCamSal.addChildElement("dDesCiuSal").setTextContent(this.dDesCiuSal);

        if (this.dTelSal != null)
            gCamSal.addChildElement("dTelSal").setTextContent(this.dTelSal);
    }

    public String getdDirLocSal() {
        return dDirLocSal;
    }

    public void setdDirLocSal(String dDirLocSal) {
        this.dDirLocSal = dDirLocSal;
    }

    public short getdNumCasSal() {
        return dNumCasSal;
    }

    public void setdNumCasSal(short dNumCasSal) {
        this.dNumCasSal = dNumCasSal;
    }

    public String getdComp1Sal() {
        return dComp1Sal;
    }

    public void setdComp1Sal(String dComp1Sal) {
        this.dComp1Sal = dComp1Sal;
    }

    public String getdComp2Sal() {
        return dComp2Sal;
    }

    public void setdComp2Sal(String dComp2Sal) {
        this.dComp2Sal = dComp2Sal;
    }

    public TDepartamento getcDepSal() {
        return cDepSal;
    }

    public void setcDepSal(TDepartamento cDepSal) {
        this.cDepSal = cDepSal;
    }

    public short getcDisSal() {
        return cDisSal;
    }

    public void setcDisSal(short cDisSal) {
        this.cDisSal = cDisSal;
    }

    public String getdDesDisSal() {
        return dDesDisSal;
    }

    public void setdDesDisSal(String dDesDisSal) {
        this.dDesDisSal = dDesDisSal;
    }

    public int getcCiuSal() {
        return cCiuSal;
    }

    public void setcCiuSal(int cCiuSal) {
        this.cCiuSal = cCiuSal;
    }

    public String getdDesCiuSal() {
        return dDesCiuSal;
    }

    public void setdDesCiuSal(String dDesCiuSal) {
        this.dDesCiuSal = dDesCiuSal;
    }

    public String getdTelSal() {
        return dTelSal;
    }

    public void setdTelSal(String dTelSal) {
        this.dTelSal = dTelSal;
    }
}
