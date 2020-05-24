package com.roshka.sifen.model.de.types;

public enum TiNatRec {

    CONTRIBUYENTE((short)1, "Contribuyente"),
    NO_CONTRIBUYENTE((short)2, "No Contribuyente");

    private short val;
    private String descripcion;

    TiNatRec(short val, String descripcion) {
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
