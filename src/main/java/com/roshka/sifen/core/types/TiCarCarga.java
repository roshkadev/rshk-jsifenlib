package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiCarCarga {
    MERCADERIA_CON_CADENA_DE_FRIO((short) 1, "Mercaderías con cadena de frío"),
    CARGA_PELIGROSA((short) 2, "Carga peligrosa"),
    OTRO((short) 3, "Otro");

    private short val;
    private String descripcion;

    TiCarCarga(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiCarCarga getByVal(short val) {
        return Arrays.stream(TiCarCarga.values()).filter(e -> e.val == val).findFirst().orElse(null);
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
