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
            dRUCCons = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRAZCONS)) {
            dRazCons = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DCODESTCONS)) {
            dCodEstCons = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DDESESTCONS)) {
            dDesEstCons = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals(NOMBRE_ELEMENTO_DRUCFACTELEC)) {
            dRUCFactElec = ResponseUtil.getTextValue(value);
        }
    }

    public String getdRUCCons() {
        return dRUCCons;
    }

    public String getdRazCons() {
        return dRazCons;
    }

    public String getdCodEstCons() {
        return dCodEstCons;
    }

    public String getdDesEstCons() {
        return dDesEstCons;
    }

    public String getdRUCFactElec() {
        return dRUCFactElec;
    }
}
