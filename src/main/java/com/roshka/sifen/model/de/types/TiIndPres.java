package com.roshka.sifen.model.de.types;

public enum TiIndPres {

    OPERACION_PRESENCIAL((short)1, "Operación presencial"),
    OPERACION_ELECTRONICA((short)2, "Operación electrónica"),
    OPERACION_TELEMARKETING((short)3, "Operación telemarketing"),
    VENTA_A_DOMICILIO((short)4, "Venta a domicilio"),
    OPERACION_BANCARIA((short)5, "Operación bancaria"),
    OPERACION_CICLICA((short)6, "Operación cíclica"),
    OTRO((short)9, null);

    private short val;
    private String descripcion;

    TiIndPres(short val, String descripcion) {
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
