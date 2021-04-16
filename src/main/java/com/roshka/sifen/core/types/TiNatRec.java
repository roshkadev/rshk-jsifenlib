package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiNatRec {
    CONTRIBUYENTE((short) 1, "Contribuyente"),
    NO_CONTRIBUYENTE((short) 2, "No Contribuyente");

    private short val;
    private String descripcion;

    TiNatRec(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiNatRec getByVal(short val) {
        return Arrays.stream(TiNatRec.values()).filter(e -> e.val == val).findFirst().orElse(null);
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