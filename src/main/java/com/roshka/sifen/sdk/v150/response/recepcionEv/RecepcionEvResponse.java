package com.roshka.sifen.sdk.v150.response.recepcionEv;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.sdk.v150.response.BaseResponse;
import org.w3c.dom.Node;

public class RecepcionEvResponse extends BaseResponse {
    private RetEnviEventoDe rRetEnviEventoDe;

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        if (value.getLocalName().equals("rRetEnviEventoDe")) {
            rRetEnviEventoDe = SifenObjectFactory.getFromNode(value, RetEnviEventoDe.class);
        }
    }

    public RetEnviEventoDe getrRetEnviEventoDe() {
        return rRetEnviEventoDe;
    }
}