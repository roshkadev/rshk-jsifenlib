package com.roshka.sifen;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.sdk.v150.request.ConsultaRUC;

public class Sifen {
    private static SifenConfig sifenConfig = null;

    public static void setSifenConfig(SifenConfig newSifenConfig) {
        sifenConfig = newSifenConfig;
    }

    public static RespuestaSifen consultaRUC(long dId, String ruc) throws SifenException {
        if (sifenConfig == null) {
            // error
            return null;
        }
        return ConsultaRUC.prepareRequest(sifenConfig, dId, ruc);
    }
}
