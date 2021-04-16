package com.roshka.sifen.core.types;

import java.util.Arrays;

/**
 * Esta clase engloba los datos/tipos tiTipEmi y tdDesTipEmi
 */
public enum TTipEmi {
    NORMAL((short) 1, "Normal"),
    CONTINGENCIA((short) 2, "Contingencia");

    private short val;
    private String descripcion;

    TTipEmi(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TTipEmi getByVal(short val) {
        return Arrays.stream(TTipEmi.values()).filter(e -> e.val == val).findFirst().orElse(null);
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