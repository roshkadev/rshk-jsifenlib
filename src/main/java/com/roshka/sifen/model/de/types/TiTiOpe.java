package com.roshka.sifen.model.de.types;

public enum TiTiOpe {

    B2B((short)1, "Business To Business"),
    B2C((short)2, "Business To Consumer"),
    B2G((short)3, "Business To Government"),
    B2F((short)4, "Business To Freelancer");


    private short val;
    private String descripcion;

    TiTiOpe(short val, String descripcion) {
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
