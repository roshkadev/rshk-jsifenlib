package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiCondCred {
    PLAZO((short) 1, "Plazo"),
    CUOTA((short) 2, "Cuota");

    private short val;
    private String descripcion;

    TiCondCred(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiCondCred getByVal(short val) {
        return Arrays.stream(TiCondCred.values()).filter(e -> e.val == val).findFirst().orElse(null);
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