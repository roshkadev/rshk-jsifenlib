package com.roshka.sifen.model.de.types;

public enum TiCondCred {
    PLAZO((short) 1, "Plazo"),
    CUOTA((short) 2, "Cuota");

    private short val;
    private String descripcion;

    TiCondCred(short val, String descripcion) {
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
