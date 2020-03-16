package com.roshka.sifen.http;

import com.roshka.sifen.model.envi.REnviBase;
import com.roshka.sifen.model.res.RResBase;

public class RespuestaSifen<T extends REnviBase, U extends RResBase> {

    private RespuestaSOAP respuestaSOAP;

    private T peticion;
    private U respuesta;

    public boolean llamadaCorrecta() {
        return respuestaSOAP != null && respuestaSOAP.llamadaCorrecta();
    }

    public RespuestaSOAP getRespuestaSOAP() {
        return respuestaSOAP;
    }

    public void setRespuestaSOAP(RespuestaSOAP respuestaSOAP) {
        this.respuestaSOAP = respuestaSOAP;
    }

    public T getPeticion() {
        return peticion;
    }

    public void setPeticion(T peticion) {
        this.peticion = peticion;
    }

    public U getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(U respuesta) {
        this.respuesta = respuesta;
    }
}
