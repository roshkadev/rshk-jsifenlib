package com.roshka.sifen.test.de;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionLoteDE;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.*;
import com.roshka.sifen.core.types.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DEMonedaExtranjeraTest extends DETestBase {

    @Test
    @Ignore
    public void testRecepcionLoteDE() throws SifenException {
        LocalDateTime currentDate = LocalDateTime.now();

        // Grupo A
        DocumentoElectronico de = new DocumentoElectronico();
        de.setdFecFirma(currentDate);
        de.setdSisFact((short) 1);

        // Grupo B
        TgOpeDE gOpeDE = new TgOpeDE();
        gOpeDE.setiTipEmi(TTipEmi.NORMAL);
        de.setgOpeDE(gOpeDE);

        // Grupo C
        TgTimb gTimb = new TgTimb();
        gTimb.setiTiDE(TTiDE.FACTURA_ELECTRONICA);
        gTimb.setdNumTim(12557605);
        gTimb.setdEst("001");
        gTimb.setdPunExp("002");
        gTimb.setdNumDoc("0000008");
        gTimb.setdFeIniT(LocalDate.parse("2019-07-24"));
        de.setgTimb(gTimb);

        // Grupo D
        TdDatGralOpe dDatGralOpe = new TdDatGralOpe();
        dDatGralOpe.setdFeEmiDE(currentDate);

        TgOpeCom gOpeCom = new TgOpeCom();
        gOpeCom.setiTipTra(TTipTra.PRESTACION_SERVICIOS);
        gOpeCom.setiTImp(TTImp.IVA);
        gOpeCom.setcMoneOpe(CMondT.USD);

        // Tipo de Cambio

        gOpeCom.setdCondTiCam(TdCondTiCam.GLOBAL);
        gOpeCom.setdTiCam(BigDecimal.valueOf(7135.0));

        dDatGralOpe.setgOpeCom(gOpeCom);



        TgEmis gEmis = new TgEmis();
        gEmis.setdRucEm("80089752");
        gEmis.setdDVEmi("8");
        gEmis.setiTipCont(TiTipCont.PERSONA_JURIDICA);
        gEmis.setdNomEmi("de generado en ambiente de prueba - sin valor comercial ni fiscal");
        gEmis.setdDirEmi("Tte. Cusmanich");
        gEmis.setdNumCas("867");
        gEmis.setcDepEmi(TDepartamento.CAPITAL);
        gEmis.setcCiuEmi(1);
        gEmis.setdDesCiuEmi("ASUNCION (DISTRITO)");
        gEmis.setdTelEmi("21204252");
        gEmis.setdEmailE("administracion@roshka.com");

        List<TgActEco> gActEcoList = new ArrayList<>();
        TgActEco gActEco = new TgActEco();
        gActEco.setcActEco("62010");
        gActEco.setdDesActEco("ACTIVIDADES de PROGRAMACIÓN INFORMÁTICA");
        gActEcoList.add(gActEco);

        gEmis.setgActEcoList(gActEcoList);
        dDatGralOpe.setgEmis(gEmis);

        TgDatRec gDatRec = new TgDatRec();
        gDatRec.setiNatRec(TiNatRec.NO_CONTRIBUYENTE);
        gDatRec.setiTiOpe(TiTiOpe.B2C);
        gDatRec.setcPaisRec(PaisType.PRY);
        gDatRec.setiTipIDRec(TiTipDocRec.CEDULA_PARAGUAYA);
        gDatRec.setdNumIDRec("4579993");
        gDatRec.setdNomRec("Martin Zarza");
        dDatGralOpe.setgDatRec(gDatRec);
        de.setgDatGralOpe(dDatGralOpe);

        // Grupo E
        TgDtipDE gDtipDE = new TgDtipDE();

        TgCamFE gCamFE = new TgCamFE();
        gCamFE.setiIndPres(TiIndPres.OPERACION_ELECTRONICA);
        gDtipDE.setgCamFE(gCamFE);

        TgCamCond gCamCond = new TgCamCond();
        gCamCond.setiCondOpe(TiCondOpe.CREDITO);

        TgPagCred gPagCred = new TgPagCred();
        gPagCred.setiCondCred(TiCondCred.PLAZO);
        gPagCred.setdPlazoCre("60 días");

        gCamCond.setgPagCred(gPagCred);
        gDtipDE.setgCamCond(gCamCond);

        List<TgCamItem> gCamItemList = new ArrayList<>();

        TgCamItem gCamItem = new TgCamItem();
        gCamItem.setdCodInt("001");
        gCamItem.setdDesProSer("Horas de Programación de Software");
        gCamItem.setcUniMed(TcUniMed.M2);
        gCamItem.setdCantProSer(BigDecimal.valueOf(13.6647));

        TgValorItem gValorItem = new TgValorItem();
        gValorItem.setdPUniProSer(BigDecimal.valueOf(95.847));

        TgValorRestaItem gValorRestaItem = new TgValorRestaItem();
        gValorRestaItem.setdDescItem(BigDecimal.valueOf(5.3));
        gValorItem.setgValorRestaItem(gValorRestaItem);
        gCamItem.setgValorItem(gValorItem);

        TgCamIVA gCamIVA = new TgCamIVA();
        gCamIVA.setiAfecIVA(TiAfecIVA.GRAVADO);
        gCamIVA.setdPropIVA(BigDecimal.valueOf(100));
        gCamIVA.setdTasaIVA(BigDecimal.valueOf(10));
        gCamItem.setgCamIVA(gCamIVA);

        gCamItemList.add(gCamItem);

        gDtipDE.setgCamItemList(gCamItemList);
        de.setgDtipDE(gDtipDE);

        // Grupo E
        TgTotSub tgTotSub = new TgTotSub();
        de.setgTotSub(tgTotSub);

        RespuestaRecepcionLoteDE ret = Sifen.recepcionLoteDE(Collections.singletonList(de));
        Assert.assertEquals(200, ret.getCodigoEstado());
        Assert.assertEquals("0300", ret.getdCodRes());
        Assert.assertEquals("Lote recibido con éxito", ret.getdMsgRes());
        logger.info(ret.toString());
    }

}
