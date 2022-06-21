package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TiMotivTras {
    TRASLADO_POR_VENTAS((short) 1, "Traslado por ventas"),
    TRASLADO_POR_CONSIGNACION((short) 2, "Traslado por consignación"),
    EXPORTACION((short) 3, "Exportación"),
    TRASLADO_POR_COMPRA((short) 4, "Traslado por compra"),
    IMPORTACION((short) 5, "Importación"),
    TRASLADO_POR_DEVOLUCION((short) 6, "Traslado por devolución"),
    TRASLADO_ENTRE_LOCALES((short) 7, "Traslado entre locales de la empresa"),
    TRASLADO_BIENES_TRANSFORMACION((short) 8, "Traslado de bienes por transformación"),
    TRASLADO_BIENES_REPARACION((short) 9, "Traslado de bienes para reparación"),
    TRASLADO_POR_EMISOR_MOVIL((short) 10, "Traslado por emisor móvil"),
    EXHIBICION_O_DEMOSTRACION((short) 11, "Exhibición o Demostración"),
    PARTICIPACION_EN_FERIAS((short) 12, "Participación en ferias"),
    TRASLADO_DE_ENCOMIENDAS((short) 13, "Traslado de encomienda"),
    DECOMISO((short) 14, "Decomiso"),
    OTRO((short) 99, "Otro");

    private short val;
    private String descripcion;

    TiMotivTras(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TiMotivTras getByVal(short val) {
        return Arrays.stream(TiMotivTras.values()).filter(e -> e.val == val).findFirst().orElse(null);
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
