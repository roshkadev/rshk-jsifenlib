package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiCondOpe {
    CONTADO((short) 1, "Contado"),
    CREDITO((short) 2, "Crédito");

    private short val;
    private String descripcion;

    TiCondOpe(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiCondOpe getByVal(short val) {
        return Arrays.stream(TiCondOpe.values()).filter(e -> e.val == val).findFirst().orElse(null);
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