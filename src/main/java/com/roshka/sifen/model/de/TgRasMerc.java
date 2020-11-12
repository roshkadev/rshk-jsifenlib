package com.roshka.sifen.model.de;

import com.roshka.sifen.model.NamespacesConstants;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TgRasMerc {
    private String dNumLote;
    private LocalDate dVencMerc;
    private String dNSerie;
    private String dNumPedi;
    private String dNumSegui;
    private String dNomImp;
    private String dDirImp;
    private String dNumFir;
    private String dNumReg;
    private String dNumRegEntCom;

    public void setupSOAPElements(SOAPElement gCamItem) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SOAPElement gRasMerc = gCamItem.addChildElement("gRasMerc", NamespacesConstants.SIFEN_NS_PREFIX);
        if (this.dNumLote != null)
            gRasMerc.addChildElement("dNumLote", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumLote);

        if (this.dVencMerc != null)
            gRasMerc.addChildElement("dVencMerc", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.dVencMerc));

        if (this.dNSerie != null)
            gRasMerc.addChildElement("dNSerie", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNSerie);

        if (this.dNumPedi != null)
            gRasMerc.addChildElement("dNumPedi", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumPedi);

        if (this.dNumSegui != null)
            gRasMerc.addChildElement("dNumSegui", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumSegui);

        if (this.dNomImp != null)
            gRasMerc.addChildElement("dNomImp", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNomImp);

        if (this.dDirImp != null)
            gRasMerc.addChildElement("dDirImp", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dDirImp);

        if (this.dNumFir != null)
            gRasMerc.addChildElement("dNumFir", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumFir);

        if (this.dNumReg != null)
            gRasMerc.addChildElement("dNumReg", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumReg);

        if (this.dNumRegEntCom != null)
            gRasMerc.addChildElement("dNumRegEntCom", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(this.dNumRegEntCom);
    }

    public String getdNumLote() {
        return dNumLote;
    }

    public void setdNumLote(String dNumLote) {
        this.dNumLote = dNumLote;
    }

    public LocalDate getdVencMerc() {
        return dVencMerc;
    }

    public void setdVencMerc(LocalDate dVencMerc) {
        this.dVencMerc = dVencMerc;
    }

    public String getdNSerie() {
        return dNSerie;
    }

    public void setdNSerie(String dNSerie) {
        this.dNSerie = dNSerie;
    }

    public String getdNumPedi() {
        return dNumPedi;
    }

    public void setdNumPedi(String dNumPedi) {
        this.dNumPedi = dNumPedi;
    }

    public String getdNumSegui() {
        return dNumSegui;
    }

    public void setdNumSegui(String dNumSegui) {
        this.dNumSegui = dNumSegui;
    }

    public String getdNomImp() {
        return dNomImp;
    }

    public void setdNomImp(String dNomImp) {
        this.dNomImp = dNomImp;
    }

    public String getdDirImp() {
        return dDirImp;
    }

    public void setdDirImp(String dDirImp) {
        this.dDirImp = dDirImp;
    }

    public String getdNumFir() {
        return dNumFir;
    }

    public void setdNumFir(String dNumFir) {
        this.dNumFir = dNumFir;
    }

    public String getdNumReg() {
        return dNumReg;
    }

    public void setdNumReg(String dNumReg) {
        this.dNumReg = dNumReg;
    }

    public String getdNumRegEntCom() {
        return dNumRegEntCom;
    }

    public void setdNumRegEntCom(String dNumRegEntCom) {
        this.dNumRegEntCom = dNumRegEntCom;
    }
}
