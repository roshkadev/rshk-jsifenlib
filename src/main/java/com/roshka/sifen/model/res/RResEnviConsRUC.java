package com.roshka.sifen.model.res;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.TContenedorRuc;
import org.w3c.dom.Node;

public class RResEnviConsRUC extends RResBase {

    public static final String NOMBRE_ELEMENTO_XCONTRUC = "xContRUC";

    TContenedorRuc xContRUC;

    public TContenedorRuc getxContRUC() {
        return xContRUC;
    }

    public void setxContRUC(TContenedorRuc xContRUC) {
        this.xContRUC = xContRUC;
    }

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals(NOMBRE_ELEMENTO_XCONTRUC)) {
            xContRUC = SifenObjectFactory.getFromNode(value, TContenedorRuc.class);
        } else {
            super.setValueFromChildNode(value);
        }

    }
}
