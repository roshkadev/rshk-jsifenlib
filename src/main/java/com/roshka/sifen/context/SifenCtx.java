package com.roshka.sifen.context;

import com.roshka.sifen.config.SifenConfig;

public class SifenCtx {

    private long currentdId;
    private SifenConfig sifenConfig;

    public SifenCtx(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;
    }

    public SifenConfig getSifenConfig() {
        return sifenConfig;
    }

    public void setSifenConfig(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;
    }

    // synchronized, just in case
    public synchronized long nextdId() {
        return ++currentdId;
    }
}
