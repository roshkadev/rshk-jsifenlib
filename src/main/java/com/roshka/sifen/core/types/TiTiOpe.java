package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTiOpe {
    B2B((short) 1, "Business To Business"),
    B2C((short) 2, "Business To Consumer"),
    B2G((short) 3, "Business To Government"),
    B2F((short) 4, "Business To Freelancer");

    private short val;
    private String descripcion;

    TiTiOpe(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTiOpe getByVal(short val) {
        return Arrays.stream(TiTiOpe.values()).filter(e -> e.val == val).findFirst().orElse(null);
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