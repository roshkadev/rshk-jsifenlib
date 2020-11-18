package com.roshka.sifen.sdk.v150.response.recepcionDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.response.BaseResponse;
import com.roshka.sifen.sdk.v150.response.consultaRuc.ContRuc;
import org.w3c.dom.Node;

public class RecepcionDEResponse extends BaseResponse {
    private ProtDe xProtDE;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("rProtDe")) {
            xProtDE = SifenObjectFactory.getFromNode(value, ProtDe.class);
        }
    }

    public ProtDe getxProtDE() {
        return xProtDE;
    }
}
