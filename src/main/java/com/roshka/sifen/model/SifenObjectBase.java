package com.roshka.sifen.model;

import com.roshka.sifen.exceptions.SifenException;
import org.w3c.dom.Node;

import java.util.logging.Logger;

public abstract class SifenObjectBase {

    private final static Logger logger = Logger.getLogger(SifenObjectBase.class.toString());

    public void setValueFromChildNode(Node value)
        throws SifenException
    {
        // do nothing
    }

    public String getTextValue(Node node) {
        if (node == null)
            return null;
        Node firstChild = node.getFirstChild();
        if (firstChild == null) {
            logger.warning("Node " + node.getNodeName() + " has no children. Returning null for Text Value");
            return null;
        }

        if (firstChild.getNodeType() != Node.TEXT_NODE) {
            logger.warning("Node " + node.getNodeName() + "'s first child is not a TextNode. Returning null for Text Value");
            return null;
        }

        return firstChild.getTextContent();

    }

}
