package com.roshka.sifen.core.fields.response.de;

import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TxContenDE extends SifenObjectBase {
    private DocumentoElectronico DE;
    private String dProtAut;
    private TxContenEv xContEv;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "rDE":
                NodeList childNodes = value.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node node = childNodes.item(i);
                    if (node.getLocalName().equals("DE")) {
                        DE = SifenObjectFactory.getFromNode(node, DocumentoElectronico.class);
                        DE.obtenerCDC();
                    } else if (node.getLocalName().equals("gCamFuFD")) {
                        getQrLink(node);
                    }
                }
                break;
            case "dProtAut":
                dProtAut = ResponseUtil.getTextValue(value);
                break;
            case "xContEv":
                xContEv = SifenObjectFactory.getFromNode(value, TxContenEv.class);
                break;
        }
    }

    public DocumentoElectronico getDE() {
        return DE;
    }

    public String getdProtAut() {
        return dProtAut;
    }

    public TxContenEv getxContEv() {
        return xContEv;
    }

    private void getQrLink(Node gCamFuFD) {
        NodeList childNodes = gCamFuFD.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getLocalName().equals("dCarQR")) {
                DE.setEnlaceQR(ResponseUtil.getTextValue(node));
            }
        }
    }
}