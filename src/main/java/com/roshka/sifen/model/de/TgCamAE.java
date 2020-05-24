package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TiIndPres;
import com.roshka.sifen.model.de.types.TiNatVen;
import com.roshka.sifen.model.de.types.TiTipDoc;
import com.roshka.sifen.model.departamentos.TDepartamento;

import java.time.LocalDate;

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
    private Short cDisVen;
    private String dDesDisVen;
    private Integer cCiuVen;
    private String dDesCiuven;
    private String dDirProv;
    private TDepartamento cDepProv;
    private Short cDisProv;
    private Integer cCiuProv;
    private String dDesCiuProv;

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

    public Short getcDisVen() {
        return cDisVen;
    }

    public void setcDisVen(Short cDisVen) {
        this.cDisVen = cDisVen;
    }

    public String getdDesDisVen() {
        return dDesDisVen;
    }

    public void setdDesDisVen(String dDesDisVen) {
        this.dDesDisVen = dDesDisVen;
    }

    public Integer getcCiuVen() {
        return cCiuVen;
    }

    public void setcCiuVen(Integer cCiuVen) {
        this.cCiuVen = cCiuVen;
    }

    public String getdDesCiuven() {
        return dDesCiuven;
    }

    public void setdDesCiuven(String dDesCiuven) {
        this.dDesCiuven = dDesCiuven;
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

    public Short getcDisProv() {
        return cDisProv;
    }

    public void setcDisProv(Short cDisProv) {
        this.cDisProv = cDisProv;
    }

    public Integer getcCiuProv() {
        return cCiuProv;
    }

    public void setcCiuProv(Integer cCiuProv) {
        this.cCiuProv = cCiuProv;
    }

    public String getdDesCiuProv() {
        return dDesCiuProv;
    }

    public void setdDesCiuProv(String dDesCiuProv) {
        this.dDesCiuProv = dDesCiuProv;
    }
}
