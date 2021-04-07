package com.roshka.sifen.model.event;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TrGeVeDisconf extends SifenObjectBase {
    private String Id;
    private String mOtEve;

    public void setupSOAPElements(SOAPElement gGroupTiEvt) throws SOAPException {
        SOAPElement rGeVeDisconf = gGroupTiEvt.addChildElement("rGeVeDisconf");

        rGeVeDisconf.addChildElement("Id").setTextContent(this.Id);
        rGeVeDisconf.addChildElement("mOtEve").setTextContent(this.mOtEve);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("Id")) {
            this.Id = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals("mOtEve")) {
            this.mOtEve = ResponseUtil.getTextValue(value);
        }
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getmOtEve() {
        return mOtEve;
    }

    public void setmOtEve(String mOtEve) {
        this.mOtEve = mOtEve;
    }
}