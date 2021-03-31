package com.roshka.sifen.sdk.v150.response.consultaRuc;

import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

public class ContRuc extends SifenObjectBase {
    private String dRUCCons;
    private String dRazCons;
    private String dCodEstCons;
    private String dDesEstCons;
    private String dRUCFactElec;

    @Override
    public void setValueFromChildNode(Node value) {
        switch (value.getLocalName()) {
            case "dRUCCons":
                dRUCCons = ResponseUtil.getTextValue(value);
                break;
            case "dRazCons":
                dRazCons = ResponseUtil.getTextValue(value);
                break;
            case "dCodEstCons":
                dCodEstCons = ResponseUtil.getTextValue(value);
                break;
            case "dDesEstCons":
                dDesEstCons = ResponseUtil.getTextValue(value);
                break;
            case "dRUCFactElec":
                dRUCFactElec = ResponseUtil.getTextValue(value);
                break;
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
