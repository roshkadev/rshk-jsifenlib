package com.roshka.sifen.model.de.types;

/**
 * Esta clase engloba los datos/tipos tiTiDE y tdDesTiDE
 */
public enum TDepartamento {

    CAPITAL((short)1, "CAPITAL"),
    CONCEPCION((short)2, "CONCEPCION"),
    SAN_PEDRO((short)3, "SAN PEDRO"),
    CORDILLERA((short)4, "CORDILLERA"),
    GUAIRA((short)5, "GUAIRA"),
    CAAGUAZU((short)6, "CAAGUAZU"),
    CAAZAPA((short)7, "CAAZAPA"),
    ITAPUA((short)8, "ITAPUA"),
    MISIONES((short)9, "MISIONES"),
    PARAGUARI((short)10, "PARAGUARI"),
    ALTO_PARANA((short)11, "ALTO PARANA"),
    CENTRAL((short)12, "CENTRAL"),
    NEEMBUCU((short)13, "NEEMBUCU"),
    AMAMBAY((short)14, "AMAMBAY"),
    PTE_HAYES((short)15, "PTE. HAYES"),
    BOQUERON((short)16, "BOQUERON"),
    ALTO_PARAGUAY((short)17, "ALTO PARAGUAY"),
    CANINDEYU((short)18, "CANINDEYU"),
    CHACO((short)19, "CHACO"),
    NUEVA_ASUNCION((short)20, "NUEVA ASUNCION");

    private short val;
    private String descripcion;

    TDepartamento(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public short getVal() {
        return val;
    }

    public String getDescripcion() {
        return descripcion;
    }


}
