package com.roshka.sifen.sdk.v150.beans;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.de.*;
import com.roshka.sifen.model.de.types.TTiDE;
import com.roshka.sifen.util.ResponseUtil;
import com.roshka.sifen.util.SifenExceptionUtil;
import com.roshka.sifen.util.SifenUtil;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un Documento Electrónico
 * basado en el tipo tDE definido en DE_v150.xsd
 */
public class DocumentoElectronico extends SifenObjectBase {
    private String Id;
    private String dDVId;
    private LocalDateTime dFecFirma;    // se debe serializar en el formato: yyyy-mm-ddThh:mi:ss
    private short dSisFact;             // sistema que factura (1. Sistema del Cliente,
                                        // 2. Sistema de facturación gratuita de la SET)

    private TgOpeDE gOpeDE;             // campos de operación del documento electrónico
    private TgTimb gTimb;               // campos del timbrado del documento
    private TdDatGralOpe gDatGralOpe;   // datos generales de la operación
    private TgDtipDE gDtipDE;
    private TgTotSub gTotSub;
    private TgCamGen gCamGen;
    private List<TgCamDEAsoc> gCamDEAsocList;

    public void setupSOAPElements(SOAPElement DE) throws SOAPException {
        TTiDE iTiDE = this.gTimb.getiTiDE();

        this.gOpeDE.setupSOAPElements(DE, iTiDE);
        this.gTimb.setupSOAPElements(DE);
        this.gDatGralOpe.setupSOAPElements(DE, iTiDE);
        this.gDtipDE.setupSOAPElements(DE, iTiDE, this.gDatGralOpe);

        if (iTiDE.getVal() != 7)
            this.gTotSub.setupSOAPElements(DE, iTiDE, this.getgDtipDE(), this.gDatGralOpe.getgOpeCom());

        if (this.gCamGen != null)
            this.gCamGen.setupSOAPElements(DE, iTiDE);

        if (iTiDE.getVal() == 4 || iTiDE.getVal() == 5 || iTiDE.getVal() == 6 || ((iTiDE.getVal() == 1 || iTiDE.getVal() == 7) && this.gCamDEAsocList != null)) {
            boolean retencionExists = false;
            for (TgPaConEIni gPaCondEIni : this.gDtipDE.getgCamCond().getgPaCondEIniList()) {
                if (gPaCondEIni.getiTiPago().getVal() == 10) {
                    retencionExists = true;
                    break;
                }
            }

            for (TgCamDEAsoc gCamDEAsoc : this.gCamDEAsocList) {
                gCamDEAsoc.setupSOAPElements(DE, this.gDatGralOpe.getgOpeCom().getiTipTra(), retencionExists);
            }
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dDVId":
                this.dDVId = ResponseUtil.getTextValue(value);
                break;
            case "dFecFirma":
                this.dFecFirma = ResponseUtil.getDateTimeValue(value);
                break;
            case "dSisFact":
                this.dSisFact = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "gOpeDE":
                this.gOpeDE = SifenObjectFactory.getFromNode(value, TgOpeDE.class);
                break;
            case "gTimb":
                this.gTimb = SifenObjectFactory.getFromNode(value, TgTimb.class);
                break;
            case "gDatGralOpe":
                this.gDatGralOpe = SifenObjectFactory.getFromNode(value, TdDatGralOpe.class);
                break;
            case "gDtipDE":
                this.gDtipDE = SifenObjectFactory.getFromNode(value, TgDtipDE.class);
                break;
            case "gTotSub":
                this.gTotSub = SifenObjectFactory.getFromNode(value, TgTotSub.class);
                break;
            case "gCamGen":
                this.gCamGen = SifenObjectFactory.getFromNode(value, TgCamGen.class);
                break;
            case "gCamDEAsoc":
                if (this.gCamDEAsocList == null) {
                    this.gCamDEAsocList = new ArrayList<>();
                }
                this.gCamDEAsocList.add(SifenObjectFactory.getFromNode(value, TgCamDEAsoc.class));
                break;
        }
    }

    public String getId() {
        return Id;
    }

    public String getdDVId() {
        return dDVId;
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

    public TdDatGralOpe getgDatGralOpe() {
        return gDatGralOpe;
    }

    public void setgDatGralOpe(TdDatGralOpe gDatGralOpe) {
        this.gDatGralOpe = gDatGralOpe;
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

    public String generateCDC() throws SifenException {
        // Se intenta la generación del CDC
        String CDC;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            CDC = SifenUtil.leftPad(String.valueOf(this.getgTimb().getiTiDE().getVal()), '0', 2) +
                    SifenUtil.leftPad(this.getgDatGralOpe().getgEmis().getdRucEm(), '0', 8) +
                    this.getgDatGralOpe().getgEmis().getdDVEmi() +
                    this.getgTimb().getdEst() +
                    this.getgTimb().getdPunExp() +
                    SifenUtil.leftPad(String.valueOf(this.getgTimb().getdNumDoc()), '0', 7) +
                    this.getgDatGralOpe().getgEmis().getiTipCont().getVal() +
                    this.getgDatGralOpe().getdFeEmiDE().format(formatter) +
                    this.getgOpeDE().getiTipEmi().getVal() +
                    this.getgOpeDE().getdCodSeg();
        } catch (Exception e) {
            throw SifenExceptionUtil.fieldNotFound("Se produjo un error al generar el CDC. Verificar si todos los campos necesarios están presentes.");
        }

        // Se setean los valores generados en sus lugares correspondientes dentro de la clase
        this.dDVId = SifenUtil.generateDv(CDC);
        this.Id = CDC + this.dDVId;

        return this.Id;
    }
}