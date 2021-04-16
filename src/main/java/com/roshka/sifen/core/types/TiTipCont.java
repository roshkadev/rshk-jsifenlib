package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTipCont {
    PERSONA_FISICA((short) 1, "Persona Física"),
    PERSONA_JURIDICA((short) 2, "Persona Jurídica");

    private short val;
    private String descripcion;

    TiTipCont(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTipCont getByVal(short val) {
        return Arrays.stream(TiTipCont.values()).filter(e -> e.val == val).findFirst().orElse(null);
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