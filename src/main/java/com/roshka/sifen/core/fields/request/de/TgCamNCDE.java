package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TiMotEmi;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgCamNCDE extends SifenObjectBase {
    private TiMotEmi iMotEmi;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SOAPElement gCamNCDE = gDtipDE.addChildElement("gCamNCDE");
        gCamNCDE.addChildElement("iMotEmi").setTextContent(String.valueOf(this.iMotEmi.getVal()));
        gCamNCDE.addChildElement("dDesMotEmi").setTextContent(this.iMotEmi.getDescripcion());
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("iMotEmi")) {
            this.iMotEmi = TiMotEmi.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
        }
    }

    public TiMotEmi getiMotEmi() {
        return iMotEmi;
    }

    public void setiMotEmi(TiMotEmi iMotEmi) {
        this.iMotEmi = iMotEmi;
    }
}