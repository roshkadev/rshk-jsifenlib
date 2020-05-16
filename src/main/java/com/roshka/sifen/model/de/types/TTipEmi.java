package com.roshka.sifen.model.de.types;

/**
 * Esta clase engloba los datos/tipos tiTipEmi y tdDesTipEmi
 */
public enum TTipEmi {

    NORMAL((short)1, "Normal"),
    CONTINGENCIA((short)2, "Contingencia");

    private short val;
    private String descripcion;

    TTipEmi(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public short getVal() {
        return val;
    }

    public void setVal(short val) {
        this.val = val;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
