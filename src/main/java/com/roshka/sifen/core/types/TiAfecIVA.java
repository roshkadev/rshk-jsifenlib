package com.roshka.sifen.core.types;

import java.util.Arrays;

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

    public static TiAfecIVA getByVal(short val) {
        return Arrays.stream(TiAfecIVA.values()).filter(e -> e.val == val).findFirst().orElse(null);
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