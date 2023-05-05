package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.CMondT;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TgCuotas extends SifenObjectBase {
    private CMondT cMoneCuo;
    private BigDecimal dMonCuota;
    private LocalDate dVencCuo;

    public void setupSOAPElements(SOAPElement gPagCred) throws SOAPException {
        SOAPElement gCuotas = gPagCred.addChildElement("gCuotas");
        gCuotas.addChildElement("cMoneCuo").setTextContent(this.cMoneCuo.name());
        gCuotas.addChildElement("dDMoneCuo").setTextContent(this.cMoneCuo.getDescripcion());
        gCuotas.addChildElement("dMonCuota").setTextContent(String.valueOf(this.dMonCuota));

        if (this.dVencCuo != null)
            gCuotas.addChildElement("dVencCuo").setTextContent(this.dVencCuo.toString());
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "cMoneCuo":
                this.cMoneCuo = CMondT.getByName(ResponseUtil.getTextValue(value));
                break;
            case "dMonCuota":
                this.dMonCuota = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dVencCuo":
                this.dVencCuo = ResponseUtil.getDateValue(value);
                break;
        }
    }

    public CMondT getcMoneCuo() {
        return cMoneCuo;
    }

    public void setcMoneCuo(CMondT cMoneCuo) {
        this.cMoneCuo = cMoneCuo;
    }

    public BigDecimal getdMonCuota() {
        return dMonCuota;
    }

    public void setdMonCuota(BigDecimal dMonCuota) {
        this.dMonCuota = dMonCuota;
    }

    public LocalDate getdVencCuo() {
        return dVencCuo;
    }

    public void setdVencCuo(LocalDate dVencCuo) {
        this.dVencCuo = dVencCuo;
    }
}
