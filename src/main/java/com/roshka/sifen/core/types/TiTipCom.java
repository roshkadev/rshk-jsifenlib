package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTipCom {
    GASOLINA((short) 1, "Gasolina"),
    DIESEL((short) 2, "Diésel"),
    ETANOL((short) 2, "Etanol"),
    GNV((short) 2, "GNV"),
    FLEX((short) 2, "Flex"),
    OTRO((short) 9, "Otro");

    private short val;
    private String descripcion;

    TiTipCom(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTipCom getByVal(short val) {
        return Arrays.stream(TiTipCom.values()).filter(e -> e.val == val).findFirst().orElse(null);
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
