package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class TgGroupTiEvt extends SifenObjectBase {
    private TrGeVeCan rGeVeCan;
    private TrGeVeInu rGeVeInu;
    private TrGeVeNotRec rGeVeNotRec;
    private TrGeVeConf rGeVeConf;
    private TrGeVeDisconf rGeVeDisconf;
    private TrGeVeDescon rGeVeDescon;
    private TrGeVeTr rGeVeTr;

    // Automáticos
    private TrGeVeRetAce rGeVeRetAce;
    private TrGeVeRetAnu rGeVeRetAnu;
    private TrGeVeCCFF rGeVeCCFF;
    private TrGeDevCCFFCue rGeDevCCFFCue;
    private TrGeDevCCFFDev rGeDevCCFFDev;
    private TrGeVeAnt rGeVeAnt;
    private TrGeVeRem rGeVeRem;

    public void setupSOAPElements(SOAPElement rEve) throws SOAPException {
        SOAPElement gGroupTiEvt = rEve.addChildElement("gGroupTiEvt");

        if (this.rGeVeCan != null) {
            this.rGeVeCan.setupSOAPElements(gGroupTiEvt);

        } else if (this.rGeVeInu != null) {
            this.rGeVeInu.setupSOAPElements(gGroupTiEvt);

        } else if (this.rGeVeNotRec != null) {
            this.rGeVeNotRec.setupSOAPElements(gGroupTiEvt);

        } else if (this.rGeVeConf != null) {
            this.rGeVeConf.setupSOAPElements(gGroupTiEvt);

        } else if (this.rGeVeDisconf != null) {
            this.rGeVeDisconf.setupSOAPElements(gGroupTiEvt);

        } else if (this.rGeVeDescon != null) {
            this.rGeVeDescon.setupSOAPElements(gGroupTiEvt);

        } else if (this.rGeVeTr != null) {
            this.rGeVeTr.setupSOAPElements(gGroupTiEvt);
        }
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "rGeVeCan":
                this.rGeVeCan = SifenObjectFactory.getFromNode(value, TrGeVeCan.class);
                break;
            case "rGeVeInu":
                this.rGeVeInu = SifenObjectFactory.getFromNode(value, TrGeVeInu.class);
                break;
            case "rGeVeNotRec":
                this.rGeVeNotRec = SifenObjectFactory.getFromNode(value, TrGeVeNotRec.class);
                break;
            case "rGeVeConf":
                this.rGeVeConf = SifenObjectFactory.getFromNode(value, TrGeVeConf.class);
                break;
            case "rGeVeDisconf":
                this.rGeVeDisconf = SifenObjectFactory.getFromNode(value, TrGeVeDisconf.class);
                break;
            case "rGeVeDescon":
                this.rGeVeDescon = SifenObjectFactory.getFromNode(value, TrGeVeDescon.class);
                break;
            case "rGeVeTr":
                this.rGeVeTr = SifenObjectFactory.getFromNode(value, TrGeVeTr.class);
                break;
            case "rGeVeRetAce":
                this.rGeVeRetAce = SifenObjectFactory.getFromNode(value, TrGeVeRetAce.class);
                break;
            case "rGeVeRetAnu":
                this.rGeVeRetAnu = SifenObjectFactory.getFromNode(value, TrGeVeRetAnu.class);
                break;
            case "rGeVeCCFF":
                this.rGeVeCCFF = SifenObjectFactory.getFromNode(value, TrGeVeCCFF.class);
                break;
            case "rGeDevCCFFCue":
                this.rGeDevCCFFCue = SifenObjectFactory.getFromNode(value, TrGeDevCCFFCue.class);
                break;
            case "rGeDevCCFFDev":
                this.rGeDevCCFFDev = SifenObjectFactory.getFromNode(value, TrGeDevCCFFDev.class);
                break;
            case "rGeVeAnt":
                this.rGeVeAnt = SifenObjectFactory.getFromNode(value, TrGeVeAnt.class);
                break;
            case "rGeVeRem":
                this.rGeVeRem = SifenObjectFactory.getFromNode(value, TrGeVeRem.class);
                break;
        }
    }

    public TrGeVeCan getrGeVeCan() {
        return rGeVeCan;
    }

    public void setrGeVeCan(TrGeVeCan rGeVeCan) {
        this.rGeVeCan = rGeVeCan;
    }

    public TrGeVeInu getrGeVeInu() {
        return rGeVeInu;
    }

    public void setrGeVeInu(TrGeVeInu rGeVeInu) {
        this.rGeVeInu = rGeVeInu;
    }

    public TrGeVeNotRec getrGeVeNotRec() {
        return rGeVeNotRec;
    }

    public void setrGeVeNotRec(TrGeVeNotRec rGeVeNotRec) {
        this.rGeVeNotRec = rGeVeNotRec;
    }

    public TrGeVeConf getrGeVeConf() {
        return rGeVeConf;
    }

    public void setrGeVeConf(TrGeVeConf rGeVeConf) {
        this.rGeVeConf = rGeVeConf;
    }

    public TrGeVeDisconf getrGeVeDisconf() {
        return rGeVeDisconf;
    }

    public void setrGeVeDisconf(TrGeVeDisconf rGeVeDisconf) {
        this.rGeVeDisconf = rGeVeDisconf;
    }

    public TrGeVeDescon getrGeVeDescon() {
        return rGeVeDescon;
    }

    public void setrGeVeDescon(TrGeVeDescon rGeVeDescon) {
        this.rGeVeDescon = rGeVeDescon;
    }

    public TrGeVeTr getrGeVeTr() {
        return rGeVeTr;
    }

    public void setrGeVeTr(TrGeVeTr rGeVeTr) {
        this.rGeVeTr = rGeVeTr;
    }

    public TrGeVeRetAce getrGeVeRetAce() {
        return rGeVeRetAce;
    }

    public TrGeVeRetAnu getrGeVeRetAnu() {
        return rGeVeRetAnu;
    }

    public TrGeVeCCFF getrGeVeCCFF() {
        return rGeVeCCFF;
    }

    public TrGeDevCCFFCue getrGeDevCCFFCue() {
        return rGeDevCCFFCue;
    }

    public TrGeDevCCFFDev getrGeDevCCFFDev() {
        return rGeDevCCFFDev;
    }

    public TrGeVeAnt getrGeVeAnt() {
        return rGeVeAnt;
    }

    public TrGeVeRem getrGeVeRem() {
        return rGeVeRem;
    }
}