package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.core.types.PaisType;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.core.types.*;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TgTransp extends SifenObjectBase {
    private TiTTrans iTipTrans;
    private TiModTrans iModTrans;
    private TiRespFlete iRespFlete;
    private TcCondNeg cCondNeg;
    private String dNuManif;
    private String dNuDespImp;
    private LocalDate dIniTras;
    private LocalDate dFinTras;
    private PaisType cPaisDest;
    private TgCamSal gCamSal;
    private List<TgCamEnt> gCamEntList;
    private List<TgVehTras> gVehTrasList;
    private TgCamTrans gCamTrans;

    public void setupSOAPElements(SOAPElement gDtipDE, TTiDE iTiDE, TiMotivTras iMotEmiNR) throws SOAPException {
        SOAPElement gTransp = gDtipDE.addChildElement("gTransp");

        if (iTiDE.getVal() == 7 || this.iTipTrans != null) {
            gTransp.addChildElement("iTipTrans").setTextContent(String.valueOf(this.iTipTrans.getVal()));
            gTransp.addChildElement("dDesTipTrans").setTextContent(this.iTipTrans.getDescripcion());
        }

        gTransp.addChildElement("iModTrans").setTextContent(String.valueOf(this.iModTrans.getVal()));
        gTransp.addChildElement("dDesModTrans").setTextContent(this.iModTrans.getDescripcion());
        gTransp.addChildElement("iRespFlete").setTextContent(String.valueOf(this.iRespFlete.getVal()));

        if (this.cCondNeg != null)
            gTransp.addChildElement("cCondNeg").setTextContent(this.cCondNeg.name());

        if (this.dNuManif != null)
            gTransp.addChildElement("dNuManif").setTextContent(this.dNuManif);

        if (iMotEmiNR != null && iMotEmiNR.getVal() == 5)
            gTransp.addChildElement("dNuDespImp").setTextContent(this.dNuDespImp);

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() == 1 && this.dIniTras != null)) {
            gTransp.addChildElement("dIniTras").setTextContent(this.dIniTras.toString());
            gTransp.addChildElement("dFinTras").setTextContent(this.dFinTras.toString());
        }

        if (this.cPaisDest != null) {
            gTransp.addChildElement("cPaisDest").setTextContent(this.cPaisDest.name());
            gTransp.addChildElement("dDesPaisDest").setTextContent(this.cPaisDest.getNombre());
        }

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() == 1 && this.gCamSal != null))
            this.gCamSal.setupSOAPElements(gTransp);

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() != 4 && iTiDE.getVal() != 5 && iTiDE.getVal() != 6 && this.gCamEntList != null)) {
            for (TgCamEnt gCamEnt : this.gCamEntList) {
                gCamEnt.setupSOAPElements(gTransp);
            }
        }

        if (iTiDE.getVal() == 7 || (iTiDE.getVal() != 4 && iTiDE.getVal() != 5 && iTiDE.getVal() != 6 && this.gVehTrasList != null)) {
            for (TgVehTras gVehTras : this.gVehTrasList) {
                gVehTras.setupSOAPElements(gTransp, this.iModTrans);
            }
        }

        if (iTiDE.getVal() == 7 || (this.iModTrans.getVal() == 1 && this.gCamTrans != null)) {
            this.gCamTrans.setupSOAPElements(gTransp);
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iTipTrans":
                this.iTipTrans = TiTTrans.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "iModTrans":
                this.iModTrans = TiModTrans.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "iRespFlete":
                this.iRespFlete = TiRespFlete.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "cCondNeg":
                this.cCondNeg = TcCondNeg.getByDescription(ResponseUtil.getTextValue(value));
                break;
            case "dNuManif":
                this.dNuManif = ResponseUtil.getTextValue(value);
                break;
            case "dNuDespImp":
                this.dNuDespImp = ResponseUtil.getTextValue(value);
                break;
            case "dIniTras":
                this.dIniTras = ResponseUtil.getDateValue(value);
                break;
            case "dFinTras":
                this.dFinTras = ResponseUtil.getDateValue(value);
                break;
            case "cPaisDest":
                this.cPaisDest = PaisType.getByName(ResponseUtil.getTextValue(value));
                break;
            case "gCamSal":
                this.gCamSal = SifenObjectFactory.getFromNode(value, TgCamSal.class);
                break;
            case "gCamEnt":
                if (this.gCamEntList == null) {
                    this.gCamEntList = new ArrayList<>();
                }
                this.gCamEntList.add(SifenObjectFactory.getFromNode(value, TgCamEnt.class));
                break;
            case "gVehTras":
                if (this.gVehTrasList == null) {
                    this.gVehTrasList = new ArrayList<>();
                }
                this.gVehTrasList.add(SifenObjectFactory.getFromNode(value, TgVehTras.class));
                break;
            case "gCamTrans":
                this.gCamTrans = SifenObjectFactory.getFromNode(value, TgCamTrans.class);
                break;
        }
    }

    public TiTTrans getiTipTrans() {
        return iTipTrans;
    }

    public void setiTipTrans(TiTTrans iTipTrans) {
        this.iTipTrans = iTipTrans;
    }

    public TiModTrans getiModTrans() {
        return iModTrans;
    }

    public void setiModTrans(TiModTrans iModTrans) {
        this.iModTrans = iModTrans;
    }

    public TiRespFlete getiRespFlete() {
        return iRespFlete;
    }

    public void setiRespFlete(TiRespFlete iRespFlete) {
        this.iRespFlete = iRespFlete;
    }

    public TcCondNeg getcCondNeg() {
        return cCondNeg;
    }

    public void setcCondNeg(TcCondNeg cCondNeg) {
        this.cCondNeg = cCondNeg;
    }

    public String getdNuManif() {
        return dNuManif;
    }

    public void setdNuManif(String dNuManif) {
        this.dNuManif = dNuManif;
    }

    public String getdNuDespImp() {
        return dNuDespImp;
    }

    public void setdNuDespImp(String dNuDespImp) {
        this.dNuDespImp = dNuDespImp;
    }

    public LocalDate getdIniTras() {
        return dIniTras;
    }

    public void setdIniTras(LocalDate dIniTras) {
        this.dIniTras = dIniTras;
    }

    public LocalDate getdFinTras() {
        return dFinTras;
    }

    public void setdFinTras(LocalDate dFinTras) {
        this.dFinTras = dFinTras;
    }

    public PaisType getcPaisDest() {
        return cPaisDest;
    }

    public void setcPaisDest(PaisType cPaisDest) {
        this.cPaisDest = cPaisDest;
    }

    public TgCamSal getgCamSal() {
        return gCamSal;
    }

    public void setgCamSal(TgCamSal gCamSal) {
        this.gCamSal = gCamSal;
    }

    public List<TgCamEnt> getgCamEntList() {
        return gCamEntList;
    }

    public void setgCamEntList(List<TgCamEnt> gCamEntList) {
        this.gCamEntList = gCamEntList;
    }

    public List<TgVehTras> getgVehTrasList() {
        return gVehTrasList;
    }

    public void setgVehTrasList(List<TgVehTras> gVehTrasList) {
        this.gVehTrasList = gVehTrasList;
    }

    public TgCamTrans getgCamTrans() {
        return gCamTrans;
    }

    public void setgCamTrans(TgCamTrans gCamTrans) {
        this.gCamTrans = gCamTrans;
    }

}
