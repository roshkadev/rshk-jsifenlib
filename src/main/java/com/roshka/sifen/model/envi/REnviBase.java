package com.roshka.sifen.model.envi;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;

import javax.xml.soap.SOAPBody;

public abstract class REnviBase {
    private long dId;

    public long getdId() {
        return dId;
    }

    public void setdId(long dId) {
        this.dId = dId;
    }

    public abstract void setupSOAPBody(SOAPBody soapBody, SifenConfig sifenConfig) throws SifenException;
}
