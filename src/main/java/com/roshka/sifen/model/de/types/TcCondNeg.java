package com.roshka.sifen.model.de.types;

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

    public String getDescripcion() {
        return descripcion;
    }

}
