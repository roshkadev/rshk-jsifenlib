package com.roshka.sifen.model.de;

import com.roshka.sifen.model.de.types.TcRelMerc;
import com.roshka.sifen.model.paises.PaisType;
import com.roshka.sifen.model.unidades_medida.TcUniMed;

import java.math.BigDecimal;

public class TgCamItem {

    private String dCodInt;
    private Short dParAranc;
    private Integer dNCM;
    private String dDncpG;
    private String dDncpE;
    private Integer dGtin;
    private Integer dGtinPq;
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

    public String getdCodInt() {
        return dCodInt;
    }

    public void setdCodInt(String dCodInt) {
        this.dCodInt = dCodInt;
    }

    public Short getdParAranc() {
        return dParAranc;
    }

    public void setdParAranc(Short dParAranc) {
        this.dParAranc = dParAranc;
    }

    public Integer getdNCM() {
        return dNCM;
    }

    public void setdNCM(Integer dNCM) {
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

    public Integer getdGtin() {
        return dGtin;
    }

    public void setdGtin(Integer dGtin) {
        this.dGtin = dGtin;
    }

    public Integer getdGtinPq() {
        return dGtinPq;
    }

    public void setdGtinPq(Integer dGtinPq) {
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
