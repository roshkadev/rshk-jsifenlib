package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.monedas.CMondT;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TgCuotas {
    private CMondT cMoneCuo;
    private BigDecimal dMonCuota;
    private LocalDate dVencCuo;

    public void setupSOAPElements(SOAPElement gPagCred) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SOAPElement gCuotas = gPagCred.addChildElement("gCuotas", Constants.SIFEN_NS_PREFIX);
        gCuotas.addChildElement("cMoneCuo", Constants.SIFEN_NS_PREFIX).setTextContent(this.cMoneCuo.toString());
        gCuotas.addChildElement("dDMoneCuo", Constants.SIFEN_NS_PREFIX).setTextContent(this.cMoneCuo.getDescripcion());
        gCuotas.addChildElement("dMonCuota", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dMonCuota));

        if (this.dVencCuo != null)
            gCuotas.addChildElement("dVencCuo", Constants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dVencCuo));
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
