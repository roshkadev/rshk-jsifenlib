package com.roshka.sifen.sdk.v150.response;

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
