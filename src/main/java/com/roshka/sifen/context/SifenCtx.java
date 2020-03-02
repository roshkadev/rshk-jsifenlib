package com.roshka.sifen.context;

import com.roshka.sifen.cert.SifenCert;
import com.roshka.sifen.config.SifenConfig;

public class SifenCtx {

    private SifenConfig sifenConfig;
    private SifenCert sifenCert;

    public SifenConfig getSifenConfig() {
        return sifenConfig;
    }

    public void setSifenConfig(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;
    }

    public SifenCert getSifenCert() {
        return sifenCert;
    }

    public void setSifenCert(SifenCert sifenCert) {
        this.sifenCert = sifenCert;
    }
}
