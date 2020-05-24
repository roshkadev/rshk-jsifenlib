package com.roshka.sifen.model.de.extra;

public enum TiCarCarga {

    MERCADERIA_CON_CADENA_DE_FRIO((short)1, "Mercaderías con cadena de frío"),
    CARGA_PELIGROSA((short)2, "Carga peligrosa"),
    OTRO((short)3, null);

    private short val;
    private String descripcion;

    TiCarCarga(short val, String descripcion) {
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
