package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiModTrans {
    TERRESTRE((short) 1, "Terrestre"),
    FLUVIAL((short) 2, "Fluvial"),
    AEREO((short) 3, "Aéreo"),
    MULTIMODAL((short) 4, "Multimodal");

    private short val;
    private String descripcion;

    TiModTrans(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiModTrans getByVal(short val) {
        return Arrays.stream(TiModTrans.values()).filter(e -> e.val == val).findFirst().orElse(null);
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