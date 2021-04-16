package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TdTipCons {
    CONSTANCIA_NO_CONTRIBUYENTE((short) 1, "Constancia de no ser contribuyente"),
    CONSTANCIA_MICROPRODUCTORES((short) 2, "Constancia de microproductores");

    private short val;
    private String descripcion;

    TdTipCons(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TdTipCons getByVal(short val) {
        return Arrays.stream(TdTipCons.values()).filter(e -> e.val == val).findFirst().orElse(null);
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