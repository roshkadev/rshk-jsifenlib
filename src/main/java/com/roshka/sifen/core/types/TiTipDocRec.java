package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTipDocRec {
    CEDULA_PARAGUAYA((short) 1, "Cédula paraguaya"),
    PASAPORTE((short) 2, "Pasaporte"),
    CEDULA_EXTRANJERA((short) 3, "Cédula extranjera"),
    CARNET_DE_RESIDENCIA((short) 4, "Carnet de residencia"),
    INNOMINADO((short) 5, "Innominado"),
    TARJETA_DIPLOMATICA((short) 6, "Tarjeta Diplomática de exoneración fiscal"),
    OTRO((short) 9, "Otro");

    private short val;
    private String descripcion;

    TiTipDocRec(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTipDocRec getByVal(short val) {
        return Arrays.stream(TiTipDocRec.values()).filter(e -> e.val == val).findFirst().orElse(null);
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
