package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiDenTarj {
    VISA((short) 1, "Visa"),
    MASTERCARD((short) 2, "Mastercard"),
    AMERICAN_EXPRESS((short) 3, "American Express"),
    MAESTRO((short) 4, "Maestro"),
    PANAL((short) 5, "Panal"),
    CABAL((short) 6, "Cabal"),
    OTRO((short) 99, "Otro");

    private short val;
    private String descripcion;

    TiDenTarj(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiDenTarj getByVal(short val) {
        return Arrays.stream(TiDenTarj.values()).filter(e -> e.val == val).findFirst().orElse(null);
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
