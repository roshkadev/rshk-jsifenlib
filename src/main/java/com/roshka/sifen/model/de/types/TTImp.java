package com.roshka.sifen.model.de.types;

/**
 * Esta clase engloba los datos/tipos tiTipTra y tdDesTiTran
 */
public enum TTImp {
    IVA((short) 1, "IVA"),
    ISC((short) 2, "ISC"),
    RENTA((short) 3, "Renta"),
    NINGUNO((short) 4, "Ninguno"),
    IVA_RENTA((short) 5, "IVA - Renta");

    private short val;
    private String descripcion;

    TTImp(short val, String descripcion) {
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
