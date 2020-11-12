package com.roshka.sifen.model.de.types;

public enum TiNatVen {
    NO_CONTRIBUYENTE((short) 1, "No Contribuyente"),
    EXRANJERO((short) 2, "Extranjero");

    private short val;
    private String descripcion;

    TiNatVen(short val, String descripcion) {
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
