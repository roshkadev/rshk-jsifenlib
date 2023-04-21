package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TiCondCred;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TgPagCred extends SifenObjectBase {
    private TiCondCred iCondCred;
    private String dPlazoCre;
    private short dCuotas;
    private BigDecimal dMonEnt;
    private List<TgCuotas> gCuotasList;

    public void setupSOAPElements(SOAPElement gCamCond) throws SOAPException {
        SOAPElement gPagCred = gCamCond.addChildElement("gPagCred");
        gPagCred.addChildElement("iCondCred").setTextContent(String.valueOf(this.iCondCred.getVal()));
        gPagCred.addChildElement("dDCondCred").setTextContent(this.iCondCred.getDescripcion());

        if (this.dPlazoCre != null || this.iCondCred.getVal() == 1)
            gPagCred.addChildElement("dPlazoCre").setTextContent(this.dPlazoCre);

        if (this.dCuotas != 0 || this.iCondCred.getVal() == 2)
            gPagCred.addChildElement("dCuotas").setTextContent(String.valueOf(this.dCuotas));

        if (this.dMonEnt != null)
            gPagCred.addChildElement("dMonEnt").setTextContent(String.valueOf(this.dMonEnt));

        if (this.gCuotasList != null) {
            for (TgCuotas gCuotas : this.gCuotasList) {
                gCuotas.setupSOAPElements(gPagCred);
            }
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iCondCred":
                this.iCondCred = TiCondCred.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dPlazoCre":
                this.dPlazoCre = ResponseUtil.getTextValue(value);
                break;
            case "dCuotas":
                this.dCuotas = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dMonEnt":
                this.dMonEnt = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "gCuotas":
                if (this.gCuotasList == null) {
                    this.gCuotasList = new ArrayList<>();
                }
                this.gCuotasList.add(SifenObjectFactory.getFromNode(value, TgCuotas.class));
                break;
        }
    }

    public TiCondCred getiCondCred() {
        return iCondCred;
    }

    public void setiCondCred(TiCondCred iCondCred) {
        this.iCondCred = iCondCred;
    }

    public String getdPlazoCre() {
        return dPlazoCre;
    }

    public void setdPlazoCre(String dPlazoCre) {
        this.dPlazoCre = dPlazoCre;
    }

    public short getdCuotas() {
        return dCuotas;
    }

    public void setdCuotas(short dCuotas) {
        this.dCuotas = dCuotas;
    }

    public BigDecimal getdMonEnt() {
        return dMonEnt;
    }

    public void setdMonEnt(BigDecimal dMonEnt) {
        this.dMonEnt = dMonEnt;
    }

    public List<TgCuotas> getgCuotasList() {
        return gCuotasList;
    }

    public void setgCuotasList(List<TgCuotas> gCuotasList) {
        this.gCuotasList = gCuotasList;
    }
}
