package com.roshka.sifen.core.types;

import java.util.Arrays;

/**
 * Esta clase engloba los datos/tipos tiTipTra y tdDesTiTran
 */
public enum TTImp {
    IVA((short) 1, "IVA"),
    ISC((short) 2, "ISC"),
    RENTA((short) 3, "Renta"),
    NINGUNO((short) 4, "Ninguno"),
    IVA_RENTA((short) 5, "IVA - Renta");

    private short val;
    private String descripcion;

    TTImp(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TTImp getByVal(short val) {
        return Arrays.stream(TTImp.values()).filter(e -> e.val == val).findFirst().orElse(null);
    }

    public short getVal() {
        return val;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "{\"val\": " + val + ", \"descripcion\": \"" + descripcion + "\"}";
    }
}