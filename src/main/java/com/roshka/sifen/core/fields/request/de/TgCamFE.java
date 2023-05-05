package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TiIndPres;
import com.roshka.sifen.core.types.TiTiOpe;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.time.LocalDate;
import java.util.Objects;

public class TgCamFE extends SifenObjectBase {
    private TiIndPres iIndPres;
    private String dDesIndPres;
    private LocalDate dFecEmNR;
    private TgCompPub gCompPub;

    public void setupSOAPElements(SOAPElement gDtipDE, TiTiOpe iTiOpe) throws SOAPException {
        SOAPElement gCamFE = gDtipDE.addChildElement("gCamFE");
        gCamFE.addChildElement("iIndPres").setTextContent(String.valueOf(this.iIndPres.getVal()));
        gCamFE.addChildElement("dDesIndPres").setTextContent(SifenUtil.coalesce(this.iIndPres.getDescripcion(), this.dDesIndPres));

        if (this.dFecEmNR != null)
            gCamFE.addChildElement("dFecEmNR").setTextContent(this.dFecEmNR.toString());

        if (this.gCompPub != null || iTiOpe.getVal() == 3) {
            Objects.requireNonNull(this.gCompPub).setupSOAPElements(gCamFE);
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iIndPres":
                this.iIndPres = TiIndPres.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dDesIndPres":
                this.dDesIndPres = ResponseUtil.getTextValue(value);
                break;
            case "dFecEmNR":
                this.dFecEmNR = ResponseUtil.getDateValue(value);
                break;
            case "gCompPub":
                this.gCompPub = SifenObjectFactory.getFromNode(value, TgCompPub.class);
                break;
        }
    }

    public TiIndPres getiIndPres() {
        return iIndPres;
    }

    public void setiIndPres(TiIndPres iIndPres) {
        this.iIndPres = iIndPres;
    }

    public String getdDesIndPres() {
        return dDesIndPres;
    }

    public void setdDesIndPres(String dDesIndPres) {
        this.dDesIndPres = dDesIndPres;
    }

    public LocalDate getdFecEmNR() {
        return dFecEmNR;
    }

    public void setdFecEmNR(LocalDate dFecEmNR) {
        this.dFecEmNR = dFecEmNR;
    }

    public TgCompPub getgCompPub() {
        return gCompPub;
    }

    public void setgCompPub(TgCompPub gCompPub) {
        this.gCompPub = gCompPub;
    }
}
