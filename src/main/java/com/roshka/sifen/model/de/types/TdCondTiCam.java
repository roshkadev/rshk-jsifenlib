package com.roshka.sifen.model.de.types;

public enum TdCondTiCam {
    GLOBAL((short) 1, "Global"),
    POR_ITEM((short) 2, "Por ítem");

    private short val;
    private String descripcion;

    TdCondTiCam(short val, String descripcion) {
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
