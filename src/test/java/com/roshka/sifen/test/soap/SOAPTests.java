package com.roshka.sifen.test.soap;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.de.*;
import com.roshka.sifen.model.de.types.*;
import com.roshka.sifen.model.departamentos.TDepartamento;
import com.roshka.sifen.model.monedas.CMondT;
import com.roshka.sifen.model.paises.PaisType;
import com.roshka.sifen.model.unidades_medida.TcUniMed;
import com.roshka.sifen.sdk.v150.beans.DocumentoElectronico;
import com.roshka.sifen.sdk.v150.response.RespuestaSifen;
import com.roshka.sifen.soap.MessageHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class SOAPTests {
    private final static Logger logger = Logger.getLogger(SOAPTests.class.toString());

    private static long currentdId = 0;

    @BeforeClass
    public static void setupSifenConfig() {
        SifenConfig sifenConfig = new SifenConfig(SifenConfig.TipoAmbiente.DEV, "C:\\Users\\mdazarza\\Documents\\taxare.pfx",
                "Pqntslc0$", SifenConfig.TipoCertificadoCliente.PFX);

        Sifen.setSifenConfig(sifenConfig);
    }

    @Test
    public void testBasicMessage() throws SOAPException, IOException {
        SOAPMessage soapMessage = MessageHelper.createMessage();
        soapMessage.writeTo(System.out);
    }

    @Test
    public void testConsultaRUC() throws SifenException {
        RespuestaSifen ret = Sifen.consultaRUC(++currentdId, "80080553");
        logger.info(ret.toString());
    }

    @Test
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
        gTimb.setTiDE(TTiDE.FACTURA_ELECTRONICA);
        gTimb.setdNumTim(12557693);
        gTimb.setdEst("1");
        gTimb.setdPunExp("2");
        gTimb.setdNumDoc("3");
        gTimb.setdFeIniT(LocalDate.parse("2019-07-31"));
        DE.setgTimb(gTimb);

        // Grupo D
        TdDatGralOpe dDatGralOpe = new TdDatGralOpe();
        dDatGralOpe.setdFeEmiDE(currentDate);

        TgOpeCom gOpeCom = new TgOpeCom();
        gOpeCom.setTipTra(TTipTra.PRESTACION_SERVICIOS);
        gOpeCom.setiTImp(TTImp.IVA);
        gOpeCom.setcMoneOpe(CMondT.PYG);
        dDatGralOpe.setgOpeCom(gOpeCom);

        TgEmis gEmis = new TgEmis();
        gEmis.setdRucEmi("80080553");
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

        TgActEco gActEco = new TgActEco();
        gActEco.setcActEco("69201");
        gActEco.setdDesActEco("ACTIVIDADES DE CONTABILIDAD, TENEDURÍA DE LIBROS (CONTADOR)");
        gEmis.setgActEcoList(Collections.singletonList(gActEco));
        dDatGralOpe.setgEmis(gEmis);

        TgDatRec gDatRec = new TgDatRec();
        gDatRec.setiNatRec(TiNatRec.NO_CONTRIBUYENTE);
        gDatRec.setiTiOpe(TiTiOpe.B2C);
        gDatRec.setcPaisRec(PaisType.PRY);
        gDatRec.setiTipIDRec(TiTipDocRec.CEDULA_PARAGUAYA);
        gDatRec.setdNumIDRec("4579993");
        gDatRec.setdNomRec("Martin Zarza");
        dDatGralOpe.setgDatRec(gDatRec);
        DE.setdDatGralOpe(dDatGralOpe);

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

        RespuestaSifen ret = Sifen.recepcionDE(++currentdId, DE);
        logger.info(ret.toString());
    }
}
