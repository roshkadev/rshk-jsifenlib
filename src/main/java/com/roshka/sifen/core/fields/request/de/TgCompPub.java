package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDate;

public class TgCompPub extends SifenObjectBase {
    private String dModCont;
    private int dEntCont;
    private short dAnoCont;
    private int dSecCont;
    private LocalDate dFeCodCont;

    public void setupSOAPElements(SOAPElement gCamFE) throws SOAPException {
        SOAPElement gCompPub = gCamFE.addChildElement("gCompPub");
        gCompPub.addChildElement("dModCont").setTextContent(this.dModCont);
        gCompPub.addChildElement("dEntCont").setTextContent(String.valueOf(this.dEntCont));
        gCompPub.addChildElement("dAnoCont").setTextContent(String.valueOf(this.dAnoCont));
        gCompPub.addChildElement("dSecCont").setTextContent(String.valueOf(this.dSecCont));
        gCompPub.addChildElement("dFeCodCont").setTextContent(this.dFeCodCont.toString());
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dModCont":
                this.dModCont = ResponseUtil.getTextValue(value);
                break;
            case "dEntCont":
                this.dEntCont = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dAnoCont":
                this.dAnoCont = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dSecCont":
                this.dSecCont = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dFeCodCont":
                this.dFeCodCont = ResponseUtil.getDateValue(value);
                break;
        }
    }

    public String getdModCont() {
        return dModCont;
    }

    public void setdModCont(String dModCont) {
        this.dModCont = dModCont;
    }

    public int getdEntCont() {
        return dEntCont;
    }

    public void setdEntCont(int dEntCont) {
        this.dEntCont = dEntCont;
    }

    public short getdAnoCont() {
        return dAnoCont;
    }

    public void setdAnoCont(short dAnoCont) {
        this.dAnoCont = dAnoCont;
    }

    public int getdSecCont() {
        return dSecCont;
    }

    public void setdSecCont(int dSecCont) {
        this.dSecCont = dSecCont;
    }

    public LocalDate getdFeCodCont() {
        return dFeCodCont;
    }

    public void setdFeCodCont(LocalDate dFeCodCont) {
        this.dFeCodCont = dFeCodCont;
    }
}
