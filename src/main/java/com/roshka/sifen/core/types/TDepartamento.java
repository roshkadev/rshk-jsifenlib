package com.roshka.sifen.core.types;

import java.util.Arrays;

/**
 * Este enumerado contiene los códigos de departamento y sus descripciones oficiales para la SET
 */
public enum TDepartamento {
    CAPITAL((short) 1, "CAPITAL"),
    CONCEPCION((short) 2, "CONCEPCION"),
    SAN_PEDRO((short) 3, "SAN PEDRO"),
    CORDILLERA((short) 4, "CORDILLERA"),
    GUAIRA((short) 5, "GUAIRA"),
    CAAGUAZU((short) 6, "CAAGUAZU"),
    CAAZAPA((short) 7, "CAAZAPA"),
    ITAPUA((short) 8, "ITAPUA"),
    MISIONES((short) 9, "MISIONES"),
    PARAGUARI((short) 10, "PARAGUARI"),
    ALTO_PARANA((short) 11, "ALTO PARANA"),
    CENTRAL((short) 12, "CENTRAL"),
    NEEMBUCU((short) 13, "NEEMBUCU"),
    AMAMBAY((short) 14, "AMAMBAY"),
    PTE_HAYES((short) 15, "PTE. HAYES"),
    BOQUERON((short) 16, "BOQUERON"),
    ALTO_PARAGUAY((short) 17, "ALTO PARAGUAY"),
    CANINDEYU((short) 18, "CANINDEYU"),
    CHACO((short) 19, "CHACO"),
    NUEVA_ASUNCION((short) 20, "NUEVA ASUNCION");

    private short val;
    private String descripcion;

    TDepartamento(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TDepartamento getByVal(short val) {
        return Arrays.stream(TDepartamento.values()).filter(e -> e.val == val).findFirst().orElse(null);
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