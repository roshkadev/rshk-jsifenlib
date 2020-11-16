package com.roshka.sifen;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.sdk.v150.beans.DocumentoElectronico;
import com.roshka.sifen.sdk.v150.request.RecepcionDE;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.sdk.v150.request.ConsultaRUC;
import com.roshka.sifen.util.SifenExceptionUtil;

import java.util.logging.Logger;

public class Sifen {
    private final static Logger logger = Logger.getLogger(Sifen.class.toString());
    private static SifenConfig sifenConfig = null;

    public static void setSifenConfig(SifenConfig newSifenConfig) {
        sifenConfig = newSifenConfig;
        logger.info("Configuración del Sifen guardada correctamente");
    }

    public static RespuestaSifen consultaRUC(long dId, String ruc) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }
        return ConsultaRUC.prepareRequest(sifenConfig, dId, ruc);
    }

    public static RespuestaSifen recepcionDE(long dId, DocumentoElectronico DE) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }
        return RecepcionDE.prepareRequest(sifenConfig, dId, DE);
    }
}
