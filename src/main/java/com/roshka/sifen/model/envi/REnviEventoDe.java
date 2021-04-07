package com.roshka.sifen.model.envi;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.Constants;
import com.roshka.sifen.sdk.v150.beans.EventoDE;
import com.roshka.sifen.util.SifenExceptionUtil;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class REnviEventoDe extends REnviBase {
    public static final String TAG_NAME = "rEnviEventoDe";

    private EventoDE eventoDE;

    @Override
    public void setupSOAPBody(SOAPBody soapBody, SifenConfig sifenConfig) throws SifenException {
        try {
            // Main Element
            SOAPBodyElement rEnviEventoDe = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, TAG_NAME));
            rEnviEventoDe.addChildElement("dId").setTextContent(String.valueOf(this.getdId()));

            SOAPElement gGroupGesEve = rEnviEventoDe.addChildElement("dEvReg").addChildElement("gGroupGesEve");

            gGroupGesEve.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            gGroupGesEve.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", Constants.SIFEN_NS_URI_RECEP_EVENTO);

            // Se completa con los demás elementos del XML
            this.eventoDE.setupSOAPElements(gGroupGesEve, sifenConfig);
        } catch (SOAPException e) {
            throw SifenExceptionUtil.requestPreparationError("Ocurrió un error al preparar el cuerpo de la petición SOAP", e);
        }
    }

    public void setEventoDE(EventoDE eventoDE) {
        this.eventoDE = eventoDE;
    }
}