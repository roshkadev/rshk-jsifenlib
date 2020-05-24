package com.roshka.sifen.model.de.types;

public enum  TiTipOpVN {

    VENTA_A_REPRESENTANTE((short)1, "Venta a representante"),
    VENTA_AL_CONSUMIDOR_FINAL((short)2, "Venta al Consumidor final"),
    VENTA_AL_GOBIERNO((short)3, "Venta a gobierno"),
    VENTA_A_FLOTA_DE_VEHICULOS((short)4, "Venta a flota de vehículos");

    private short val;
    private String descripcion;

    TiTipOpVN(short val, String descripcion) {
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
