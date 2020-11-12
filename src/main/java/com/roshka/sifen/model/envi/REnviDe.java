package com.roshka.sifen.model.envi;

import com.roshka.sifen.model.NamespacesConstants;
import com.roshka.sifen.model.de.TDE;
import com.roshka.sifen.model.de.TgCamDEAsoc;
import com.roshka.sifen.model.de.TgPagCont;
import com.roshka.sifen.model.de.types.TTiDE;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.roshka.sifen.sdk.Constants.SIFEN_CURRENT_VERSION;

public class REnviDe extends REnviBase {
    public static final String TAG_NAME = "rEnviDe";

    private TDE DE;

    @Override
    public void setupSOAPBody(SOAPBody soapBody) throws SOAPException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        // Main Element
        SOAPBodyElement rResEnviDe = soapBody.addBodyElement(
                new QName(NamespacesConstants.SIFEN_NS_URI, TAG_NAME, NamespacesConstants.SIFEN_NS_PREFIX)
        );

        // dId
        rResEnviDe.addChildElement("dId", NamespacesConstants.SIFEN_NS_PREFIX)
                .setTextContent(String.valueOf(this.getdId()));

        // rDe
        SOAPElement rDe = rResEnviDe.addChildElement("xDe", NamespacesConstants.SIFEN_NS_PREFIX)
                .addChildElement("rDe", NamespacesConstants.SIFEN_NS_PREFIX);

        rDe.addChildElement("dVerFor", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(SIFEN_CURRENT_VERSION);

        SOAPElement DE = rDe.addChildElement("DE", NamespacesConstants.SIFEN_NS_PREFIX);
        DE.addAttribute(new QName("Id"), this.DE.getId());
        DE.addChildElement("dDVId", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.DE.getdDVId()));
        DE.addChildElement("dFecFirma",NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(dateFormat.format(this.DE.getdFecFirma()));
        DE.addChildElement("dSisFact", NamespacesConstants.SIFEN_NS_PREFIX).setTextContent(String.valueOf(this.DE.getdSisFact()));

        TTiDE iTiDE = this.DE.getgTimb().getTiDE();
        this.DE.getgOpeDE().setupSOAPElements(DE, iTiDE);
        this.DE.getgTimb().setupSOAPElements(DE);
        this.DE.getdDatGralOpe().setupSOAPElements(DE, iTiDE);
        this.DE.getgDtipDE().setupSOAPElements(DE, iTiDE, this.DE.getdDatGralOpe());

        if (iTiDE.getVal() != 7)
            this.DE.getgTotSub().setupSOAPElements(DE, iTiDE, this.DE.getdDatGralOpe().getgOpeCom());

        if (this.DE.getgCamGen() != null)
            this.DE.getgCamGen().setupSOAPElements(DE, iTiDE);

        if (iTiDE.getVal() == 4 || iTiDE.getVal() == 5 || iTiDE.getVal() == 6 || ((iTiDE.getVal() == 1 || iTiDE.getVal() == 7) && this.DE.getgCamDEAsocList().size() > 0)) {
            boolean retencionExists = false;
            for (TgPagCont gPaCondEIni : this.DE.getgDtipDE().getgCamCond().getgPaCondEIniList()) {
                if (gPaCondEIni.getiTiPago().getVal() == 10) {
                    retencionExists = true;
                    break;
                }
            }

            for (TgCamDEAsoc gCamDEAsoc : this.DE.getgCamDEAsocList()) {
                gCamDEAsoc.setupSOAPElements(DE, this.DE.getdDatGralOpe().getgOpeCom().getTipTra(), retencionExists);
            }
        }
    }

    // E1. Campos Factura Electrónica
    private String iIndPres;
    private String dDesIndPres;
    private String dFecEmNR;
    // E1.1. Campos de informaciones de Compras Públicas
    private String dModCont;
    private String dEntCont;
    private String dAnoCont;
    private String dSecCont;
    private String dFeCodCont;

    // E7. Campos de la Condición
    private String iCondOpe;
    private String dDCondOpe;
    // E7.1. Campos de la Forma de Pago
    private String iTiPago;
    private String dDesTiPag;
    private String dMonTiPag;
    private String cMoneTiPag;
    private String dDMoneTiPag;
    private String dTiCamTiPag;

    // E8. Campos de los ítems
    private String dCodInt;
    private String dParAranc;
    private String dNCM;
    private String dDncpG;
    private String dDncpE;
    private String dGtin;
    private String dGtinPq;
    private String dDesProSer;
    private String cUniMed;
    private String dDesUniMed;
    private String dCantProSer;
    private String cPaisOrig;
    private String dDesPaisOrig;
    private String dInfItem;
    private String cRelMerc;
    private String dDesRelMerc;
    private String dCanQuiMer;
    private String dPorQuiMer;
    private String dCDCAnticipo;
}
