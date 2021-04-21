package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TcCondNeg {
    CFR("Costo y flete"),
    CIF("Costo, seguro y flete"),
    CIP("Transporte y seguros pagados hasta"),
    CPT("Transporte pagado hasta"),
    DAP("Entregada en el lugar convenido"),
    DAT("Entregada en terminal"),
    DDP("Entregada derechos pagados"),
    EXW("En fabrica"),
    FAS("Franco al costado del buque"),
    FCA("Franco transportista"),
    FOB("Franco a bordo");

    private String descripcion;

    TcCondNeg(String descripcion) {
        this.descripcion = descripcion;
    }

    public static TcCondNeg getByDescription(String description) {
        return Arrays.stream(TcCondNeg.values()).filter(e -> e.name().equalsIgnoreCase(description)).findFirst().orElse(null);
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "{\"descripcion\":\"" + descripcion + "\"}";
    }
}