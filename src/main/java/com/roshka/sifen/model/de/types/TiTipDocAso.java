package com.roshka.sifen.model.de.types;

public enum TiTipDocAso {
    ELECTRONICO((short) 1, "Electrónico"),
    IMPRESO((short) 2, "Impreso"),
    CONSTANCIA_ELECTRONICA((short) 3, "Constancia Electrónica");

    private short val;
    private String descripcion;

    TiTipDocAso(short val, String descripcion) {
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
