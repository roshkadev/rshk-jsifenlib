package com.roshka.sifen.model.de.types;

public enum TdTipCons {

    CONSTANCIA_NO_CONTRIBUYENTE((short)1, "Constancia de no ser contribuyente"),
    CONSTANCIA_MICROPRODUCTORES((short)2, "Constancia de microproductores");

    private short val;
    private String descripcion;

    TdTipCons(short val, String descripcion) {
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
