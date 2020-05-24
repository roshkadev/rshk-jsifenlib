package com.roshka.sifen.model.de.types;

public enum TiModTrans {

    TERRESTRE((short)1, "Terrestre"),
    FLUVIAL((short)2, "Fluvial"),
    AEREO((short)3, "Aéreo"),
    MULTIMODAL((short)4, "Multimodal");

    private short val;
    private String descripcion;

    TiModTrans(short val, String descripcion) {
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
