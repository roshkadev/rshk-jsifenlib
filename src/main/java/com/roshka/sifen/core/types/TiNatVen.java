package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiNatVen {
    NO_CONTRIBUYENTE((short) 1, "No contribuyente"),
    EXTRANJERO((short) 2, "Extranjero");

    private short val;
    private String descripcion;

    TiNatVen(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiNatVen getByVal(short val) {
        return Arrays.stream(TiNatVen.values()).filter(e -> e.val == val).findFirst().orElse(null);
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