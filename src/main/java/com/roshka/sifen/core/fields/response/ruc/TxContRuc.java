package com.roshka.sifen.core.fields.response.ruc;

import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

public class TxContRuc extends SifenObjectBase {
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
