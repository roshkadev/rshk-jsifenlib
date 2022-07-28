package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TcRelMerc {
    TOLERANCIA_DE_QUIEBRA((short) 1, "Tolerancia de quiebra"),
    TOLERANCIO_DE_MERMA((short) 2, "Tolerancia de merma");

    private final short val;
    private final String descripcion;

    TcRelMerc(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TcRelMerc getByVal(short val) {
        return Arrays.stream(TcRelMerc.values()).filter(e -> e.val == val).findFirst().orElse(null);
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