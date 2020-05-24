package com.roshka.sifen.model.de.types;

public enum TiTTrans {
    PROPIO((short)1, "Propio"),
    TERCERO((short)2, "Tercero");

    private short val;
    private String descripcion;

    TiTTrans(short val, String descripcion) {
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
