package com.roshka.sifen;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.response.*;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.request.*;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.EventosDE;

import java.util.List;
import java.util.logging.Logger;

/**
 * Clase principal de la librería desde la cuál se realizan todas las operaciones de Sifen.
 */
public class Sifen {
    private final static Logger logger = Logger.getLogger(Sifen.class.toString());
    private static SifenConfig sifenConfig = null;
    private static long dId = 1;

    /**
     * Establece la configuración necesaria para el funcionamiento correcto de todas las funcionalidades. Solo
     * debe realizarse una vez al principio, antes de ejecutar alguna acción. Si la configuración necesita ser
     * actualizada, simplemente invocar de vuelta.
     *
     * @param newSifenConfig El objeto de configuración que será utilizado.
     */
    public static void setSifenConfig(SifenConfig newSifenConfig) {
        sifenConfig = newSifenConfig;
        logger.info("Configuración de Sifen guardada correctamente");
    }

    /**
     * @return El objeto de configuración previamente establecido.
     */
    public static SifenConfig getSifenConfig() {
        return sifenConfig;
    }

    /**
     * Realiza una consulta a Sifen y devuelve como resultado los datos y el estado del RUC de un contribuyente.
     * @param ruc RUC de un contribuyente a ser consultado en Sifen, sin el DV.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la consulta no pudo ser realizada.
     */
    public static RespuestaConsultaRUC consultaRUC(String ruc) throws SifenException {
        return consultaRUC(ruc, sifenConfig);
    }

