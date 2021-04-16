package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTTrans {
    PROPIO((short) 1, "Propio"),
    TERCERO((short) 2, "Tercero");

    private short val;
    private String descripcion;

    TiTTrans(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTTrans getByVal(short val) {
        return Arrays.stream(TiTTrans.values()).filter(e -> e.val == val).findFirst().orElse(null);
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