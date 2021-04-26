package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.ArrayList;
import java.util.List;

public class TgGrupSeg extends SifenObjectBase {
    private String dCodEmpSeg;
    private List<TgGrupPolSeg> gGrupPolSegList;

    public void setupSOAPElements(SOAPElement gCamEsp) throws SOAPException {
        SOAPElement gGrupSeg = gCamEsp.addChildElement("gGrupSeg");
        if (this.dCodEmpSeg != null)
            gGrupSeg.addChildElement("dCodEmpSeg").setTextContent(this.dCodEmpSeg);

        for (TgGrupPolSeg gGrupPolSeg : this.gGrupPolSegList) {
            gGrupPolSeg.setupSOAPElements(gGrupSeg);
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("dCodEmpSeg")) {
            this.dCodEmpSeg = ResponseUtil.getTextValue(value);
        } else if (value.getLocalName().equals("gGrupPolSeg")) {
            if (this.gGrupPolSegList == null) {
                this.gGrupPolSegList = new ArrayList<>();
            }
            this.gGrupPolSegList.add(SifenObjectFactory.getFromNode(value, TgGrupPolSeg.class));
        }
    }

    public String getdCodEmpSeg() {
        return dCodEmpSeg;
    }

    public void setdCodEmpSeg(String dCodEmpSeg) {
        this.dCodEmpSeg = dCodEmpSeg;
    }

    public List<TgGrupPolSeg> getgGrupPolSegList() {
        return gGrupPolSegList;
    }

    public void setgGrupPolSegList(List<TgGrupPolSeg> gGrupPolSegList) {
        this.gGrupPolSegList = gGrupPolSegList;
    }
}