    /**
     * Realiza una consulta a Sifen y devuelve como resultado los datos y el estado del RUC de un contribuyente.
     * @param ruc RUC de un contribuyente a ser consultado en Sifen, sin el DV.
     * @param sifenConfig Configuración de Sifen a ser utilizada en esta petición.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la consulta no pudo ser realizada.
     */
    public static RespuestaConsultaRUC consultaRUC(String ruc, SifenConfig sifenConfig) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración de Sifen.");
        }

        logger.info("Preparando petición 'Consulta de RUC'");
        ReqConsRuc reqConsRuc = new ReqConsRuc(dId++, sifenConfig);
        reqConsRuc.setdRUCCons(ruc);

        return (RespuestaConsultaRUC) reqConsRuc.makeRequest(sifenConfig.getUrlConsultaRUC());
    }

    /**
     * Realiza una consulta a Sifen y devuelve como resultado el Documento Electrónico encontrado y todos sus eventos asociados.
     * @param cdc Código de Control, que es el identificador único de un Documento Electrónico.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la consulta no pudo ser realizada.
     */
    public static RespuestaConsultaDE consultaDE(String cdc) throws SifenException {
        return consultaDE(cdc, sifenConfig);
    }

    /**
     * Realiza una consulta a Sifen y devuelve como resultado el Documento Electrónico encontrado y todos sus eventos asociados.
     * @param cdc Código de Control, que es el identificador único de un Documento Electrónico.
     * @param sifenConfig Configuración de Sifen a ser utilizada en esta petición.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la consulta no pudo ser realizada.
     */
    public static RespuestaConsultaDE consultaDE(String cdc, SifenConfig sifenConfig) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración de Sifen.");
        }

        logger.info("Preparando petición 'Consulta de DE'");
        ReqConsDe reqConsDe = new ReqConsDe(dId++, sifenConfig);
        reqConsDe.setdCDC(cdc);

        return (RespuestaConsultaDE) reqConsDe.makeRequest(sifenConfig.getUrlConsulta());
    }

    /**
     * Realiza una consulta a Sifen y devuelve como resultado el estado del lote consultado.
     * @param nroLote Número de Lote recibido como respuesta en el envío del mismo.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la consulta no pudo ser realizada.
     */
    public static RespuestaConsultaLoteDE consultaLoteDE(String nroLote) throws SifenException {
        return consultaLoteDE(nroLote, sifenConfig);
    }

    /**
     * Realiza una consulta a Sifen y devuelve como resultado el estado del lote consultado.
     * @param nroLote Número de Lote recibido como respuesta en el envío del mismo.
     * @param sifenConfig Configuración de Sifen a ser utilizada en esta petición.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la consulta no pudo ser realizada.
     */
    public static RespuestaConsultaLoteDE consultaLoteDE(String nroLote, SifenConfig sifenConfig) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración de Sifen.");
        }

        logger.info("Preparando petición 'Consulta de Resultado de Lote de DE'");
        ReqConsLoteDe reqConsLoteDe = new ReqConsLoteDe(dId++, sifenConfig);
        reqConsLoteDe.setdProtConsLote(nroLote);

        return (RespuestaConsultaLoteDE) reqConsLoteDe.makeRequest(sifenConfig.getUrlConsultaLote());
    }

    /**
     * Realiza un envío del Documento Electrónico a Sifen para su correspondiente aprobación.
     * @param de Objeto que hace referencia a un Documento Electrónico, con todos sus datos.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la firma digital del DE falla o, si la consulta no pudo ser realizada.
     */
    public static RespuestaRecepcionDE recepcionDE(DocumentoElectronico de) throws SifenException {
        return recepcionDE(de, sifenConfig);
    }

    /**
     * Realiza un envío del Documento Electrónico a Sifen para su correspondiente aprobación.
     * @param de Objeto que hace referencia a un Documento Electrónico, con todos sus datos.
     * @param sifenConfig Configuración de Sifen a ser utilizada en esta petición.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la firma digital del DE falla o, si la consulta no pudo ser realizada.
     */
    public static RespuestaRecepcionDE recepcionDE(DocumentoElectronico de, SifenConfig sifenConfig) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración de Sifen.");
        }

        logger.info("Preparando petición 'Recepción de DE'");
        ReqRecDe reqRecDe = new ReqRecDe(dId++, sifenConfig);
        reqRecDe.setDE(de);

        return (RespuestaRecepcionDE) reqRecDe.makeRequest(sifenConfig.getUrlRecibe());
    }

    /**
     * Realiza un envío de un lote de Documentos Electrónicos a Sifen para su correspondiente aprobación. La respuesta
     * de la aprobación o rechazo de cada DE es asíncrono, es decir, no se encuentra en la respuesta de esta petición.
     * @param deList Listado de los objetos que hacen referencia a los Documentos Electrónicos, con todos los datos.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario de algún DE
     *                        no pudo ser encontrado o, si la forma digital de algún DE falla o, si la consulta no pudo ser realizada.
     */
    public static RespuestaRecepcionLoteDE recepcionLoteDE(List<DocumentoElectronico> deList) throws SifenException {
        return recepcionLoteDE(deList, sifenConfig);
    }

    /**
     * Realiza un envío de un lote de Documentos Electrónicos a Sifen para su correspondiente aprobación. La respuesta
     * de la aprobación o rechazo de cada DE es asíncrono, es decir, no se encuentra en la respuesta de esta petición.
     * @param deList Listado de los objetos que hacen referencia a los Documentos Electrónicos, con todos los datos.
     * @param sifenConfig Configuración de Sifen a ser utilizada en esta petición.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario de algún DE
     *                        no pudo ser encontrado o, si la forma digital de algún DE falla o, si la consulta no pudo ser realizada.
     */
    public static RespuestaRecepcionLoteDE recepcionLoteDE(List<DocumentoElectronico> deList, SifenConfig sifenConfig) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración de Sifen.");
        }

        logger.info("Preparando petición 'Recepción de Lote de DE'");
        ReqRecLoteDe reqRecLoteDe = new ReqRecLoteDe(dId++, sifenConfig);
        reqRecLoteDe.setDEList(deList);

        return (RespuestaRecepcionLoteDE) reqRecLoteDe.makeRequest(sifenConfig.getUrlRecibeLote());
    }

    /**
     * Realiza un envío a Sifen de los eventos agregados en el objeto recibido como argumento.
     * @param eventosDE Objeto que contiene el listado de eventos a ser enviados a Sifen.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la firma digital de algún evento falla o, si la consulta no pudo ser
     *                        realizada.
     */
    public static RespuestaRecepcionEvento recepcionEvento(EventosDE eventosDE) throws SifenException {
        return recepcionEvento(eventosDE, sifenConfig);
    }

    /**
     * Realiza un envío a Sifen de los eventos agregados en el objeto recibido como argumento.
     * @param eventosDE Objeto que contiene el listado de eventos a ser enviados a Sifen.
     * @param sifenConfig Configuración de Sifen a ser utilizada en esta petición.
     * @return La respuesta a la consulta proveída por Sifen, en forma de clase.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        consulta no pudo ser encontrado o, si la firma digital de algún evento falla o, si la consulta no pudo ser
     *                        realizada.
     */
    public static RespuestaRecepcionEvento recepcionEvento(EventosDE eventosDE, SifenConfig sifenConfig) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración de Sifen.");
        }

        logger.info("Preparando petición 'Recepción de Eventos'");
        ReqRecEventoDe reqRecEventoDe = new ReqRecEventoDe(dId++, sifenConfig);
        reqRecEventoDe.setEventoDE(eventosDE);

        return (RespuestaRecepcionEvento) reqRecEventoDe.makeRequest(sifenConfig.getUrlEvento());
    }
}