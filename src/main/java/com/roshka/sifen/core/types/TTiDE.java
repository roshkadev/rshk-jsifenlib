package com.roshka.sifen.core.types;

import java.util.Arrays;

/**
 * Esta clase engloba los datos/tipos tiTiDE y tdDesTiDE
 */
public enum TTiDE {
    FACTURA_ELECTRONICA((short) 1, "Factura electrónica"),
    FACTURA_ELECTRONICA_EXPORTACION((short) 2, "Factura electrónica de exportación"),
    FACTURA_ELECTRONICA_IMPORTACION((short) 3, "Factura electrónica de importación"),
    AUTOFACTURA_ELECTRONICA((short) 4, "Autofactura electrónica"),
    NOTA_DE_CREDITO_ELECTRONICA((short) 5, "Nota de crédito electrónica"),
    NOTA_DE_DEBITO_ELECTRONICA((short) 6, "Nota de débito electrónica"),
    NOTA_DE_REMISION_ELECTRONICA((short) 7, "Nota de remisión electrónica"),
    COMPROBANTE_RETENCION_ELECTRONICO((short) 8, "Comprobante de retención electrónico");

    private short val;
    private String descripcion;

    TTiDE(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TTiDE getByVal(short val) {
        return Arrays.stream(TTiDE.values()).filter(e -> e.val == val).findFirst().orElse(null);
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