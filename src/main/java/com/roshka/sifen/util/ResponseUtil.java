package com.roshka.sifen.util;

import com.roshka.sifen.exceptions.SifenException;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.logging.Logger;

public class ResponseUtil {
    private final static Logger logger = Logger.getLogger(ResponseUtil.class.toString());

    public static Node getMainNode(SOAPMessage soapMessage, String nodeName) throws SifenException {
        SOAPBody soapBody;
        try {
            soapBody = soapMessage.getSOAPBody();
        } catch (SOAPException e) {
            throw SifenExceptionUtil.invalidSOAPResponse("El cuerpo del mensaje SOAP es nulo. No se puede obtener el nodo principal.");
        }

        if (soapBody == null)
            throw SifenExceptionUtil.invalidSOAPResponse("El cuerpo del mensaje SOAP es nulo. No se puede obtener el nodo principal.");

        Node node = soapBody.getFirstChild();
        if (node == null)
            throw SifenExceptionUtil.invalidSOAPResponse("El cuerpo del mensaje SOAP tiene el primer nodo nulo.");

        if (node.getNodeName() == null || !node.getLocalName().equalsIgnoreCase(nodeName)) {
            throw SifenExceptionUtil.invalidSOAPResponse(
                    "El nombre del nodo [" + node.getLocalName() + "] no coincide con el nombre esperado [" + nodeName + "]"
            );
        }
        return node;
    }

    public static String getTextValue(Node node) {
        if (node == null)
            return null;

        Node firstChild = node.getFirstChild();
        if (firstChild == null) {
            logger.warning("El nodo " + node.getNodeName() + " no contiene nodos hijos. Retornand");
            return null;
        }

        if (firstChild.getNodeType() != Node.TEXT_NODE) {
            logger.warning("Node " + node.getNodeName() + "'s first child is not a TextNode. Returning null for Text Value");
            return null;
        }

        return firstChild.getTextContent();
    }
}
