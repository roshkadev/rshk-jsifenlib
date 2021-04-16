package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiMotEmi {
    DEVOLUCION_Y_AJUSTES_DE_PRECIOS((short) 1, "Devolución y Ajuste de precios"),
    DEVOLUCION((short) 2, "Devolución"),
    DESCUENTO((short) 3, "Descuento"),
    BONIFICACION((short) 4, "Bonificación"),
    CREDITO_INCOBRABLE((short) 5, "Crédito incobrable"),
    RECUPERO_DE_COSTO((short) 6, "Recupero de costo"),
    RECUPERO_DE_GASTO((short) 7, "Recupero de gasto"),
    AJUSTE_DE_PRECIO((short) 8, "Ajuste de precio");

    private short val;
    private String descripcion;

    TiMotEmi(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiMotEmi getByVal(short val) {
        return Arrays.stream(TiMotEmi.values()).filter(e -> e.val == val).findFirst().orElse(null);
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