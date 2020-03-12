package com.roshka.sifen.sdk.v150;

import com.roshka.sifen.context.SifenCtx;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.http.RespuestaSifen;
import com.roshka.sifen.http.SOAPHelper;
import com.roshka.sifen.model.envi.REnviConsRUC;
import com.roshka.sifen.model.res.RResEnviConsRUC;
import com.roshka.sifen.soap.MessageHelper;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Logger;

public class ConsultaRUC extends ConsultaBase {

    private final static Logger logger = Logger.getLogger(ConsultaRUC.class.toString());

    public ConsultaRUC(SifenCtx sifenCtx) {
        super(sifenCtx);
        this.setUrl(sifenCtx.getSifenConfig().getUrlConsultaRUC());
    }

    public RResEnviConsRUC consultaRUC(REnviConsRUC rEnviConsRUC)
        throws SifenException
    {
        SOAPMessage message = null;
        try {
            message = MessageHelper.createMessage();
            rEnviConsRUC.setupSOAPBody(message.getSOAPBody());
            RespuestaSifen respuestaSifen = SOAPHelper.performSOAPRequest(getSifenCtx(), message, getUrl());
            if (respuestaSifen.llamadaCorrecta()) {
                logger.info("Llamada Correcta!");
            } else {
                logger.info("Llamada Incorrecta!");
            }
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RResEnviConsRUC consultaRUC(String ruc)
        throws SifenException
    {
        REnviConsRUC rEnviConsRUC = new REnviConsRUC();
        rEnviConsRUC.setdId(getSifenCtx().nextdId());
        rEnviConsRUC.setdRUCCons(ruc);
        return consultaRUC(rEnviConsRUC);
    }

}
