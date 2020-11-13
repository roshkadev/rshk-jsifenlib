package com.roshka.sifen.model;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.util.SifenExceptionUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.logging.Logger;

public class SifenObjectFactory {

    private final static Logger logger = Logger.getLogger(SifenObjectFactory.class.toString());

    public static <T extends SifenObjectBase> T getFromNode(Node mainNode, Class<T> sifenObjectBase) throws SifenException {
        try {
            T ret = sifenObjectBase.newInstance();
            NodeList childNodes = mainNode.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                ret.setValueFromChildNode(node);
            }
            return ret;
        } catch (InstantiationException e) {
            logger.throwing(SifenObjectFactory.class.getCanonicalName(), "getFromNode", e);
            throw SifenExceptionUtil.unexpectedError(
                    "Error de instanciación al intentar crear un objeto de clase: " + sifenObjectBase.getCanonicalName() + " -> " + e.getLocalizedMessage(), e
            );
        } catch (IllegalAccessException e) {
            logger.throwing(SifenObjectFactory.class.getCanonicalName(), "getFromNode", e);
            throw SifenExceptionUtil.unexpectedError(
                    "Acceso ilegal al intentar crear un objeto de clase: " + sifenObjectBase.getCanonicalName() + " -> " + e.getLocalizedMessage(), e
            );
        }
    }
}
