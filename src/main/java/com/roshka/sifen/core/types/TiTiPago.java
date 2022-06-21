package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiTiPago {
    EFECTIVO((short) 1, "Efectivo"),
    CHEQUE((short) 2, "Cheque"),
    TARJETA_DE_CREDITO((short) 3, "Tarjeta de crédito"),
    TARJETA_DE_DEBITO((short) 4, "Tarjeta de débito"),
    TRANSFERENCIA((short) 5, "Transferencia"),
    GIRO((short) 6, "Giro"),
    BILLETERA_ELECTRONICA((short) 7, "Billetera electrónica"),
    TARJETA_EMPRESARIAL((short) 8, "Tarjeta empresarial"),
    VALE((short) 9, "Vale"),
    RETENCION((short) 10, "Retención"),
    PAGO_POR_ANTICIPO((short) 11, "Pago por anticipo"),
    VALOR_FISCAL((short) 12, "Valor fiscal"),
    VALOR_COMERCIAL((short) 13, "Valor comercial"),
    COMPENSACION((short) 14, "Compensación"),
    PERMUTA((short) 15, "Permuta"),
    PAGO_BANCARIO((short) 16, "Pago bancario"),
    PAGO_MOVIL((short) 17, "Pago Móvil"),
    DONACION((short) 18, "Donación"),
    PROMOCION((short) 19, "Promoción"),
    CONSUMO_INTERNO((short) 20, "Consumo Interno"),
    PAGO_ELECTRONICO((short) 21, "Pago Electrónico"),
    OTRO((short) 99, "Otro");

    private short val;
    private String descripcion;

    TiTiPago(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiTiPago getByVal(short val) {
        return Arrays.stream(TiTiPago.values()).filter(e -> e.val == val).findFirst().orElse(null);
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
