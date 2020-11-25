package com.roshka.sifen.sdk.v150.beans;

import com.roshka.sifen.model.de.*;
import com.roshka.sifen.model.de.types.TTiDE;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa un Documento Electrónico
 * basado en el tipo tDE definido en DE_v150.xsd
 */
public class DocumentoElectronico {
    private LocalDateTime dFecFirma;  // se debe serializar en el formato: yyyy-mm-ddThh:mi:ss
    private short dSisFact;           // sistema que factura (1. Sistema del Cliente,
                                      // 2. Sistema de facturación gratuita de la SET)

    private TgOpeDE gOpeDE;          // campos de operación del documento electrónico
    private TgTimb gTimb;             // campos del timbrado del documento
    private TdDatGralOpe dDatGralOpe;      // datos generales de la operación
    private TgDtipDE gDtipDE;
    private TgTotSub gTotSub;
    private TgCamGen gCamGen;
    private List<TgCamDEAsoc> gCamDEAsocList;

    public void setupSOAPElements(SOAPElement DE) throws SOAPException {
        TTiDE iTiDE = this.gTimb.getTiDE();

        this.gOpeDE.setupSOAPElements(DE, iTiDE);
        this.gTimb.setupSOAPElements(DE);
        this.dDatGralOpe.setupSOAPElements(DE, iTiDE);
        this.gDtipDE.setupSOAPElements(DE, iTiDE, this.dDatGralOpe);

        if (iTiDE.getVal() != 7)
            this.gTotSub.setupSOAPElements(DE, iTiDE, this.dDatGralOpe.getgOpeCom());

        if (this.gCamGen != null)
            this.gCamGen.setupSOAPElements(DE, iTiDE);

        if (iTiDE.getVal() == 4 || iTiDE.getVal() == 5 || iTiDE.getVal() == 6 || ((iTiDE.getVal() == 1 || iTiDE.getVal() == 7) && this.gCamDEAsocList != null)) {
            boolean retencionExists = false;
            for (TgPagCont gPaCondEIni : this.gDtipDE.getgCamCond().getgPaCondEIniList()) {
                if (gPaCondEIni.getiTiPago().getVal() == 10) {
                    retencionExists = true;
                    break;
                }
            }

            for (TgCamDEAsoc gCamDEAsoc : this.gCamDEAsocList) {
                gCamDEAsoc.setupSOAPElements(DE, this.dDatGralOpe.getgOpeCom().getTipTra(), retencionExists);
            }
        }
    }

    public LocalDateTime getdFecFirma() {
        return dFecFirma;
    }

    public void setdFecFirma(LocalDateTime dFecFirma) {
        this.dFecFirma = dFecFirma;
    }

    public short getdSisFact() {
        return dSisFact;
    }

    public void setdSisFact(short dSisFact) {
        this.dSisFact = dSisFact;
    }

    public TgOpeDE getgOpeDE() {
        return gOpeDE;
    }

    public void setgOpeDE(TgOpeDE gOpeDE) {
        this.gOpeDE = gOpeDE;
    }

    public TgTimb getgTimb() {
        return gTimb;
    }

    public void setgTimb(TgTimb gTimb) {
        this.gTimb = gTimb;
    }

    public TdDatGralOpe getdDatGralOpe() {
        return dDatGralOpe;
    }

    public void setdDatGralOpe(TdDatGralOpe dDatGralOpe) {
        this.dDatGralOpe = dDatGralOpe;
    }

    public TgDtipDE getgDtipDE() {
        return gDtipDE;
    }

    public void setgDtipDE(TgDtipDE gDtipDE) {
        this.gDtipDE = gDtipDE;
    }

    public TgTotSub getgTotSub() {
        return gTotSub;
    }

    public void setgTotSub(TgTotSub gTotSub) {
        this.gTotSub = gTotSub;
    }

    public TgCamGen getgCamGen() {
        return gCamGen;
    }

    public void setgCamGen(TgCamGen gCamGen) {
        this.gCamGen = gCamGen;
    }

    public List<TgCamDEAsoc> getgCamDEAsocList() {
        return gCamDEAsocList;
    }

    public void setgCamDEAsocList(List<TgCamDEAsoc> gCamDEAsocList) {
        this.gCamDEAsocList = gCamDEAsocList;
    }
}
