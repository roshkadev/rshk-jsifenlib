package com.roshka.sifen.core.types;

import java.util.Arrays;

public enum TdMotEv {
    CAMBIO_LOCAL_ENTREGA((short) 1, "Cambio del local de la entrega"),
    CAMBIO_CHOFER((short) 2, "Cambio del chofer"),
    CAMBIO_TRANSPORTISTA((short) 3, "Cambio del transportista"),
    CAMBIO_VEHICULO((short) 4, "Cambio de vehículo");

    private short val;
    private String descripcion;

    TdMotEv(short val, String descripcion) {
        this.val = val;
        this.descripcion = descripcion;
    }

    public static TdMotEv getByVal(short val) {
        return Arrays.stream(TdMotEv.values()).filter(e -> e.val == val).findFirst().orElse(null);
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