package com.roshka.sifen.model.de.types;

public enum TiDenTarj {

    VISA((short)1, "Visa"),
    MASTERCARD((short)2, "Mastercard"),
    AMERICAN_EXPRESS((short)3, "American Express"),
    MAESTRO((short)4, "Maestro"),
    PANAL((short)5, "Panal"),
    CABAL((short)6, "Cabal"),
    OTRO((short)99, null);

    private short val;
    private String descripcion;

    TiDenTarj(short val, String descripcion) {
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
