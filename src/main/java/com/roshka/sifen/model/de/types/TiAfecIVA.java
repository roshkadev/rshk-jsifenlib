package com.roshka.sifen.model.de.types;

public enum TiAfecIVA {
    GRAVADO((short) 1, "Gravado IVA"),
    EXONERADO((short) 2, "Exonerado (Art. 83- Ley 125/91)"),
    EXENTO((short) 3, "Exento"),
    GRAVADO_PARCIAL((short) 4, "Gravado parcial (Grav- Exento)");

    private short val;
    private String descripcion;

    TiAfecIVA(short val, String descripcion) {
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
