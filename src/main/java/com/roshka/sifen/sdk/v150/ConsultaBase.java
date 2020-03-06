package com.roshka.sifen.sdk.v150;

import com.roshka.sifen.context.SifenCtx;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.envi.REnviBase;

public class ConsultaBase<T> {

    private SifenCtx sifenCtx;
    private String url;

    public ConsultaBase(SifenCtx sifenCtx) {
        this.sifenCtx = sifenCtx;
    }

    public SifenCtx getSifenCtx() {
        return sifenCtx;
    }

    public void setSifenCtx(SifenCtx sifenCtx) {
        this.sifenCtx = sifenCtx;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected T call(String action, REnviBase rEnviBase, Class<T> clazz)
        throws SifenException
    {
        return null;
    }
}
