package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.*;
import com.roshka.sifen.internal.ctx.GenerationCtx;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.math.BigDecimal;

public class TgCamItem extends SifenObjectBase {
    private String dCodInt;
    private short dParAranc;
    private int dNCM;
    private String dDncpG;
    private String dDncpE;
    private long dGtin;
    private long dGtinPq;
    private String dDesProSer;
    private TcUniMed cUniMed;
    private BigDecimal dCantProSer;
    private PaisType cPaisOrig;
    private String dInfItem;
    private TcRelMerc cRelMerc;
    private BigDecimal dCanQuiMer;
    private BigDecimal dPorQuiMer;
    private String dCDCAnticipo;
    private TgValorItem gValorItem;
    private TgCamIVA gCamIVA;
    private TgRasMerc gRasMerc;
    private TgVehNuevo gVehNuevo;

    public void setupSOAPElements(GenerationCtx generationCtx, SOAPElement gDtipDE, TTiDE iTiDE, TdDatGralOpe gDatGralOpe) throws SOAPException {
        TiTiOpe iTiOpe = gDatGralOpe.getgDatRec().getiTiOpe();
        TTipTra iTipTra = null;
        TdCondTiCam dCondTiCam = null;
        TTImp iTImp = null;
        CMondT cMoneOpe = null;
        if (gDatGralOpe.getgOpeCom() != null) {
            iTipTra = gDatGralOpe.getgOpeCom().getiTipTra();
            dCondTiCam = gDatGralOpe.getgOpeCom().getdCondTiCam();
            iTImp = gDatGralOpe.getgOpeCom().getiTImp();
            cMoneOpe = gDatGralOpe.getgOpeCom().getcMoneOpe();
        }

        SOAPElement gCamItem = gDtipDE.addChildElement("gCamItem");
        gCamItem.addChildElement("dCodInt").setTextContent(this.dCodInt);

        if (this.dParAranc != 0)
            gCamItem.addChildElement("dParAranc").setTextContent(String.valueOf(this.dParAranc));

        if (this.dNCM != 0)
            gCamItem.addChildElement("dNCM").setTextContent(String.valueOf(this.dNCM));

        if (this.dDncpG != null || iTiOpe.getVal() == 3) {
            gCamItem.addChildElement("dDncpG").setTextContent(this.dDncpG);
            gCamItem.addChildElement("dDncpE").setTextContent(this.dDncpE);
        }

        if (this.dGtin != 0)
            gCamItem.addChildElement("dGtin").setTextContent(String.valueOf(this.dGtin));

        if (this.dGtinPq != 0)
            gCamItem.addChildElement("dGtinPq").setTextContent(String.valueOf(this.dGtinPq));

        gCamItem.addChildElement("dDesProSer").setTextContent(this.dDesProSer);
        gCamItem.addChildElement("cUniMed").setTextContent(String.valueOf(this.cUniMed.getVal()));
        gCamItem.addChildElement("dDesUniMed").setTextContent(this.cUniMed.getAbreviatura());
        gCamItem.addChildElement("dCantProSer").setTextContent(String.valueOf(this.dCantProSer));

        if (this.cPaisOrig != null) {
            gCamItem.addChildElement("cPaisOrig").setTextContent(this.cPaisOrig.name());
            gCamItem.addChildElement("dDesPaisOrig").setTextContent(this.cPaisOrig.getNombre());
        }

        if (this.dInfItem != null)
            gCamItem.addChildElement("dInfItem").setTextContent(this.dInfItem);

        if (this.cRelMerc != null) {
            gCamItem.addChildElement("cRelMerc").setTextContent(String.valueOf(this.cRelMerc.getVal()));
            gCamItem.addChildElement("dDesRelMerc").setTextContent(this.cRelMerc.getDescripcion());
        }

        if ((iTiDE.getVal() == 7 && this.cRelMerc != null) || this.dCanQuiMer != null)
            gCamItem.addChildElement("dCanQuiMer").setTextContent(String.valueOf(this.dCanQuiMer));

        if ((iTiDE.getVal() == 7 && this.cRelMerc != null) || this.dPorQuiMer != null)
            gCamItem.addChildElement("dPorQuiMer").setTextContent(String.valueOf(this.dPorQuiMer));

        if ((iTipTra != null && iTipTra.getVal() == 9) || this.dCDCAnticipo != null)
            gCamItem.addChildElement("dCDCAnticipo").setTextContent(this.dCDCAnticipo);

        if (iTiDE.getVal() != 7) {
            this.gValorItem.setupSOAPElements(gCamItem, iTiDE, dCondTiCam, iTImp, this.dCantProSer, cMoneOpe);
        }

        if (iTImp != null && (iTImp.getVal() == 1 || iTImp.getVal() == 3 || iTImp.getVal() == 4 || iTImp.getVal() == 5) && iTiDE.getVal() != 4 && iTiDE.getVal() != 7)
            this.gCamIVA.setupSOAPElements(generationCtx, gCamItem, cMoneOpe, this.gValorItem.getgValorRestaItem().getdTotOpeItem());

        if (this.gRasMerc != null)
            this.gRasMerc.setupSOAPElements(gCamItem);

        if (this.gVehNuevo != null)
            this.gVehNuevo.setupSOAPElements(gCamItem);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dCodInt":
                this.dCodInt = ResponseUtil.getTextValue(value);
                break;
            case "dParAranc":
                this.dParAranc = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dNCM":
                this.dNCM = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dDncpG":
                this.dDncpG = ResponseUtil.getTextValue(value);
                break;
            case "dDncpE":
                this.dDncpE = ResponseUtil.getTextValue(value);
                break;
            case "dGtin":
                this.dGtin = Long.parseLong(ResponseUtil.getTextValue(value));
                break;
            case "dGtinPq":
                this.dGtinPq = Long.parseLong(ResponseUtil.getTextValue(value));
                break;
            case "dDesProSer":
                this.dDesProSer = ResponseUtil.getTextValue(value);
                break;
            case "cUniMed":
                this.cUniMed = TcUniMed.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dCantProSer":
                this.dCantProSer = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "cPaisOrig":
                this.cPaisOrig = PaisType.getByName(ResponseUtil.getTextValue(value));
                break;
            case "dInfItem":
                this.dInfItem = ResponseUtil.getTextValue(value);
                break;
            case "cRelMerc":
                this.cRelMerc = TcRelMerc.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "dCanQuiMer":
                this.dCanQuiMer = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dPorQuiMer":
                this.dPorQuiMer = new BigDecimal(ResponseUtil.getTextValue(value));
                break;
            case "dCDCAnticipo":
                this.dCDCAnticipo = ResponseUtil.getTextValue(value);
                break;
            case "gValorItem":
                this.gValorItem = SifenObjectFactory.getFromNode(value, TgValorItem.class);
                break;
            case "gCamIVA":
                this.gCamIVA = SifenObjectFactory.getFromNode(value, TgCamIVA.class);
                break;
            case "gRasMerc":
                this.gRasMerc = SifenObjectFactory.getFromNode(value, TgRasMerc.class);
                break;
            case "gVehNuevo":
                this.gVehNuevo = SifenObjectFactory.getFromNode(value, TgVehNuevo.class);
                break;
        }
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
        this.dDncpG = SifenUtil.leftPad(dDncpG, '0', 8);
    }

    public String getdDncpE() {
        return dDncpE;
    }

    public void setdDncpE(String dDncpE) {
        this.dDncpE = dDncpE;
    }

    public long getdGtin() {
        return dGtin;
    }

    public void setdGtin(long dGtin) {
        this.dGtin = dGtin;
    }

    public long getdGtinPq() {
        return dGtinPq;
    }

    public void setdGtinPq(long dGtinPq) {
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

    public TcRelMerc getcRelMerc() {
        return cRelMerc;
    }

    public void setcRelMerc(TcRelMerc cRelMerc) {
        this.cRelMerc = cRelMerc;
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
