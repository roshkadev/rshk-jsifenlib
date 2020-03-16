package com.roshka.sifen.model;

import com.roshka.sifen.exceptions.SifenException;
import org.w3c.dom.Node;

public class TContenedorRuc extends SifenObjectBase {

    public static final String NOMBRE_ELEMENTO_DRUCCONS = "dRUCCons";
    public static final String NOMBRE_ELEMENTO_DRAZCONS = "dRazCons";
    public static final String NOMBRE_ELEMENTO_DCODESTCONS = "dCodEstCons";
    public static final String NOMBRE_ELEMENTO_DDESESTCONS = "dDesEstCons";
    public static final String NOMBRE_ELEMENTO_DRUCFACTELEC = "dRUCFactElec";

    private String dRUCCons;
    private String dRazCons;
    private String dCodEstCons;
    private String dDesEstCons;
    private String dRUCFactElec;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {

        if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRUCCONS)) {
            setdRUCCons(getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRAZCONS)) {
            setdRazCons(getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DCODESTCONS)) {
            setdCodEstCons(getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DDESESTCONS)) {
            setdDesEstCons(getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRUCFACTELEC)) {
            setdRUCFactElec(getTextValue(value));
        } else {
            super.setValueFromChildNode(value);
        }

    }

    public String getdRUCCons() {
        return dRUCCons;
    }

    public void setdRUCCons(String dRUCCons) {
        this.dRUCCons = dRUCCons;
    }

    public String getdRazCons() {
        return dRazCons;
    }

    public void setdRazCons(String dRazCons) {
        this.dRazCons = dRazCons;
    }

    public String getdCodEstCons() {
        return dCodEstCons;
    }

    public void setdCodEstCons(String dCodEstCons) {
        this.dCodEstCons = dCodEstCons;
    }

    public String getdDesEstCons() {
        return dDesEstCons;
    }

    public void setdDesEstCons(String dDesEstCons) {
        this.dDesEstCons = dDesEstCons;
    }

    public String getdRUCFactElec() {
        return dRUCFactElec;
    }

    public void setdRUCFactElec(String dRUCFactElec) {
        this.dRUCFactElec = dRUCFactElec;
    }
}
