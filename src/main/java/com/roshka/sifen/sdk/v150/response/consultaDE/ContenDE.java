package com.roshka.sifen.sdk.v150.response.consultaDE;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.SifenObjectBase;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.util.ResponseUtil;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class ContenDE extends SifenObjectBase {
    private String rDE;
    private String dProtAut;
    private final List<ContenEv> xContEv = new ArrayList<>();

    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "rDE":
                //rDE = ResponseUtil.getTextValue(value);
                rDE = null;
                break;
            case "dProtAut":
                dProtAut = ResponseUtil.getTextValue(value);
                break;
            case "xContEv":
                xContEv.add(SifenObjectFactory.getFromNode(value, ContenEv.class));
                break;
        }
    }

    public String getrDE() {
        return rDE;
    }

    public String getdProtAut() {
        return dProtAut;
    }

    public List<ContenEv> getxContEv() {
        return xContEv;
    }
}