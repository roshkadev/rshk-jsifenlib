package com.roshka.sifen.model.de.types;

public enum TiCondOpe {
    CONTADO((short) 1, "Contado"),
    CREDITO((short) 2, "Crédito");

    private short val;
    private String descripcion;

    TiCondOpe(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public short getVal() {
        return val;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
