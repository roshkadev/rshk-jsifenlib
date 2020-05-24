package com.roshka.sifen.model.de.types;

public enum TiRespFlete {
    EMISOR_FACTURA_ELECTRONICA((short)1),
    RECEPTOR_FACTURA_ELECTRONICA((short)2),
    TERCERO((short)3),
    AGENTE_INTERMEDIARIO((short)4),
    TRANSPORTE_PROPIO((short)5);

    private short val;

    TiRespFlete(short val) {
        this.val = val;
    }

    public short getVal() {
        return val;
    }
}
