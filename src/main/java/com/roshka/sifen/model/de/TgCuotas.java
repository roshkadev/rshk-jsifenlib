package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.monedas.CMondT;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TgCuotas {
    private CMondT cMoneCuo;
    private BigDecimal dMonCuota;
    private LocalDate dVencCuo;

    public void setupSOAPElements(SOAPElement gPagCred) throws SOAPException {
        SOAPElement gCuotas = gPagCred.addChildElement("gCuotas");
        gCuotas.addChildElement("cMoneCuo").setTextContent(this.cMoneCuo.toString());
        gCuotas.addChildElement("dDMoneCuo").setTextContent(this.cMoneCuo.getDescripcion());
        gCuotas.addChildElement("dMonCuota").setTextContent(String.valueOf(this.dMonCuota));

        if (this.dVencCuo != null)
            gCuotas.addChildElement("dVencCuo").setTextContent(this.dVencCuo.toString());
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
