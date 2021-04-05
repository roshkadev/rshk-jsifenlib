package com.roshka.sifen.sdk.v150.response.consultaDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.beans.DocumentoElectronico;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class ContenDE extends SifenObjectBase {
    private DocumentoElectronico DE;
    private String dProtAut;
    private final List<ContenEv> xContEv = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "rDE":
                NodeList childNodes = value.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node node = childNodes.item(i);
                    if (node.getLocalName().equals("DE")) {
                        DE = SifenObjectFactory.getFromNode(node, DocumentoElectronico.class);
                        DE.generateCDC();
                        break;
                    }
                }
                break;
            case "dProtAut":
                dProtAut = ResponseUtil.getTextValue(value);
                break;
            case "xContEv":
                xContEv.add(SifenObjectFactory.getFromNode(value, ContenEv.class));
                break;
        }
    }

    public DocumentoElectronico getDE() {
        return DE;
    }

    public String getdProtAut() {
        return dProtAut;
    }

    public List<ContenEv> getxContEv() {
        return xContEv;
    }
}