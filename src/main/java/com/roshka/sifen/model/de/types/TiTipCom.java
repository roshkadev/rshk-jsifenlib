package com.roshka.sifen.model.de.types;

public enum TiTipCom {

    GASOLINA((short)1, "Gasolina"),
    DIESEL((short)2, "Diésel"),
    ETANOL((short)2, "Etanol"),
    GNV((short)2, "GNV"),
    FLEX((short)2, "Flex"),
    OTRO((short)9, null);

    private short val;
    private String descripcion;

    TiTipCom(short val, String descripcion) {
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
