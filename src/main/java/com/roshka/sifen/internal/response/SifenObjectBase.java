package com.roshka.sifen.internal.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.types.CMondT;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.math.BigDecimal;

/**
 * Clase abstracta heredada por las clases utilizadas como parte de las respuestas a las peticiones.
 */
public abstract class SifenObjectBase {
    public abstract void setValueFromChildNode(Node value) throws SifenException;
}