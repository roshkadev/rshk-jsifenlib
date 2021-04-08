package com.roshka.sifen.core.fields.request.event;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.helpers.SignatureHelper;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.roshka.sifen.internal.Constants.SIFEN_CURRENT_VERSION;

public class TrGesEve extends SifenObjectBase {
    private String Id;
    private LocalDateTime dFecFirma;
    private TgGroupTiEvt gGroupTiEvt;

    public void setupSOAPElements(SOAPElement gGroupGesEve, SifenConfig sifenConfig) throws SOAPException, SifenException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement rGesEve = gGroupGesEve.addChildElement("rGesEve");

        SOAPElement rEve = rGesEve.addChildElement("rEve");
        rEve.setAttribute("Id", this.Id);
        Attr idAttribute = rEve.getAttributeNode("Id");
        rEve.setIdAttributeNode(idAttribute, true);

        rEve.addChildElement("dFecFirma").setTextContent(this.dFecFirma.format(formatter));
        rEve.addChildElement("dVerFor").setTextContent(SIFEN_CURRENT_VERSION);
        this.gGroupTiEvt.setupSOAPElements(rEve);

        // Firma Digital del Evento
        SignatureHelper.signDocument(sifenConfig, rGesEve, this.Id);
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("rEve")) {
            Element rEve = (Element) value;
            this.Id = rEve.getAttribute("Id");

            NodeList childNodes = value.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node.getLocalName().equals("dFecFirma")) {
                    this.dFecFirma = ResponseUtil.getDateTimeValue(node);
                } else if (node.getLocalName().equals("gGroupTiEvt")) {
                    this.gGroupTiEvt = SifenObjectFactory.getFromNode(node, TgGroupTiEvt.class);
                }
            }
        }
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public LocalDateTime getdFecFirma() {
        return dFecFirma;
    }

    public void setdFecFirma(LocalDateTime dFecFirma) {
        this.dFecFirma = dFecFirma;
    }

    public TgGroupTiEvt getgGroupTiEvt() {
        return gGroupTiEvt;
    }

    public void setgGroupTiEvt(TgGroupTiEvt gGroupTiEvt) {
        this.gGroupTiEvt = gGroupTiEvt;
    }
}