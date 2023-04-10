package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TiMotivTras;
import com.roshka.sifen.core.types.TiRespEmiNR;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.time.LocalDate;

/**
 * Campos de la nota de remisión electrónica
 */
public class TgCamNRE extends SifenObjectBase {
    private TiMotivTras iMotEmiNR;
    private TiRespEmiNR iRespEmiNR;
    private int dKmR;
    private LocalDate dFecEm;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SOAPElement gCamNRE = gDtipDE.addChildElement("gCamNRE");
        gCamNRE.addChildElement("iMotEmiNR").setTextContent(String.valueOf(this.iMotEmiNR.getVal()));
        gCamNRE.addChildElement("dDesMotEmiNR").setTextContent(this.iMotEmiNR.getDescripcion());
        gCamNRE.addChildElement("iRespEmiNR").setTextContent(String.valueOf(this.iRespEmiNR.getVal()));
        gCamNRE.addChildElement("dDesRespEmiNR").setTextContent(this.iRespEmiNR.getDescripcion());

        if (this.dKmR != 0)
            gCamNRE.addChildElement("dKmR").setTextContent(String.valueOf(this.dKmR));

        if (this.dFecEm != null)
            gCamNRE.addChildElement("dFecEm").setTextContent(this.dFecEm.toString());
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iMotEmiNR":
                this.iMotEmiNR = TiMotivTras.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "iRespEmiNR":
                this.iRespEmiNR = TiRespEmiNR.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dKmR":
                this.dKmR = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dFecEm":
                this.dFecEm = ResponseUtil.getDateValue(value);
                break;
        }
    }

    public TiMotivTras getiMotEmiNR() {
        return iMotEmiNR;
    }

    public void setiMotEmiNR(TiMotivTras iMotEmiNR) {
        this.iMotEmiNR = iMotEmiNR;
    }

    public TiRespEmiNR getiRespEmiNR() {
        return iRespEmiNR;
    }

    public void setiRespEmiNR(TiRespEmiNR iRespEmiNR) {
        this.iRespEmiNR = iRespEmiNR;
    }

    public int getdKmR() {
        return dKmR;
    }

    public void setdKmR(int dKmR) {
        this.dKmR = dKmR;
    }

    public LocalDate getdFecEm() {
        return dFecEm;
    }

    public void setdFecEm(LocalDate dFecEm) {
        this.dFecEm = dFecEm;
    }
}