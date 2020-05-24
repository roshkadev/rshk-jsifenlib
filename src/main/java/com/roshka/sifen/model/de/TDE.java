package com.roshka.sifen.model.de;

import java.time.LocalDateTime;

/**
 * Clase que representa un Documento Electrónico
 * basado en el tipo tDE definido en DE_v150.xsd
 */
public class TDE {



    private short dDVId;                // dígito verificador
    private LocalDateTime dFecFirma;    // se debe serializar en el formato: yyyy-mm-ddThh:mi:ss
    private short dSisFact;             // sistema que factura (1. Sistema del Cliente, 2. Sistema
                                        // de facturación gratuita de la SET)
    private TgCOpeDE gOpeDE;            // campos de operación del documento electrónico
    private TgDTim gTimb;               // campos del timbrado del documento
    private TgDaGOC dDatGralOpe;        // datos generales de la operación
    private TgDtipDE asdf;





}
