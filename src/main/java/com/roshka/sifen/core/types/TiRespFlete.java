package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiRespFlete {
    EMISOR_FACTURA_ELECTRONICA((short) 1),
    RECEPTOR_FACTURA_ELECTRONICA((short) 2),
    TERCERO((short) 3),
    AGENTE_INTERMEDIARIO((short) 4),
    TRANSPORTE_PROPIO((short) 5);

    private short val;

    TiRespFlete(short val) {
        this.val = val;
    }

    public static TiRespFlete getByVal(short val) {
        return Arrays.stream(TiRespFlete.values()).filter(e -> e.val == val).findFirst().orElse(null);
    }

    public short getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "{\"val\": " + val + "}";
    }
}