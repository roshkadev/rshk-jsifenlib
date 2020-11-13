package com.roshka.sifen.model;

import com.roshka.sifen.exceptions.SifenException;
import org.w3c.dom.Node;

public abstract class SifenObjectBase {
    public abstract void setValueFromChildNode(Node value) throws SifenException;
}
