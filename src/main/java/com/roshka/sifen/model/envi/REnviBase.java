package com.roshka.sifen.model.envi;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;

public abstract class REnviBase {

    private long dId;

    public long getdId() {
        return dId;
    }

    public void setdId(long dId) {
        this.dId = dId;
    }

    public abstract void setupSOAPBody(SOAPBody soapBody)
        throws SOAPException;
}
