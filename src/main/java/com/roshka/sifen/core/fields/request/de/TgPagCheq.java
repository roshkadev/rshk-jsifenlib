package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class TgPagCheq extends SifenObjectBase {
    private String dNumCheq;
    private String dBcoEmi;

    public void setupSOAPElements(SOAPElement gPaConEIni) throws SOAPException {
        SOAPElement gPagCheq = gPaConEIni.addChildElement("gPagCheq");
        gPagCheq.addChildElement("dNumCheq").setTextContent(this.dNumCheq);
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
        this.dNumCheq = SifenUtil.leftPad(dNumCheq, '0', 8);
    }

    public String getdBcoEmi() {
        return dBcoEmi;
    }

    public void setdBcoEmi(String dBcoEmi) {
        this.dBcoEmi = dBcoEmi;
    }
}