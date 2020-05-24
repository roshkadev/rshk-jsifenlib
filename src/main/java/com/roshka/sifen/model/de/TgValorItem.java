package com.roshka.sifen.model.de;

import java.math.BigDecimal;

public class TgValorItem {

    private BigDecimal dPUniProSer;
    private BigDecimal dTiCamIt;
    private BigDecimal dTotBruOpeItem;
    private TgValorRestaItem gValorRestaItem;

    public BigDecimal getdPUniProSer() {
        return dPUniProSer;
    }

    public void setdPUniProSer(BigDecimal dPUniProSer) {
        this.dPUniProSer = dPUniProSer;
    }

    public BigDecimal getdTiCamIt() {
        return dTiCamIt;
    }

    public void setdTiCamIt(BigDecimal dTiCamIt) {
        this.dTiCamIt = dTiCamIt;
    }

    public BigDecimal getdTotBruOpeItem() {
        return dTotBruOpeItem;
    }

    public void setdTotBruOpeItem(BigDecimal dTotBruOpeItem) {
        this.dTotBruOpeItem = dTotBruOpeItem;
    }

    public TgValorRestaItem getgValorRestaItem() {
        return gValorRestaItem;
    }

    public void setgValorRestaItem(TgValorRestaItem gValorRestaItem) {
        this.gValorRestaItem = gValorRestaItem;
    }

}
