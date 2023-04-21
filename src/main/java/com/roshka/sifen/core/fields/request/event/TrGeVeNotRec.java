package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TiNatRec;
import com.roshka.sifen.core.types.TiTipDocRec;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrGeVeNotRec extends SifenObjectBase {
    private String Id;
    private LocalDateTime dFecEmi;
    private LocalDateTime dFecRecep;
    private TiNatRec iTipRec;
    private String dNomRec;
    private String dRucRec;
    private String dDVRec;
    private TiTipDocRec dTipIDRec;
    private String dNumID;
    private BigDecimal dTotalGs;

    public void setupSOAPElements(SOAPElement gGroupTiEvt) throws SOAPException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement rGeVeNotRec = gGroupTiEvt.addChildElement("rGeVeNotRec");

        rGeVeNotRec.addChildElement("Id").setTextContent(this.Id);
        rGeVeNotRec.addChildElement("dFecEmi").setTextContent(this.dFecEmi.format(formatter));
        rGeVeNotRec.addChildElement("dFecRecep").setTextContent(this.dFecRecep.format(formatter));
        rGeVeNotRec.addChildElement("iTipRec").setTextContent(String.valueOf(this.iTipRec.getVal()));
        rGeVeNotRec.addChildElement("dNomRec").setTextContent(this.dNomRec);

        if (this.iTipRec.getVal() == 1) {
            rGeVeNotRec.addChildElement("dRucRec").setTextContent(this.dRucRec);
            rGeVeNotRec.addChildElement("dDVRec").setTextContent(this.dDVRec);
        } else {
            rGeVeNotRec.addChildElement("dTipIDRec").setTextContent(String.valueOf(this.dTipIDRec.getVal()));
            rGeVeNotRec.addChildElement("dNumID").setTextContent(this.dNumID);
        }

        rGeVeNotRec.addChildElement("dTotalGs").setTextContent(String.valueOf(this.dTotalGs));
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "Id":
                this.Id = ResponseUtil.getTextValue(value);
                break;
            case "dFecEmi":
                this.dFecEmi = ResponseUtil.getDateTimeValue(value);
                break;
            case "dFecRecep":
                this.dFecRecep = ResponseUtil.getDateTimeValue(value);
                break;
            case "iTipRec":
                this.iTipRec = TiNatRec.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dNomRec":
                this.dNomRec = ResponseUtil.getTextValue(value);
                break;
            case "dRucRec":
                this.dRucRec = ResponseUtil.getTextValue(value);
                break;
            case "dDVRec":
                this.dDVRec = ResponseUtil.getTextValue(value);
                break;
            case "dTipIDRec":
                this.dTipIDRec = TiTipDocRec.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dNumID":
                this.dNumID = ResponseUtil.getTextValue(value);
                break;
            case "dTotalGs":
                this.dTotalGs = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
        }
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public LocalDateTime getdFecEmi() {
        return dFecEmi;
    }

    public void setdFecEmi(LocalDateTime dFecEmi) {
        this.dFecEmi = dFecEmi;
    }

    public LocalDateTime getdFecRecep() {
        return dFecRecep;
    }

    public void setdFecRecep(LocalDateTime dFecRecep) {
        this.dFecRecep = dFecRecep;
    }

    public TiNatRec getiTipRec() {
        return iTipRec;
    }

    public void setiTipRec(TiNatRec iTipRec) {
        this.iTipRec = iTipRec;
    }

    public String getdNomRec() {
        return dNomRec;
    }

    public void setdNomRec(String dNomRec) {
        this.dNomRec = dNomRec;
    }

    public String getdRucRec() {
        return dRucRec;
    }

    public void setdRucRec(String dRucRec) {
        this.dRucRec = dRucRec;
    }

    public String getdDVRec() {
        return dDVRec;
    }

    public void setdDVRec(String dDVRec) {
        this.dDVRec = dDVRec;
    }

    public TiTipDocRec getdTipIDRec() {
        return dTipIDRec;
    }

    public void setdTipIDRec(TiTipDocRec dTipIDRec) {
        this.dTipIDRec = dTipIDRec;
    }

    public String getdNumID() {
        return dNumID;
    }

    public void setdNumID(String dNumID) {
        this.dNumID = dNumID;
    }

    public BigDecimal getdTotalGs() {
        return dTotalGs;
    }

    public void setdTotalGs(BigDecimal dTotalGs) {
        this.dTotalGs = dTotalGs;
    }
}