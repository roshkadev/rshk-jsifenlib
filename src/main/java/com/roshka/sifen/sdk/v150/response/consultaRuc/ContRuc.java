package com.roshka.sifen.sdk.v150.response.consultaRuc;

import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

public class ContRuc extends SifenObjectBase {
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
    public void setValueFromChildNode(Node value) {
        if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRUCCONS)) {
            setdRUCCons(ResponseUtil.getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRAZCONS)) {
            setdRazCons(ResponseUtil.getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DCODESTCONS)) {
            setdCodEstCons(ResponseUtil.getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DDESESTCONS)) {
            setdDesEstCons(ResponseUtil.getTextValue(value));
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRUCFACTELEC)) {
            setdRUCFactElec(ResponseUtil.getTextValue(value));
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
