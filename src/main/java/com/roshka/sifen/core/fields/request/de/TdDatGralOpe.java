package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TdDatGralOpe extends SifenObjectBase {
    private LocalDateTime dFeEmiDE;
    private TgOpeCom gOpeCom;
    private TgEmis gEmis;
    private TgDatRec gDatRec;

    public void setupSOAPElements(SOAPElement DE, TTiDE iTiDE) throws SOAPException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement gDatGralOpe = DE.addChildElement("gDatGralOpe");
        gDatGralOpe.addChildElement("dFeEmiDE").setTextContent(this.dFeEmiDE.format(formatter));
        if (iTiDE.getVal() != 7)
            this.gOpeCom.setupSOAPElements(gDatGralOpe, iTiDE);
        this.gEmis.setupSOAPElements(gDatGralOpe);
        this.gDatRec.setupSOAPElements(gDatGralOpe, iTiDE);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dFeEmiDE":
                this.dFeEmiDE = ResponseUtil.getDateTimeValue(value);
                break;
            case "gOpeCom":
                this.gOpeCom = SifenObjectFactory.getFromNode(value, TgOpeCom.class);
                break;
            case "gEmis":
                this.gEmis = SifenObjectFactory.getFromNode(value, TgEmis.class);
                break;
            case "gDatRec":
                this.gDatRec = SifenObjectFactory.getFromNode(value, TgDatRec.class);
                break;
        }
    }

    public LocalDateTime getdFeEmiDE() {
        return dFeEmiDE;
    }

    public void setdFeEmiDE(LocalDateTime dFeEmiDE) {
        this.dFeEmiDE = dFeEmiDE;
    }

    public TgOpeCom getgOpeCom() {
        return gOpeCom;
    }

    public void setgOpeCom(TgOpeCom gOpeCom) {
        this.gOpeCom = gOpeCom;
    }

    public TgEmis getgEmis() {
        return gEmis;
    }

    public void setgEmis(TgEmis gEmis) {
        this.gEmis = gEmis;
    }

    public TgDatRec getgDatRec() {
        return gDatRec;
    }

    public void setgDatRec(TgDatRec gDatRec) {
        this.gDatRec = gDatRec;
    }
}