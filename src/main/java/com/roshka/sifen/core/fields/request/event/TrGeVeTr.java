package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.core.types.TiModTrans;
import com.roshka.sifen.core.types.TiNatRec;
import com.roshka.sifen.core.types.TiTTrans;
import com.roshka.sifen.core.types.TiTipDocRec;
import com.roshka.sifen.core.types.TDepartamento;
import com.roshka.sifen.core.types.TdMotEv;
import com.roshka.sifen.core.types.TdTipIdenVeh;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;

public class TrGeVeTr extends SifenObjectBase {
    private String Id;
    private TdMotEv dMotEv;
    private TDepartamento cDepEnt;
    private short cDisEnt;
    private String dDesDisEnt;
    private short cCiuEnt;
    private String dDesCiuEnt;
    private String dDirEnt;
    private int dNumCas;
    private String dCompDir1;
    private String dNomChof;
    private String dNumIDChof;
    private TiNatRec iNatTrans;
    private String dRucTrans;
    private String dDVTrans;
    private String dNomTrans;
    private TiTipDocRec iTipIDTrans;
    private String dNumIDTrans;
    private TiTTrans iTipTrans;
    private TiModTrans iModTrans;
    private String dTiVehTras;
    private String dMarVeh;
    private TdTipIdenVeh dTipIdenVeh;
    private String dNroIDVeh;
    private String dNroMatVeh;

