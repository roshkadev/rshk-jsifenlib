package com.roshka.sifen.sdk.v150;

import com.roshka.sifen.context.SifenCtx;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.http.RespuestaSifen;
import com.roshka.sifen.model.SifenObjectFactory;
import com.roshka.sifen.model.envi.REnviConsRUC;
import com.roshka.sifen.model.res.RResEnviConsRUC;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.logging.Logger;

public class ConsultaRUC extends ConsultaBase<REnviConsRUC, RResEnviConsRUC> {

    public static final String NOMBRE_NODO = "rResEnviConsRuc";

    private final static Logger logger = Logger.getLogger(ConsultaRUC.class.toString());

    public ConsultaRUC(SifenCtx sifenCtx) {
        super(sifenCtx);
        this.setUrl(sifenCtx.getSifenConfig().getUrlConsultaRUC());
    }

    @Override
    public RResEnviConsRUC procesarRespuesta(SOAPMessage soapMessage)
            throws SifenException, SOAPException
    {
        SOAPBody soapBody = soapMessage.getSOAPBody();
        final Node nodeRResEnviConsRuc = getMainNode(soapBody, NOMBRE_NODO);
        return SifenObjectFactory.getFromNode(nodeRResEnviConsRuc, RResEnviConsRUC.class);
    }

    public RespuestaSifen<REnviConsRUC, RResEnviConsRUC> consultaRUC(REnviConsRUC rEnviConsRUC)
        throws SifenException
    {
        return ejecutarConsulta(rEnviConsRUC);
    }

    public RespuestaSifen<REnviConsRUC, RResEnviConsRUC> consultaRUC(String ruc)
        throws SifenException
    {
        REnviConsRUC rEnviConsRUC = new REnviConsRUC();
        rEnviConsRUC.setdId(getSifenCtx().nextdId());
        rEnviConsRUC.setdRUCCons(ruc);
        return consultaRUC(rEnviConsRUC);
    }

}
