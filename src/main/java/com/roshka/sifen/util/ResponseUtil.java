package com.roshka.sifen.util;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.soap.MessageHelper;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static SOAPMessage parseSoapMessage(SOAPMessage soapMessage) {
        final StringWriter sw = new StringWriter();
        try {
            TransformerFactory.newInstance().newTransformer().transform(new DOMSource(soapMessage.getSOAPPart()), new StreamResult(sw));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

        String xml = sw.toString();
        xml = SifenUtil.unescapeXML(xml)
                .replaceAll("<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?>", "")
                .replaceAll(">[\\s\r\n]*<", "><");

        try {
            soapMessage = MessageHelper.parseMessage(soapMessage.getMimeHeaders(), new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
        } catch (SOAPException | IOException e) {
            logger.info("Se produjo un error al parsear la respuesta XML. Ignorando.");
        }

        return soapMessage;
    }

    public static String getTextValue(Node node) {
        if (node == null)
            return null;

        Node firstChild = node.getFirstChild();
        if (firstChild == null) {
            logger.warning("El nodo " + node.getNodeName() + " no contiene nodos hijos. Retornando");
            return null;
        }

        if (firstChild.getNodeType() != Node.TEXT_NODE) {
            logger.warning("El primer nodo hijo del nodo " + node.getNodeName() + "no es un nodo de texto. Retornando null.");
            return null;
        }

        return firstChild.getTextContent();
    }

    public static LocalDate getDateValue(Node node) {
        String date = getTextValue(node);
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        }
        return null;
    }

    public static LocalDateTime getDateTimeValue(Node node, boolean includesTimezone) {
        String date = getTextValue(node);
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(includesTimezone ? "yyyy-MM-dd'T'HH:mm:ssXXX" : "yyyy-MM-dd'T'HH:mm:ss");
            return LocalDateTime.parse(date, formatter);
        }
        return null;
    }
}