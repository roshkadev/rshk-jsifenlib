package com.roshka.sifen.model.de.types;

public enum TcRelMerc {

    TOLERANCIA_DE_QUIEBRA((short)1, "Tolerancia de quiebra"),
    TOLERANCIO_DE_MERMA((short)2, "Tolerancia de merma");

    private short val;
    private String descripcion;

    TcRelMerc(short val, String descripcion) {
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
