package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TdCondTiCam {
    GLOBAL((short) 1, "Global"),
    POR_ITEM((short) 2, "Por ítem");

    private short val;
    private String descripcion;

    TdCondTiCam(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TdCondTiCam getByVal(short val) {
        return Arrays.stream(TdCondTiCam.values()).filter(e -> e.val == val).findFirst().orElse(null);
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