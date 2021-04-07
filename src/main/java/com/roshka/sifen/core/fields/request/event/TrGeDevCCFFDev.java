package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import java.time.LocalDateTime;

public class TrGeDevCCFFDev extends SifenObjectBase {
    private String Id;
    private String dNumDevSol;
    private String dNumDevInf;
    private String dNumDevRes;
    private LocalDateTime dFeEmiSol;
    private LocalDateTime dFeEmiInf;
    private LocalDateTime dFeEmiRes;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "Id":
                this.Id = ResponseUtil.getTextValue(value);
                break;
            case "dNumDevSol":
                this.dNumDevSol = ResponseUtil.getTextValue(value);
                break;
            case "dNumDevInf":
                this.dNumDevInf = ResponseUtil.getTextValue(value);
                break;
            case "dNumDevRes":
                this.dNumDevRes = ResponseUtil.getTextValue(value);
                break;
            case "dFeEmiSol":
                this.dFeEmiSol = ResponseUtil.getDateTimeValue(value);
                break;
            case "dFeEmiInf":
                this.dFeEmiInf = ResponseUtil.getDateTimeValue(value);
                break;
            case "dFeEmiRes":
                this.dFeEmiRes = ResponseUtil.getDateTimeValue(value);
                break;
        }
    }

    public String getId() {
        return Id;
    }

    public String getdNumDevSol() {
        return dNumDevSol;
    }

    public String getdNumDevInf() {
        return dNumDevInf;
    }

    public String getdNumDevRes() {
        return dNumDevRes;
    }

    public LocalDateTime getdFeEmiSol() {
        return dFeEmiSol;
    }

    public LocalDateTime getdFeEmiInf() {
        return dFeEmiInf;
    }

    public LocalDateTime getdFeEmiRes() {
        return dFeEmiRes;
    }
}