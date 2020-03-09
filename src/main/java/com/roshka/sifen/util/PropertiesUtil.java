package com.roshka.sifen.util;

import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesUtil {

    private final static Logger logger = Logger.getLogger(PropertiesUtil.class.toString());

    public static int getIntOrDefault(Properties properties, String key, int defval)
    {
        String intStrVal = properties.getProperty(key);
        if (intStrVal == null)
            return defval;
        int ret = defval;
        try {
            ret = Integer.parseInt(intStrVal);
        } catch (NumberFormatException e) {
            logger.warning(
                    "El valor de configuración de la clave [" + key + "] no puede ser convertido a número. Usando el valor por defecto: [" + defval + "]"
            );
        }
        
        return ret;
    }


}
