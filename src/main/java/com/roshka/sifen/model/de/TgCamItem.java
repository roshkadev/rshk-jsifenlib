package com.roshka.sifen.model.de;

import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.de.types.*;
import com.roshka.sifen.model.paises.PaisType;
import com.roshka.sifen.model.unidades_medida.TcUniMed;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgCamItem {
    private String dCodInt;
    private short dParAranc;
    private int dNCM;
    private String dDncpG;
    private String dDncpE;
    private int dGtin;
    private int dGtinPq;
    private String dDesProSer;
    private TcUniMed cUniMed;
    private BigDecimal dCantProSer;
    private PaisType cPaisOrig;
    private String dInfItem;
    private TcRelMerc tcRelMerc;
    private BigDecimal dCanQuiMer;
    private BigDecimal dPorQuiMer;
    private String dCDCAnticipo;
    private TgValorItem gValorItem;
    private TgCamIVA gCamIVA;
    private TgRasMerc gRasMerc;
    private TgVehNuevo gVehNuevo;

    public void setupSOAPElements(SOAPElement gDtipDE, TTiDE iTiDE, TgDaGOC gDatGralOpe) throws SOAPException {
        TiTiOpe iTiOpe = gDatGralOpe.getgDatRec().getiTiOpe();
        TTipTra iTipTra = gDatGralOpe.getgOpeCom().getTipTra();
        TdCondTiCam dCondTiCam = gDatGralOpe.getgOpeCom().getdCondTiCam();
        TTImp iTImp = gDatGralOpe.getgOpeCom().getiTImp();

        SOAPElement gCamItem = gDtipDE.addChildElement("gCamItem", Constants.SIFEN_NS_PREFIX);
        gCamItem.addChildElement("dCodInt", Constants.SIFEN_NS_PREFIX).setTextContent(this.dCodInt);

        if (this.dParAranc != 0)
            gCamItem.addChildElement("dParAranc", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dParAranc));

        if (this.dNCM != 0)
            gCamItem.addChildElement("dNCM", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dNCM));

        if (this.dDncpG != null || iTiOpe.getVal() == 3) {
            gCamItem.addChildElement("dDncpG", Constants.SIFEN_NS_PREFIX).setTextContent(this.dDncpG);
            gCamItem.addChildElement("dDncpE", Constants.SIFEN_NS_PREFIX).setTextContent(this.dDncpE);
        }

        if (this.dGtin != 0)
            gCamItem.addChildElement("dGtin", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dGtin));

        if (this.dGtinPq != 0)
            gCamItem.addChildElement("dGtinPq", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dGtinPq));

        gCamItem.addChildElement("dDesProSer", Constants.SIFEN_NS_PREFIX).setTextContent(this.dDesProSer);
        gCamItem.addChildElement("cUniMed", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.cUniMed.getVal()));
        gCamItem.addChildElement("dDesUniMed", Constants.SIFEN_NS_PREFIX).setTextContent(this.cUniMed.getDescripcion());
        gCamItem.addChildElement("dCantProSer", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dCantProSer));

        if (this.cPaisOrig != null) {
            gCamItem.addChildElement("cPaisOrig", Constants.SIFEN_NS_PREFIX).setTextContent(this.cPaisOrig.toString());
            gCamItem.addChildElement("dDesPaisOrig", Constants.SIFEN_NS_PREFIX).setTextContent(this.cPaisOrig.getNombre());
        }

        if (this.dInfItem != null)
            gCamItem.addChildElement("dInfItem", Constants.SIFEN_NS_PREFIX).setTextContent(this.dInfItem);

        if (iTiDE.getVal() != 7 || this.tcRelMerc != null) {
            gCamItem.addChildElement("cRelMerc", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.tcRelMerc.getVal()));
            gCamItem.addChildElement("dDesRelMerc", Constants.SIFEN_NS_PREFIX).setTextContent(this.tcRelMerc.getDescripcion());

        }

        if ((iTiDE.getVal() != 7 || this.tcRelMerc != null) || this.dCanQuiMer != null)
            gCamItem.addChildElement("dCanQuiMer", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dCanQuiMer));

        if ((iTiDE.getVal() != 7 || this.tcRelMerc != null) || this.dPorQuiMer != null)
            gCamItem.addChildElement("dPorQuiMer", Constants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.dPorQuiMer));

        if (iTipTra.getVal() == 9 || this.dCDCAnticipo != null)
            gCamItem.addChildElement("dCDCAnticipo", Constants.SIFEN_NS_PREFIX).setTextContent(this.dCDCAnticipo);

        if (iTiDE.getVal() != 7)
            this.gValorItem.setupSOAPElements(gCamItem, dCondTiCam);

        if (((iTImp.getVal() == 1 || iTImp.getVal() == 3 || iTImp.getVal() == 4 || iTImp.getVal() == 5) && (iTiDE.getVal() != 4 && iTiDE.getVal() != 7)) || iTImp.getVal() != 2)
            this.gCamIVA.setupSOAPElements(gCamItem);

        if (this.gRasMerc != null)
            this.gRasMerc.setupSOAPElements(gCamItem);

        if (this.gVehNuevo != null)
            this.gVehNuevo.setupSOAPElements(gCamItem);
    }

    public String getdCodInt() {
        return dCodInt;
    }

    public void setdCodInt(String dCodInt) {
        this.dCodInt = dCodInt;
    }

    public short getdParAranc() {
        return dParAranc;
    }

    public void setdParAranc(short dParAranc) {
        this.dParAranc = dParAranc;
    }

    public int getdNCM() {
        return dNCM;
    }

    public void setdNCM(int dNCM) {
        this.dNCM = dNCM;
    }

    public String getdDncpG() {
        return dDncpG;
    }

    public void setdDncpG(String dDncpG) {
        this.dDncpG = dDncpG;
    }

    public String getdDncpE() {
        return dDncpE;
    }

    public void setdDncpE(String dDncpE) {
        this.dDncpE = dDncpE;
    }

    public int getdGtin() {
        return dGtin;
    }

    public void setdGtin(int dGtin) {
        this.dGtin = dGtin;
    }

    public int getdGtinPq() {
        return dGtinPq;
    }

    public void setdGtinPq(int dGtinPq) {
        this.dGtinPq = dGtinPq;
    }

    public String getdDesProSer() {
        return dDesProSer;
    }

    public void setdDesProSer(String dDesProSer) {
        this.dDesProSer = dDesProSer;
    }

    public TcUniMed getcUniMed() {
        return cUniMed;
    }

    public void setcUniMed(TcUniMed cUniMed) {
        this.cUniMed = cUniMed;
    }

    public BigDecimal getdCantProSer() {
        return dCantProSer;
    }

    public void setdCantProSer(BigDecimal dCantProSer) {
        this.dCantProSer = dCantProSer;
    }

    public PaisType getcPaisOrig() {
        return cPaisOrig;
    }

    public void setcPaisOrig(PaisType cPaisOrig) {
        this.cPaisOrig = cPaisOrig;
    }

    public String getdInfItem() {
        return dInfItem;
    }

    public void setdInfItem(String dInfItem) {
        this.dInfItem = dInfItem;
    }

    public TcRelMerc getTcRelMerc() {
        return tcRelMerc;
    }

    public void setTcRelMerc(TcRelMerc tcRelMerc) {
        this.tcRelMerc = tcRelMerc;
    }

    public BigDecimal getdCanQuiMer() {
        return dCanQuiMer;
    }

    public void setdCanQuiMer(BigDecimal dCanQuiMer) {
        this.dCanQuiMer = dCanQuiMer;
    }

    public BigDecimal getdPorQuiMer() {
        return dPorQuiMer;
    }

    public void setdPorQuiMer(BigDecimal dPorQuiMer) {
        this.dPorQuiMer = dPorQuiMer;
    }

    public String getdCDCAnticipo() {
        return dCDCAnticipo;
    }

    public void setdCDCAnticipo(String dCDCAnticipo) {
        this.dCDCAnticipo = dCDCAnticipo;
    }

    public TgValorItem getgValorItem() {
        return gValorItem;
    }

    public void setgValorItem(TgValorItem gValorItem) {
        this.gValorItem = gValorItem;
    }

    public TgCamIVA getgCamIVA() {
        return gCamIVA;
    }

    public void setgCamIVA(TgCamIVA gCamIVA) {
        this.gCamIVA = gCamIVA;
    }

    public TgRasMerc getgRasMerc() {
        return gRasMerc;
    }

    public void setgRasMerc(TgRasMerc gRasMerc) {
        this.gRasMerc = gRasMerc;
    }

    public TgVehNuevo getgVehNuevo() {
        return gVehNuevo;
    }

    public void setgVehNuevo(TgVehNuevo gVehNuevo) {
        this.gVehNuevo = gVehNuevo;
    }
}
