package com.roshka.sifen.model.de.types;

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

    public short getVal() {
        return val;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
