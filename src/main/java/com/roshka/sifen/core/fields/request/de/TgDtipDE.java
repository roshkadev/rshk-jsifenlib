package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.core.types.TiTiOpe;
import com.roshka.sifen.internal.ctx.GenerationCtx;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.util.ArrayList;
import java.util.List;

public class TgDtipDE extends SifenObjectBase {
    private TgCamFE gCamFE;
    private TgCamAE gCamAE;
    private TgCamNCDE gCamNCDE;
    private TgCamNRE gCamNRE;
    private TgCamCond gCamCond;
    private List<TgCamItem> gCamItemList;
    private TgCamEsp gCamEsp;
    private TgTransp gTransp;

    public void setupSOAPElements(GenerationCtx generationCtx, SOAPElement DE, TTiDE iTiDE, TdDatGralOpe gDatGralOpe) throws SOAPException {
        TiTiOpe iTiOpe = gDatGralOpe.getgDatRec().getiTiOpe();

        SOAPElement gDtipDE = DE.addChildElement("gDtipDE");
        if (iTiDE.getVal() == 1)
            this.gCamFE.setupSOAPElements(gDtipDE, iTiOpe);
        else if (iTiDE.getVal() == 4)
            this.gCamAE.setupSOAPElements(gDtipDE);
        else if (iTiDE.getVal() == 5 || iTiDE.getVal() == 6)
            this.gCamNCDE.setupSOAPElements(gDtipDE);
        else if (iTiDE.getVal() == 7)
            this.gCamNRE.setupSOAPElements(gDtipDE);

        if (iTiDE.getVal() == 1 || iTiDE.getVal() == 4)
            this.gCamCond.setupSOAPElements(gDtipDE);

        for (TgCamItem gCamItem : this.gCamItemList) {
            gCamItem.setupSOAPElements(generationCtx, gDtipDE, iTiDE, gDatGralOpe);
        }

        if (this.gCamEsp != null)
            this.gCamEsp.setupSOAPElements(gDtipDE);

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() == 1 && this.gTransp != null))
            this.gTransp.setupSOAPElements(gDtipDE, iTiDE, this.gCamNRE != null ? this.gCamNRE.getiMotEmiNR() : null);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "gCamFE":
                this.gCamFE = SifenObjectFactory.getFromNode(value, TgCamFE.class);
                break;
            case "gCamAE":
                this.gCamAE = SifenObjectFactory.getFromNode(value, TgCamAE.class);
                break;
            case "gCamNCDE":
                this.gCamNCDE = SifenObjectFactory.getFromNode(value, TgCamNCDE.class);
                break;
            case "gCamNRE":
                this.gCamNRE = SifenObjectFactory.getFromNode(value, TgCamNRE.class);
                break;
            case "gCamCond":
                this.gCamCond = SifenObjectFactory.getFromNode(value, TgCamCond.class);
                break;
            case "gCamItem":
                if (this.gCamItemList == null) {
                    this.gCamItemList = new ArrayList<>();
                }
                this.gCamItemList.add(SifenObjectFactory.getFromNode(value, TgCamItem.class));
                break;
            case "gCamEsp":
                this.gCamEsp = SifenObjectFactory.getFromNode(value, TgCamEsp.class);
                break;
            case "gTransp":
                this.gTransp = SifenObjectFactory.getFromNode(value, TgTransp.class);
                break;
        }
    }

    public TgCamFE getgCamFE() {
        return gCamFE;
    }

    public void setgCamFE(TgCamFE gCamFE) {
        this.gCamFE = gCamFE;
    }

    public TgCamAE getgCamAE() {
        return gCamAE;
    }

    public void setgCamAE(TgCamAE gCamAE) {
        this.gCamAE = gCamAE;
    }

    public TgCamNCDE getgCamNCDE() {
        return gCamNCDE;
    }

    public void setgCamNCDE(TgCamNCDE gCamNCDE) {
        this.gCamNCDE = gCamNCDE;
    }

    public TgCamNRE getgCamNRE() {
        return gCamNRE;
    }

    public void setgCamNRE(TgCamNRE gCamNRE) {
        this.gCamNRE = gCamNRE;
    }

    public TgCamCond getgCamCond() {
        return gCamCond;
    }

    public void setgCamCond(TgCamCond gCamCond) {
        this.gCamCond = gCamCond;
    }

    public List<TgCamItem> getgCamItemList() {
        return gCamItemList;
    }

    public void setgCamItemList(List<TgCamItem> gCamItemList) {
        this.gCamItemList = gCamItemList;
    }

    public TgCamEsp getgCamEsp() {
        return gCamEsp;
    }

    public void setgCamEsp(TgCamEsp gCamEsp) {
        this.gCamEsp = gCamEsp;
    }

    public TgTransp getgTransp() {
        return gTransp;
    }

    public void setgTransp(TgTransp gTransp) {
        this.gTransp = gTransp;
    }

}
