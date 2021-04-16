package com.roshka.sifen.core.types;

import java.util.Arrays;

/**
 * Esta clase engloba los datos/tipos tiTipTra y tdDesTiTran
 */
public enum TTipTra {
    VENTA_MERCADERIA((short) 1, "Venta de mercadería"),
    PRESTACION_SERVICIOS((short) 2, "Prestación de servicios"),
    MIXTO((short) 3, "Mixto (Venta de mercadería y servicios)"),
    VENTA_ACTIVO_FIJO((short) 4, "Venta de activo fijo"),
    VENTA_DIVISAS((short) 5, "Venta de divisas"),
    COMPRA_DIVISAS((short) 6, "Compra de divisas"),
    PROMOCION_O_MUESTRAS((short) 7, "Promoción o entrega de muestras"),
    DONACION((short) 8, "Donación"),
    ANTICIPO((short) 9, "Anticipo"),
    COMPRA_PRODUCTOS((short) 10, "Compra de productos"),
    COMPRA_SERVICIOS((short) 11, "Compra de servicios"),
    VENTA_CREDITO_FISCAL((short) 12, "Venta de crédito fiscal"),
    MUESTRAS_MEDICAS((short) 13, "Muestras médicas (Art. 3 RG 24/2014)");

    private short val;
    private String descripcion;

    TTipTra(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TTipTra getByVal(short val) {
        return Arrays.stream(TTipTra.values()).filter(e -> e.val == val).findFirst().orElse(null);
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