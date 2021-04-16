package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TdTipIdenVeh {
    NRO_IDENTIFICACION((short) 1, "Número de identificación del vehículo"),
    NRO_MATRICULA((short) 2, "Número de matrícula del vehículo");

    private short val;
    private String descripcion;

    TdTipIdenVeh(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TdTipIdenVeh getByVal(short val) {
        return Arrays.stream(TdTipIdenVeh.values()).filter(e -> e.val == val).findFirst().orElse(null);
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