package com.roshka.sifen.core.fields.request.de;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.TiCondOpe;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;

import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TgCamCond extends SifenObjectBase {
    private TiCondOpe iCondOpe;
    private List<TgPaConEIni> gPaConEIniList;
    private TgPagCred gPagCred;

    public void setupSOAPElements(SOAPElement gDtipDE) throws SOAPException {
        SOAPElement gCamCond = gDtipDE.addChildElement("gCamCond");
        gCamCond.addChildElement("iCondOpe").setTextContent(String.valueOf(this.iCondOpe.getVal()));
        gCamCond.addChildElement("dDCondOpe").setTextContent(this.iCondOpe.getDescripcion());

        if (this.iCondOpe.getVal() == 1 || this.gPagCred.getdMonEnt() != null || this.gPaConEIniList != null) {
            for (TgPaConEIni gPaConEIni : Objects.requireNonNull(this.gPaConEIniList)) {
                gPaConEIni.setupSOAPElements(gCamCond);
            }
        }

        if (this.iCondOpe.getVal() == 2)
            this.gPagCred.setupSOAPElements(gCamCond);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "iCondOpe":
                this.iCondOpe = TiCondOpe.getByVal(Short.parseShort(ResponseUtil.getTextValue(value)));
                break;
            case "gPaConEIni":
                if (this.gPaConEIniList == null) {
                    this.gPaConEIniList = new ArrayList<>();
                }
                this.gPaConEIniList.add(SifenObjectFactory.getFromNode(value, TgPaConEIni.class));
                break;
            case "gPagCred":
                this.gPagCred = SifenObjectFactory.getFromNode(value, TgPagCred.class);
                break;
        }
    }

    public TiCondOpe getiCondOpe() {
        return iCondOpe;
    }

    public void setiCondOpe(TiCondOpe iCondOpe) {
        this.iCondOpe = iCondOpe;
    }

    public TgPagCred getgPagCred() {
        return gPagCred;
    }

    public void setgPagCred(TgPagCred gPagCred) {
        this.gPagCred = gPagCred;
    }

    public List<TgPaConEIni> getgPaConEIniList() {
        return gPaConEIniList;
    }

    public void setgPaConEIniList(List<TgPaConEIni> gPaConEIniList) {
        this.gPaConEIniList = gPaConEIniList;
    }
}
