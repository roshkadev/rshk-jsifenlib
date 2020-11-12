package com.roshka.sifen.model.de;

import java.time.LocalDateTime;

/**
 * Clase que representa un Documento Electrónico
 * basado en el tipo tDE definido en DE_v150.xsd
 */
public class TDE {
    private String Id;
    private short dDVId;              // dígito verificador
    private LocalDateTime dFecFirma;  // se debe serializar en el formato: yyyy-mm-ddThh:mi:ss
    private short dSisFact;           // sistema que factura (1. Sistema del Cliente,
                                      // 2. Sistema de facturación gratuita de la SET)

    private TgCOpeDE gOpeDE;          // campos de operación del documento electrónico
    private TgDTim gTimb;             // campos del timbrado del documento
    private TgDaGOC dDatGralOpe;      // datos generales de la operación
    private TgDtipDE gDtipDE;

    private TgTotSub gTotSub;
    private TgCamGen gCamGen;
    private TgCamDEAsoc gCamDEAsoc;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public short getdDVId() {
        return dDVId;
    }

    public void setdDVId(short dDVId) {
        this.dDVId = dDVId;
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

    public TgCOpeDE getgOpeDE() {
        return gOpeDE;
    }

    public void setgOpeDE(TgCOpeDE gOpeDE) {
        this.gOpeDE = gOpeDE;
    }

    public TgDTim getgTimb() {
        return gTimb;
    }

    public void setgTimb(TgDTim gTimb) {
        this.gTimb = gTimb;
    }

    public TgDaGOC getdDatGralOpe() {
        return dDatGralOpe;
    }

    public void setdDatGralOpe(TgDaGOC dDatGralOpe) {
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

    public TgCamDEAsoc getgCamDEAsoc() {
        return gCamDEAsoc;
    }

    public void setgCamDEAsoc(TgCamDEAsoc gCamDEAsoc) {
        this.gCamDEAsoc = gCamDEAsoc;
    }

}
