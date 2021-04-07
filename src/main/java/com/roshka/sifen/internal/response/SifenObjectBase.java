package com.roshka.sifen.internal.response;

import com.roshka.sifen.core.exceptions.SifenException;
import org.w3c.dom.Node;

public abstract class SifenObjectBase {
    public abstract void setValueFromChildNode(Node value) throws SifenException;
}