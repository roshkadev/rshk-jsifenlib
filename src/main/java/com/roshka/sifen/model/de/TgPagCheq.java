package com.roshka.sifen.model.de;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.util.ResponseUtil;
import com.roshka.sifen.util.SifenUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgPagCheq extends SifenObjectBase {
    private String dNumCheq;
    private String dBcoEmi;

    public void setupSOAPElements(SOAPElement gPaConEIni) throws SOAPException {
        SOAPElement gPagCheq = gPaConEIni.addChildElement("gPagCheq");
        gPagCheq.addChildElement("dNumCheq").setTextContent(SifenUtil.leftPad(this.dNumCheq, '0', 8));
        gPagCheq.addChildElement("dBcoEmi").setTextContent(this.dBcoEmi);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dNumCheq")) {
            this.dNumCheq = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals("dBcoEmi")) {
            this.dBcoEmi = ResponseUtil.getTextValue(value);
        }
    }

    public String getdNumCheq() {
        return dNumCheq;
    }

    public void setdNumCheq(String dNumCheq) {
        this.dNumCheq = dNumCheq;
    }

    public String getdBcoEmi() {
        return dBcoEmi;
    }

    public void setdBcoEmi(String dBcoEmi) {
        this.dBcoEmi = dBcoEmi;
    }
}