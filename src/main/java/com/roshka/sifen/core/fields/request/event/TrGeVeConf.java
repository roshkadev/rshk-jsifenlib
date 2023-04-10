package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TiTipConf;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrGeVeConf extends SifenObjectBase {
    private String Id;
    private TiTipConf iTipConf;
    private LocalDateTime dFecRecep;

    public void setupSOAPElements(SOAPElement gGroupTiEvt) throws SOAPException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement rGeVeConf = gGroupTiEvt.addChildElement("rGeVeConf");

        rGeVeConf.addChildElement("Id").setTextContent(this.Id);
        rGeVeConf.addChildElement("iTipConf").setTextContent(String.valueOf(this.iTipConf.getVal()));

        if (this.dFecRecep != null || this.iTipConf.getVal() == 2)
            rGeVeConf.addChildElement("dFecRecep").setTextContent(this.dFecRecep.format(formatter));
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "Id":
                this.Id = ResponseUtil.getTextValue(value);
                break;
            case "iTipConf":
                this.iTipConf = TiTipConf.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dFecRecep":
                this.dFecRecep = ResponseUtil.getDateTimeValue(value);
                break;
        }
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public TiTipConf getiTipConf() {
        return iTipConf;
    }

    public void setiTipConf(TiTipConf iTipConf) {
        this.iTipConf = iTipConf;
    }

    public LocalDateTime getdFecRecep() {
        return dFecRecep;
    }

    public void setdFecRecep(LocalDateTime dFecRecep) {
        this.dFecRecep = dFecRecep;
    }
}