    public void setupSOAPElements(SOAPElement gGroupTiEvt) throws SOAPException {
        SOAPElement rGeVeTr = gGroupTiEvt.addChildElement("rGeVeTr");

        rGeVeTr.addChildElement("Id").setTextContent(this.Id);
        rGeVeTr.addChildElement("dMotEv").setTextContent(String.valueOf(this.dMotEv.getVal()));

        if (this.cDepEnt != null || this.dMotEv.getVal() == 1) {
            rGeVeTr.addChildElement("cDepEnt").setTextContent(String.valueOf(this.cDepEnt.getVal()));
            rGeVeTr.addChildElement("dDesDepEnt").setTextContent(this.cDepEnt.getDescripcion());
        }

        if ((this.cDisEnt != 0 && this.dDesDisEnt != null) || this.dMotEv.getVal() == 1) {
            rGeVeTr.addChildElement("cDisEnt").setTextContent(String.valueOf(this.cDisEnt));
            rGeVeTr.addChildElement("dDesDisEnt").setTextContent(this.dDesDisEnt);
        }

        if ((this.cCiuEnt != 0 && this.dDesCiuEnt != null) || this.dMotEv.getVal() == 1) {
            rGeVeTr.addChildElement("cCiuEnt").setTextContent(String.valueOf(this.cCiuEnt));
            rGeVeTr.addChildElement("dDesCiuEnt").setTextContent(this.dDesCiuEnt);
        }

        if (this.dDirEnt != null || this.dMotEv.getVal() == 1) {
            rGeVeTr.addChildElement("dDirEnt").setTextContent(this.dDirEnt);
            rGeVeTr.addChildElement("dNumCas").setTextContent(String.valueOf(this.dNumCas));
        }

        if (this.dCompDir1 != null)
            rGeVeTr.addChildElement("dCompDir1").setTextContent(this.dCompDir1);

        if ((this.dNomChof != null && this.dNumIDChof != null) || this.dMotEv.getVal() == 2) {
            rGeVeTr.addChildElement("dNomChof").setTextContent(this.dNomChof);
            rGeVeTr.addChildElement("dNumIDChof").setTextContent(this.dNumIDChof);
        }

        if (this.iNatTrans != null || this.dMotEv.getVal() == 3) {
            rGeVeTr.addChildElement("iNatTrans").setTextContent(String.valueOf(this.iNatTrans.getVal()));
            rGeVeTr.addChildElement("dRucTrans").setTextContent(this.dRucTrans);
            rGeVeTr.addChildElement("dDVTrans").setTextContent(this.dDVTrans);
        }

        if (this.dNomTrans != null || this.dMotEv.getVal() == 3)
            rGeVeTr.addChildElement("dNomTrans").setTextContent(this.dNomTrans);

        if (this.iNatTrans != null && this.iNatTrans.getVal() == 2) {
            rGeVeTr.addChildElement("iTipIDTrans").setTextContent(String.valueOf(this.iTipIDTrans.getVal()));
            rGeVeTr.addChildElement("dDTipIDTrans").setTextContent(this.iTipIDTrans.getDescripcion());
            rGeVeTr.addChildElement("dNumIDTrans").setTextContent(this.dNumIDTrans);
        }

        if (this.iTipTrans != null || this.dMotEv.getVal() == 4) {
            rGeVeTr.addChildElement("iTipTrans").setTextContent(String.valueOf(this.iTipTrans.getVal()));
            rGeVeTr.addChildElement("dDesTipTrans").setTextContent(this.iTipTrans.getDescripcion());
        }

        if (this.iModTrans != null || this.dMotEv.getVal() == 4) {
            rGeVeTr.addChildElement("iModTrans").setTextContent(String.valueOf(this.iModTrans.getVal()));
            rGeVeTr.addChildElement("dDesModTrans").setTextContent(this.iModTrans.getDescripcion());
        }

        if (this.dTiVehTras != null || this.dMotEv.getVal() == 4)
            rGeVeTr.addChildElement("dTiVehTras").setTextContent(this.dTiVehTras);

        if (this.dMarVeh != null || this.dMotEv.getVal() == 4)
            rGeVeTr.addChildElement("dMarVeh").setTextContent(this.dMarVeh);

        if (this.dTipIdenVeh != null || this.dMotEv.getVal() == 4) {
            rGeVeTr.addChildElement("dTipIdenVeh").setTextContent(String.valueOf(this.dTipIdenVeh.getVal()));

            if (this.dTipIdenVeh.getVal() == 1) {
                rGeVeTr.addChildElement("dNroIDVeh").setTextContent(this.dNroIDVeh);
            } else {
                rGeVeTr.addChildElement("dNroMatVeh").setTextContent(this.dNroMatVeh);
            }
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "Id":
                this.Id = ResponseUtil.getTextValue(value);
                break;
            case "dMotEv":
                this.dMotEv = TdMotEv.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "cDepEnt":
                this.cDepEnt = TDepartamento.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "cDisEnt":
                this.cDisEnt = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dDesDisEnt":
                this.dDesDisEnt = ResponseUtil.getTextValue(value);
                break;
            case "cCiuEnt":
                this.cCiuEnt = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "dDesCiuEnt":
                this.dDesCiuEnt = ResponseUtil.getTextValue(value);
                break;
            case "dDirEnt":
                this.dDirEnt = ResponseUtil.getTextValue(value);
                break;
            case "dNumCas":
                this.dNumCas = Integer.parseInt(ResponseUtil.getTextValue(value));
                break;
            case "dCompDir1":
                this.dCompDir1 = ResponseUtil.getTextValue(value);
                break;
            case "dNomChof":
                this.dNomChof = ResponseUtil.getTextValue(value);
                break;
            case "dNumIDChof":
                this.dNumIDChof = ResponseUtil.getTextValue(value);
                break;
            case "iNatTrans":
                this.iNatTrans = TiNatRec.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));;
                break;
            case "dRucTrans":
                this.dRucTrans = ResponseUtil.getTextValue(value);
                break;
            case "dDVTrans":
                this.dDVTrans = ResponseUtil.getTextValue(value);
                break;
            case "dNomTrans":
                this.dNomTrans = ResponseUtil.getTextValue(value);
                break;
            case "iTipIDTrans":
                this.iTipIDTrans = TiTipDocRec.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));;
                break;
            case "dNumIDTrans":
                this.dNumIDTrans = ResponseUtil.getTextValue(value);
                break;
            case "iTipTrans":
                this.iTipTrans = TiTTrans.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));;
                break;
            case "iModTrans":
                this.iModTrans = TiModTrans.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));;
                break;
            case "dTiVehTras":
                this.dTiVehTras = ResponseUtil.getTextValue(value);
                break;
            case "dMarVeh":
                this.dMarVeh = ResponseUtil.getTextValue(value);
                break;
            case "dTipIdenVeh":
                this.dTipIdenVeh = TdTipIdenVeh.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));;
                break;
            case "dNroIDVeh":
                this.dNroIDVeh = ResponseUtil.getTextValue(value);
                break;
            case "dNroMatVeh":
                this.dNroMatVeh = ResponseUtil.getTextValue(value);
                break;
        }
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public TdMotEv getdMotEv() {
        return dMotEv;
    }

    public void setdMotEv(TdMotEv dMotEv) {
        this.dMotEv = dMotEv;
    }

    public TDepartamento getcDepEnt() {
        return cDepEnt;
    }

    public void setcDepEnt(TDepartamento cDepEnt) {
        this.cDepEnt = cDepEnt;
    }

    public short getcDisEnt() {
        return cDisEnt;
    }

    public void setcDisEnt(short cDisEnt) {
        this.cDisEnt = cDisEnt;
    }

    public String getdDesDisEnt() {
        return dDesDisEnt;
    }

    public void setdDesDisEnt(String dDesDisEnt) {
        this.dDesDisEnt = dDesDisEnt;
    }

    public short getcCiuEnt() {
        return cCiuEnt;
    }

    public void setcCiuEnt(short cCiuEnt) {
        this.cCiuEnt = cCiuEnt;
    }

    public String getdDesCiuEnt() {
        return dDesCiuEnt;
    }

    public void setdDesCiuEnt(String dDesCiuEnt) {
        this.dDesCiuEnt = dDesCiuEnt;
    }

    public String getdDirEnt() {
        return dDirEnt;
    }

    public void setdDirEnt(String dDirEnt) {
        this.dDirEnt = dDirEnt;
    }

    public int getdNumCas() {
        return dNumCas;
    }

    public void setdNumCas(int dNumCas) {
        this.dNumCas = dNumCas;
    }

    public String getdCompDir1() {
        return dCompDir1;
    }

    public void setdCompDir1(String dCompDir1) {
        this.dCompDir1 = dCompDir1;
    }

    public String getdNomChof() {
        return dNomChof;
    }

    public void setdNomChof(String dNomChof) {
        this.dNomChof = dNomChof;
    }

    public String getdNumIDChof() {
        return dNumIDChof;
    }

    public void setdNumIDChof(String dNumIDChof) {
        this.dNumIDChof = dNumIDChof;
    }

    public TiNatRec getiNatTrans() {
        return iNatTrans;
    }

    public void setiNatTrans(TiNatRec iNatTrans) {
        this.iNatTrans = iNatTrans;
    }

    public String getdRucTrans() {
        return dRucTrans;
    }

    public void setdRucTrans(String dRucTrans) {
        this.dRucTrans = dRucTrans;
    }

    public String getdDVTrans() {
        return dDVTrans;
    }

    public void setdDVTrans(String dDVTrans) {
        this.dDVTrans = dDVTrans;
    }

    public String getdNomTrans() {
        return dNomTrans;
    }

    public void setdNomTrans(String dNomTrans) {
        this.dNomTrans = dNomTrans;
    }

    public TiTipDocRec getiTipIDTrans() {
        return iTipIDTrans;
    }

    public void setiTipIDTrans(TiTipDocRec iTipIDTrans) {
        this.iTipIDTrans = iTipIDTrans;
    }

    public String getdNumIDTrans() {
        return dNumIDTrans;
    }

    public void setdNumIDTrans(String dNumIDTrans) {
        this.dNumIDTrans = dNumIDTrans;
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

    public String getdTiVehTras() {
        return dTiVehTras;
    }

    public void setdTiVehTras(String dTiVehTras) {
        this.dTiVehTras = dTiVehTras;
    }

    public String getdMarVeh() {
        return dMarVeh;
    }

    public void setdMarVeh(String dMarVeh) {
        this.dMarVeh = dMarVeh;
    }

    public TdTipIdenVeh getdTipIdenVeh() {
        return dTipIdenVeh;
    }

    public void setdTipIdenVeh(TdTipIdenVeh dTipIdenVeh) {
        this.dTipIdenVeh = dTipIdenVeh;
    }

    public String getdNroIDVeh() {
        return dNroIDVeh;
    }

    public void setdNroIDVeh(String dNroIDVeh) {
        this.dNroIDVeh = dNroIDVeh;
    }

    public String getdNroMatVeh() {
        return dNroMatVeh;
    }

    public void setdNroMatVeh(String dNroMatVeh) {
        this.dNroMatVeh = dNroMatVeh;
    }
}