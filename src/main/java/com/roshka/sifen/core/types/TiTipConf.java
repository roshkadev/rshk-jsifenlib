package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTipConf {
    CONFORMIDAD_TOTAL((short) 1, "Conformidad Total del DTE"),
    CONFORMIDAD_PARCIAL((short) 2, "Conformidad Parcial del DTE");

    private short val;
    private String descripcion;

    TiTipConf(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTipConf getByVal(short val) {
        return Arrays.stream(TiTipConf.values()).filter(e -> e.val == val).findFirst().orElse(null);
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