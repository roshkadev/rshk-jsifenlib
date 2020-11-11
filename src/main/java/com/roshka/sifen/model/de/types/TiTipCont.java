package com.roshka.sifen.model.de.types;

public enum TiTipCont {
    PERSONA_FISICA((short) 1, "Persona Física"),
    PERSONA_JURIDICA((short) 2, "Persona Jurídica");

    private short val;
    private String descripcion;

    TiTipCont(short val, String descripcion) {
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
