package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class TgCamGen extends SifenObjectBase {
    private String dOrdCompra;
    private String dOrdVta;
    private String dAsiento;
    private TgCamCarg gCamCarg;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE) throws SOAPException {
        SOAPElement gCamGen = DE.addChildElement("gCamGen");
        if (this.dOrdCompra != null)
            gCamGen.addChildElement("dOrdCompra").setTextContent(this.dOrdCompra);

        if (this.dOrdVta != null)
            gCamGen.addChildElement("dOrdVta").setTextContent(this.dOrdVta);

        if (this.dAsiento != null)
            gCamGen.addChildElement("dAsiento").setTextContent(this.dAsiento);

        if ((iTiDE.getVal() == 1 || iTiDE.getVal() == 7) && this.gCamCarg != null)
            this.gCamCarg.setupSOAPElements(gCamGen);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dOrdCompra":
                this.dOrdCompra = ResponseUtil.getTextValue(value);
                break;
            case "dOrdVta":
                this.dOrdVta = ResponseUtil.getTextValue(value);
                break;
            case "dAsiento":
                this.dAsiento = ResponseUtil.getTextValue(value);
                break;
            case "gCamCarg":
                this.gCamCarg = SifenObjectFactory.getFromNode(value, TgCamCarg.class);
                break;
        }
    }

    public String getdOrdCompra() {
        return dOrdCompra;
    }

    public void setdOrdCompra(String dOrdCompra) {
        this.dOrdCompra = dOrdCompra;
    }

    public String getdOrdVta() {
        return dOrdVta;
    }

    public void setdOrdVta(String dOrdVta) {
        this.dOrdVta = dOrdVta;
    }

    public String getdAsiento() {
        return dAsiento;
    }

    public void setdAsiento(String dAsiento) {
        this.dAsiento = dAsiento;
    }

    public TgCamCarg getgCamCarg() {
        return gCamCarg;
    }

    public void setgCamCarg(TgCamCarg gCamCarg) {
        this.gCamCarg = gCamCarg;
    }
}
