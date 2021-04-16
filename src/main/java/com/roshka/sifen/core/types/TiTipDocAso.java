package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTipDocAso {
    ELECTRONICO((short) 1, "Electrónico"),
    IMPRESO((short) 2, "Impreso"),
    CONSTANCIA_ELECTRONICA((short) 3, "Constancia Electrónica");

    private short val;
    private String descripcion;

    TiTipDocAso(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTipDocAso getByVal(short val) {
        return Arrays.stream(TiTipDocAso.values()).filter(e -> e.val == val).findFirst().orElse(null);
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