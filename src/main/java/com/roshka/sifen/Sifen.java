package com.roshka.sifen;

import com.roshka.sifen.core.RespuestaSifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.request.ReqConsDe;
import com.roshka.sifen.internal.request.ReqConsRuc;
import com.roshka.sifen.internal.request.ReqRecDe;
import com.roshka.sifen.internal.request.ReqRecEventoDe;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.EventosDE;

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

        logger.info("Preparando petición 'Consulta de RUC'");
        ReqConsRuc reqConsRuc = new ReqConsRuc(dId, sifenConfig);
        reqConsRuc.setdRUCCons(ruc);

        return reqConsRuc.makeRequest(sifenConfig.getUrlConsultaRUC());
    }

    public static RespuestaSifen recepcionDE(long dId, DocumentoElectronico de) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }

        logger.info("Preparando petición 'Recepción de DE'");
        ReqRecDe reqRecDe = new ReqRecDe(dId, sifenConfig);
        reqRecDe.setDE(de);

        return reqRecDe.makeRequest(sifenConfig.getUrlRecibe());
    }

    public static String generarXmlDE(long dId, DocumentoElectronico de) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }

        logger.info("Preparando XML del DE");
        return de.getXmlString(dId, sifenConfig);
    }

    public static boolean generarXmlDE(long dId, DocumentoElectronico de, String rutaDestino) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }

        logger.info("Preparando XML del DE");
        return de.saveXml(dId, sifenConfig, rutaDestino);
    }

    public static RespuestaSifen consultaDE(long dId, String cdc) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }

        logger.info("Preparando petición 'Consulta de DE'");
        ReqConsDe reqConsDe = new ReqConsDe(dId, sifenConfig);
        reqConsDe.setdCDC(cdc);

        return reqConsDe.makeRequest(sifenConfig.getUrlConsulta());
    }

    public static RespuestaSifen recepcionEvento(long dId, EventosDE eventosDE) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }

        logger.info("Preparando petición 'Recepción de Eventos'");
        ReqRecEventoDe reqRecEventoDe = new ReqRecEventoDe(dId, sifenConfig);
        reqRecEventoDe.setEventoDE(eventosDE);

        return reqRecEventoDe.makeRequest(sifenConfig.getUrlEvento());
    }
}