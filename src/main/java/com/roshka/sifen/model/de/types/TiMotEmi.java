package com.roshka.sifen.model.de.types;

public enum TiMotEmi {

    DEVOLUCION_Y_AJUSTES_DE_PRECIOS("1", "Devolución y Ajuste de precios"),
    DEVOLUCION("2", "Devolución"),
    DESCUENTO("3", "Descuento"),
    BONIFICACION("4", "Bonificación"),
    CREDITO_INCOBRABLE("5", "Crédito incobrable"),
    RECUPERO_DE_COSTO("6", "Recupero de costo"),
    RECUPERO_DE_GASTO("7", "Recupero de gasto"),
    AJUSTE_DE_PRECIO("8", "Ajuste de precio");

    private String val;
    private String descripcion;

    TiMotEmi(String val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public String getVal() {
        return val;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
