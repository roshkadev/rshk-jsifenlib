package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiForProPa {
    POS((short) 1, "POS"),
    PAGO_ELECTRONICO((short) 2, "Pago Electrónico"),
    OTRO((short) 9, "Otro");

    private short val;
    private String descripcion;

    TiForProPa(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiForProPa getByVal(short val) {
        return Arrays.stream(TiForProPa.values()).filter(e -> e.val == val).findFirst().orElse(null);
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