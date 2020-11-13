package com.roshka.sifen.sdk.v150.response.consultaRuc;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.response.BaseResponse;
import org.w3c.dom.Node;

public class ConsultaRUCResponse extends BaseResponse {
    private ContRuc xContRUC;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("xContRUC")) {
            xContRUC = SifenObjectFactory.getFromNode(value, ContRuc.class);
        } else {
            super.setValueFromChildNode(value);
        }
    }

    public ContRuc getxContRUC() {
        return xContRUC;
    }

    public void setxContRUC(ContRuc xContRUC) {
        this.xContRUC = xContRUC;
    }
}
