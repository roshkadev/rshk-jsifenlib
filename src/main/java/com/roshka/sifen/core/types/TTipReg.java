package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TTipReg {
    REGIMEN_TURISMO((short) 1, "Régimen de Turismo"),
    IMPORTADOR((short) 2, "Importador"),
    EXPORTADOR((short) 3, "Exportador"),
    MAQUILA((short) 4, "Maquila"),
    LEY_60_90((short) 5, "Ley Nº 60/90"),
    REGIMEN_PEQUENO_PRODUCTOR((short) 6, "Régimen del Pequeño Productor"),
    REGIMEN_MEDIANO_PRODUCTOR((short) 7, "Régimen del Mediano Productor"),
    REGIMEN_CONTABLE((short) 8, "Régimen Contable");

    private short val;
    private String descripcion;

    TTipReg(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TTipReg getByVal(short val) {
        return Arrays.stream(TTipReg.values()).filter(e -> e.val == val).findFirst().orElse(null);
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