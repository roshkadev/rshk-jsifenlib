package com.roshka.sifen.test.de;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.response.RespuestaConsultaRUC;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.response.ruc.TxContRuc;
import org.junit.Ignore;
import org.junit.Test;

public class ConsultaRUCTest extends DETestBase {

    @Test
    @Ignore
    public void testConsultaRUC() throws SifenException {
        RespuestaConsultaRUC ret = Sifen.consultaRUC("80032161");
        assert ret.getCodigoEstado() == 200;
        assert ret.getdCodRes() != null && ret.getdCodRes().equals("0502");
        TxContRuc txContRuc = ret.getxContRUC();
        assert txContRuc != null;
        assert txContRuc.getdRazCons().contains("CLUB");
        assert txContRuc.getdRazCons().contains("CERRO");
        assert txContRuc.getdRazCons().contains("PORTEÑO");
    }

}
