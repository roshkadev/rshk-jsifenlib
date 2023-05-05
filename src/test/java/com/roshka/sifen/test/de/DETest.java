package com.roshka.sifen.test.de;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.EventosDE;
import com.roshka.sifen.core.beans.ValidezFirmaDigital;
import com.roshka.sifen.core.beans.response.*;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.*;
import com.roshka.sifen.core.fields.request.event.TgGroupTiEvt;
import com.roshka.sifen.core.fields.request.event.TrGeVeDisconf;
import com.roshka.sifen.core.fields.request.event.TrGesEve;
import com.roshka.sifen.core.types.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DETest extends DETestBase {
    @Test
    @Ignore
    public void testRecepcionDE() throws SifenException {
        LocalDateTime currentDate = LocalDateTime.now();

        // Grupo A
        DocumentoElectronico DE = new DocumentoElectronico();
        DE.setdFecFirma(currentDate);
        DE.setdSisFact((short) 1);

        // Grupo B
        TgOpeDE gOpeDE = new TgOpeDE();
        gOpeDE.setiTipEmi(TTipEmi.NORMAL);
        DE.setgOpeDE(gOpeDE);

        // Grupo C
        TgTimb gTimb = new TgTimb();
        gTimb.setiTiDE(TTiDE.FACTURA_ELECTRONICA);
        gTimb.setdNumTim(12557662);
        gTimb.setdEst("001");
        gTimb.setdPunExp("002");
        gTimb.setdNumDoc("0000007");
        gTimb.setdFeIniT(LocalDate.parse("2019-07-31"));
        DE.setgTimb(gTimb);

        // Grupo D
        TdDatGralOpe dDatGralOpe = new TdDatGralOpe();
        dDatGralOpe.setdFeEmiDE(currentDate);

        TgOpeCom gOpeCom = new TgOpeCom();
        gOpeCom.setiTipTra(TTipTra.PRESTACION_SERVICIOS);
        gOpeCom.setiTImp(TTImp.IVA);
        gOpeCom.setcMoneOpe(CMondT.PYG);
        dDatGralOpe.setgOpeCom(gOpeCom);

        TgEmis gEmis = new TgEmis();
        gEmis.setdRucEm("80080553");
        gEmis.setdDVEmi("4");
        gEmis.setiTipCont(TiTipCont.PERSONA_JURIDICA);
        gEmis.setdNomEmi("DE generado en ambiente de prueba - sin valor comercial ni fiscal");
        gEmis.setdDirEmi("Mayor Bullo");
        gEmis.setdNumCas("670");
        gEmis.setcDepEmi(TDepartamento.CAPITAL);
        gEmis.setcCiuEmi(1);
        gEmis.setdDesCiuEmi("ASUNCION (DISTRITO)");
        gEmis.setdTelEmi("212376717");
        gEmis.setdEmailE("administracion@taxare.com.py");

        List<TgActEco> gActEcoList = new ArrayList<>();
        TgActEco gActEco = new TgActEco();
        gActEco.setcActEco("69209");
        gActEco.setdDesActEco("ACTIVIDADES DE CONTABILIDAD, TENEDURÍA DE LIBROS, AUDITORIA Y ASESORIA FISCAL N.C.P.");
        gActEcoList.add(gActEco);

        TgActEco gActEco2 = new TgActEco();
        gActEco2.setcActEco("62090");
        gActEco2.setdDesActEco("OTRAS ACTIVIDADES DE TECNOLOGÍA DE LA INFORMACIÓN Y SERVICIOS INFORMÁTICOS");
        gActEcoList.add(gActEco2);

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
        DE.setgDatGralOpe(dDatGralOpe);

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
        for (int i = 0; i < 2; i++) {
            TgCamItem gCamItem = new TgCamItem();
            gCamItem.setdCodInt(i == 0 ? "001" : "002");
            gCamItem.setdDesProSer(i == 0 ? "Servicio de Liquidación de IVA" : "Servicio de Liquidación de IRP");
            gCamItem.setcUniMed(TcUniMed.UNI);
            gCamItem.setdCantProSer(BigDecimal.valueOf(1));

            TgValorItem gValorItem = new TgValorItem();
            gValorItem.setdPUniProSer(BigDecimal.valueOf(120000));

            TgValorRestaItem gValorRestaItem = new TgValorRestaItem();
            gValorItem.setgValorRestaItem(gValorRestaItem);
            gCamItem.setgValorItem(gValorItem);

            TgCamIVA gCamIVA = new TgCamIVA();
            gCamIVA.setiAfecIVA(TiAfecIVA.GRAVADO);
            gCamIVA.setdPropIVA(BigDecimal.valueOf(100));
            gCamIVA.setdTasaIVA(BigDecimal.valueOf(10));
            gCamItem.setgCamIVA(gCamIVA);

            gCamItemList.add(gCamItem);
        }

        gDtipDE.setgCamItemList(gCamItemList);
        DE.setgDtipDE(gDtipDE);

        // Grupo E
        DE.setgTotSub(new TgTotSub());

        logger.info("CDC del Documento Electrónico -> " + DE.obtenerCDC());

        RespuestaRecepcionDE ret = Sifen.recepcionDE(DE);
        logger.info(ret.toString());
    }

    @Test
    @Ignore
    public void testConsultaDE() throws SifenException {
        RespuestaConsultaDE ret = Sifen.consultaDE("01800805534001002000000722021040613265708133");
        logger.info(ret.toString());
    }

    @Test
    @Ignore
    public void testRecepcionEvento() throws SifenException {
        LocalDateTime currentDate = LocalDateTime.now();

        // Evento de Cancelación
        /*TrGeVeCan trGeVeCan = new TrGeVeCan();
        trGeVeCan.setId("01800805534001002000000722021040613265708133");
        trGeVeCan.setmOtEve("Prueba de cancelación de documento electrónico");

        TgGroupTiEvt tgGroupTiEvt = new TgGroupTiEvt();
        tgGroupTiEvt.setrGeVeCan(trGeVeCan);*/

        // Evento de Conformidad
        /*TrGeVeConf trGeVeConf = new TrGeVeConf();
        trGeVeConf.setId("01800805534001002000000722021040613265708133");
        trGeVeConf.setiTipConf(TiTipConf.CONFORMIDAD_PARCIAL);
        trGeVeConf.setdFecRecep(currentDate);

        TgGroupTiEvt tgGroupTiEvt = new TgGroupTiEvt();
        tgGroupTiEvt.setrGeVeConf(trGeVeConf);*/

        // Evento de Disconformidad
        TrGeVeDisconf trGeVeDisconf = new TrGeVeDisconf();
        trGeVeDisconf.setId("01800805534001002000000722021040613265708133");
        trGeVeDisconf.setmOtEve("Prueba de disconformidad de documento electrónico");

        TgGroupTiEvt tgGroupTiEvt = new TgGroupTiEvt();
        tgGroupTiEvt.setrGeVeDisconf(trGeVeDisconf);

        TrGesEve rGesEve1 = new TrGesEve();
        rGesEve1.setId("1");
        rGesEve1.setdFecFirma(currentDate);
        rGesEve1.setgGroupTiEvt(tgGroupTiEvt);

        EventosDE eventosDE = new EventosDE();
        eventosDE.setrGesEveList(Collections.singletonList(rGesEve1));

        RespuestaRecepcionEvento ret = Sifen.recepcionEvento(eventosDE);
        logger.info(ret.toString());
    }

    @Test
    @Ignore
    public void testConversionXml() throws SifenException, IOException {
        String xml = new String(Files.readAllBytes(Paths.get("C:\\Users\\mzarz\\Desktop\\de.xml")), StandardCharsets.UTF_8);
        DocumentoElectronico DE = new DocumentoElectronico(xml);
        logger.info(DE.toString());
    }

    private DocumentoElectronico setupDocumentoElectronico() {
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
        gTimb.setdNumTim(12557662);
        gTimb.setdEst("001");
        gTimb.setdPunExp("002");
        gTimb.setdNumDoc("0000008");
        gTimb.setdFeIniT(LocalDate.parse("2019-07-31"));
        de.setgTimb(gTimb);

        // Grupo D
        TdDatGralOpe dDatGralOpe = new TdDatGralOpe();
        dDatGralOpe.setdFeEmiDE(currentDate);

        TgOpeCom gOpeCom = new TgOpeCom();
        gOpeCom.setiTipTra(TTipTra.PRESTACION_SERVICIOS);
        gOpeCom.setiTImp(TTImp.IVA);
        gOpeCom.setcMoneOpe(CMondT.PYG);
        dDatGralOpe.setgOpeCom(gOpeCom);

        TgEmis gEmis = new TgEmis();
        gEmis.setdRucEm("80080553");
        gEmis.setdDVEmi("4");
        gEmis.setiTipCont(TiTipCont.PERSONA_JURIDICA);
        gEmis.setdNomEmi("de generado en ambiente de prueba - sin valor comercial ni fiscal");
        gEmis.setdDirEmi("Mayor Bullo");
        gEmis.setdNumCas("670");
        gEmis.setcDepEmi(TDepartamento.CAPITAL);
        gEmis.setcCiuEmi(1);
        gEmis.setdDesCiuEmi("ASUNCION (DISTRITO)");
        gEmis.setdTelEmi("212376717");
        gEmis.setdEmailE("administracion@taxare.com.py");

        List<TgActEco> gActEcoList = new ArrayList<>();
        TgActEco gActEco = new TgActEco();
        gActEco.setcActEco("69209");
        gActEco.setdDesActEco("ACTIVIDADES de CONTABILIDAD, TENEDURÍA de LIBROS, AUDITORIA Y ASESORIA FISCAL N.C.P.");
        gActEcoList.add(gActEco);

        TgActEco gActEco2 = new TgActEco();
        gActEco2.setcActEco("62090");
        gActEco2.setdDesActEco("OTRAS ACTIVIDADES de TECNOLOGÍA de LA INFORMACIÓN Y SERVICIOS INFORMÁTICOS");
        gActEcoList.add(gActEco2);

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


        gDtipDE.setgCamItemList(new ArrayList<>());
        de.setgDtipDE(gDtipDE);

        // Grupo E
        de.setgTotSub(new TgTotSub());

        return de;

    }

    private TgCamItem createTgCamItem(
            String codigo,
            String descripcion,
            BigDecimal cantidad,
            BigDecimal precioUnitario,
            TcUniMed uniMed,
            BigDecimal pctDescuento
    ) {
        TgCamItem gCamItem = new TgCamItem();

        gCamItem.setdCodInt(codigo);
        gCamItem.setdDesProSer(descripcion);
        gCamItem.setcUniMed(uniMed);
        gCamItem.setdCantProSer(cantidad);

        TgValorItem gValorItem = new TgValorItem();
        gValorItem.setdPUniProSer(precioUnitario);

        TgValorRestaItem gValorRestaItem = new TgValorRestaItem();
        gValorItem.setgValorRestaItem(gValorRestaItem);
        gValorRestaItem.setdDescItem(pctDescuento);
        gCamItem.setgValorItem(gValorItem);

        TgCamIVA gCamIVA = new TgCamIVA();
        gCamIVA.setiAfecIVA(TiAfecIVA.GRAVADO);
        gCamIVA.setdPropIVA(BigDecimal.valueOf(100));
        gCamIVA.setdTasaIVA(BigDecimal.valueOf(10));
        gCamItem.setgCamIVA(gCamIVA);

        return gCamItem;
    }

    @Test
    @Ignore
    public void testRecepcionLoteDE() throws SifenException {

        DocumentoElectronico de = setupDocumentoElectronico();

        TgCamItem tgCamItem00 = createTgCamItem(
                "001",
                "Servicio de Liquidación de IVA",
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(120000),
                TcUniMed.UNI,
                BigDecimal.valueOf(0)
        );

        TgCamItem tgCamItem01 = createTgCamItem(
                "002",
                "Servicio de Liquidación de IRP",
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(88000),
                TcUniMed.UNI,
                BigDecimal.valueOf(1.3)
        );

        TgDtipDE tgDtipDE = de.getgDtipDE();
        tgDtipDE.getgCamItemList().add(tgCamItem00);
        tgDtipDE.getgCamItemList().add(tgCamItem01);

        RespuestaRecepcionLoteDE ret = Sifen.recepcionLoteDE(Collections.singletonList(de));
        Assert.assertEquals(200, ret.getCodigoEstado());
        Assert.assertEquals("0300", ret.getdCodRes());
        Assert.assertEquals("Lote recibido con éxito", ret.getdMsgRes());
        logger.info(ret.toString());
    }

    @Test
    @Ignore
    public void testConsultaLoteDe() throws SifenException {
        RespuestaConsultaLoteDE ret = Sifen.consultaLoteDE("109051592123784");
        logger.info(ret.toString());
    }

    @Test
    @Ignore
    public void testValidacionDE() {
        ValidezFirmaDigital validity = Sifen.validarFirmaDE("D:\\de.xml");
        logger.info(validity.isValido() ? "Firma digital válida" : "Firma digital inválida");
    }

}
