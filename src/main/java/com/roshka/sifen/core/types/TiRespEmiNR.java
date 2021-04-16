package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiRespEmiNR {
    EMISOR_FACTURA((short) 1, "Emisor de la factura"),
    POSEEDOR_FACTURA_Y_BIENES((short) 2, "Poseedor de la factura y bienes"),
    EMPRESA_TRANSPORTISTA((short) 3, "Empresa transportista"),
    DESPACHANTE_DE_ADUANAS((short) 4, "Despachante de Aduanas"),
    AGENTE_DE_TRANSPORTE_O_INTERMEDIARIO((short) 5, "Agente de transporte o intermediario");

    private short val;
    private String descripcion;

    TiRespEmiNR(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiRespEmiNR getByVal(short val) {
        return Arrays.stream(TiRespEmiNR.values()).filter(e -> e.val == val).findFirst().orElse(null);
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