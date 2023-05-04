package com.roshka.sifen.internal.ctx;

import com.roshka.sifen.core.SifenConfig;

public class GenerationCtx {

    private String forzarCDC;
    private boolean habilitarNotaTecnica13;
    private SifenConfig sifenConfig;

    public static GenerationCtx getDefaultFromConfig(SifenConfig sifenConfig) {
        GenerationCtx generationCtx = new GenerationCtx();
        generationCtx.setForzarCDC(null);
        generationCtx.setHabilitarNotaTecnica13(sifenConfig.isHabilitarNotaTecnica13());
        generationCtx.setSifenConfig(sifenConfig);
        return generationCtx;

    }

    public String getForzarCDC() {
        return forzarCDC;
    }

    public void setForzarCDC(String forzarCDC) {
        this.forzarCDC = forzarCDC;
    }

    public boolean isHabilitarNotaTecnica13() {
        return habilitarNotaTecnica13;
    }

    public void setHabilitarNotaTecnica13(boolean habilitarNotaTecnica13) {
        this.habilitarNotaTecnica13 = habilitarNotaTecnica13;
    }

    public SifenConfig getSifenConfig() {
        return sifenConfig;
    }

    public void setSifenConfig(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;
    }
}
