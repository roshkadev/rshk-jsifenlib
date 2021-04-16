package com.roshka.sifen.core.types;

import java.util.Arrays;

/**
 * Esta clase engloba los datos/tipos tiCondAnt y tdDesCondAnt
 */
public enum TiCondAnt {
    ANTICIPO_GLOBAL((short) 1, "Anticipo Global"),
    ANTICIPO_POR_ITEM((short) 2, "Anticipo por Ítem");

    private short val;
    private String descripcion;

    TiCondAnt(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiCondAnt getByVal(short val) {
        return Arrays.stream(TiCondAnt.values()).filter(e -> e.val == val).findFirst().orElse(null);
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