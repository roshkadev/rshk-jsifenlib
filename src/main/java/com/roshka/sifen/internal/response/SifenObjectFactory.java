package com.roshka.sifen.internal.response;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.logging.Logger;

/**
 * Clase encargada de leer los Nodos XML y sus hijos y generar las respuestas formateadas.
 */
public class SifenObjectFactory {
    private final static Logger logger = Logger.getLogger(SifenObjectFactory.class.toString());

    public static <T extends SifenObjectBase> T getFromNode(Node mainNode, Class<T> sifenObjectBase) throws SifenException {
        try {
            T object = sifenObjectBase.newInstance();
            getFromNode(mainNode, object);
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.throwing(SifenObjectFactory.class.getCanonicalName(), "getFromNode", e);
            throw SifenExceptionUtil.unexpectedError(
                    "Error de instanciación al intentar crear un objeto de clase: " + sifenObjectBase.getCanonicalName() + " -> " + e.getLocalizedMessage(), e
            );
        }
    }

    public static <T extends SifenObjectBase> void getFromNode(Node mainNode, T object) throws SifenException {
        NodeList childNodes = mainNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            object.setValueFromChildNode(node);
        }
    }
}