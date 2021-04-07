package com.roshka.sifen.core;

import com.roshka.sifen.internal.response.BaseResponse;

public class RespuestaSifen {
    private int codigoEstado;
    private BaseResponse respuesta;

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public BaseResponse getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(BaseResponse respuesta) {
        this.respuesta = respuesta;
    }
}