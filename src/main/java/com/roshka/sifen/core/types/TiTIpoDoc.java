package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTIpoDoc {
    FACTURA((short) 1, "Factura"),
    NOTA_DE_CREDITO((short) 2, "Nota de crédito"),
    NOTA_DE_DEBITO((short) 3, "Nota de débito"),
    NOTA_DE_REMISION((short) 4, "Nota de remisión"),
    COMPROBANTE_DE_RETENCION((short) 5, "Comprobante de retención");

    private short val;
    private String descripcion;

    TiTIpoDoc(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTIpoDoc getByVal(short val) {
        return Arrays.stream(TiTIpoDoc.values()).filter(e -> e.val == val).findFirst().orElse(null);
    }

    public short getVal() {
        return val;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "{\"val\": " + val + ", \"descripcion\": \"" + descripcion + "\"}";
    }
}