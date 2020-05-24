package com.roshka.sifen.model.de.types;

public enum TiForProPa {

    POS((short)1, "POS"),
    PAGO_ELECTRONICO((short)2, "Pago Electrónico"),
    OTRO((short)9, "Otro");

    private short val;
    private String descripcion;

    TiForProPa(short val, String descripcion) {
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
