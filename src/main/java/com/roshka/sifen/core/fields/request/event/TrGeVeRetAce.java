package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;

public class TrGeVeRetAce extends SifenObjectBase {
    private String Id;
    private int dNumTimRet;
    private String dEstRet;
    private String dPunExpRet;
    private String dNumDocRet;
    private String dCodConRet;
    private LocalDateTime dFeEmiRet;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "Id":
                this.Id = ResponseUtil.getTextValue(value);
                break;
            case "dNumTimRet":
                this.dNumTimRet = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dEstRet":
                this.dEstRet = ResponseUtil.getTextValue(value);
                break;
            case "dPunExpRet":
                this.dPunExpRet = ResponseUtil.getTextValue(value);
                break;
            case "dNumDocRet":
                this.dNumDocRet = ResponseUtil.getTextValue(value);
                break;
            case "dCodConRet":
                this.dCodConRet = ResponseUtil.getTextValue(value);
                break;
            case "dFeEmiRet":
                this.dFeEmiRet = ResponseUtil.getDateTimeValue(value);
                break;
        }
    }

    public String getId() {
        return Id;
    }

    public int getdNumTimRet() {
        return dNumTimRet;
    }

    public String getdEstRet() {
        return dEstRet;
    }

    public String getdPunExpRet() {
        return dPunExpRet;
    }

    public String getdNumDocRet() {
        return dNumDocRet;
    }

    public String getdCodConRet() {
        return dCodConRet;
    }

    public LocalDateTime getdFeEmiRet() {
        return dFeEmiRet;
    }
}