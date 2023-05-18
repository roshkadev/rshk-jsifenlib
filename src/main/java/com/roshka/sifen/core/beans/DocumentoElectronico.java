package com.roshka.sifen.core.beans;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.*;
import com.roshka.sifen.core.types.TTiDE;
import com.roshka.sifen.internal.Constants;
import com.roshka.sifen.internal.ctx.GenerationCtx;
import com.roshka.sifen.internal.helpers.SignatureHelper;
import com.roshka.sifen.internal.helpers.SoapHelper;
import com.roshka.sifen.internal.response.SifenObjectBase;
import com.roshka.sifen.internal.response.SifenObjectFactory;
import com.roshka.sifen.internal.util.ResponseUtil;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

import static com.roshka.sifen.internal.Constants.SIFEN_CURRENT_VERSION;

/**
 * Clase que representa un Documento Electrónico, incluyendo todos los campos disponibles en un DE en formato XML.
 */
public class DocumentoElectronico extends SifenObjectBase {
    private String Id;
    private String dDVId;
    private LocalDateTime dFecFirma;    // se debe serializar en el formato: yyyy-mm-ddThh:mi:ss
    private short dSisFact;             // sistema que factura (1. Sistema del Cliente, 2. Sistema de facturación gratuita de la SET)

    private TgOpeDE gOpeDE;             // campos de operación del documento electrónico
    private TgTimb gTimb;               // campos del timbrado del documento
    private TdDatGralOpe gDatGralOpe;   // datos generales de la operación
    private TgDtipDE gDtipDE;
    private TgTotSub gTotSub;
    private TgCamGen gCamGen;
    private List<TgCamDEAsoc> gCamDEAsocList;

    private String enlaceQR;
    private final static Logger logger = Logger.getLogger(DocumentoElectronico.class.toString());

    /**
     * Constructor base del Documento Electrónico.
     */
    public DocumentoElectronico() {
    }

    /**
     * Construye un Documento Electrónico en base a un XML.
     *
     * @param xml XML a ser utilizado para la generación del Documento Electrónico.
     * @throws SifenException Si el XML tiene un formato inválido o, si algún dato necesario para la generación del DE
     *                        no pudo ser encontrado.
     */
    public DocumentoElectronico(String xml) throws SifenException {
        xml = xml.replaceAll(">[\\s\r\n]*<", "><");

        // Parseamos el xml
        Document xmlDocument;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw SifenExceptionUtil.xmlParsingError("Se produjo un error al parsear el archivo XML. Formato incorrecto.");
        }

        // Obtenemos el nodo principal
        Node mainNode = xmlDocument.getElementsByTagName("DE").item(0);

        SifenObjectFactory.getFromNode(mainNode, this);
        this.obtenerCDC();
    }

    public DocumentoElectronico(String xml, String CDCrecibido) throws SifenException {
        xml = xml.replaceAll(">[\\s\r\n]*<", "><");

        // Parseamos el xml
        Document xmlDocument;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw SifenExceptionUtil.xmlParsingError("Se produjo un error al parsear el archivo XML. Formato incorrecto.");
        }

        // Obtenemos el nodo principal
        Node mainNode = xmlDocument.getElementsByTagName("DE").item(0);

        SifenObjectFactory.getFromNode(mainNode, this);
        this.obtenerCDC(CDCrecibido);
    }

    /**
     * Calcula el CDC del Documento Electrónico en cuestión y lo retorna. Además de lo anterior, también establece los
     * valores en el lugar correspondiente dentro del objeto.
     *
     * @return CDC calculado del Documento Electrónico.
     * @throws SifenException Si alguno de los campos necesarios para el cálculo del CDC no se encuentra.
     */
    public String obtenerCDC() throws SifenException {
        // Se intenta la generación del CDC
        String CDC;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            CDC = SifenUtil.leftPad(String.valueOf(this.getgTimb().getiTiDE().getVal()), '0', 2) +
                    SifenUtil.leftPad(this.getgDatGralOpe().getgEmis().getdRucEm(), '0', 8) +
                    this.getgDatGralOpe().getgEmis().getdDVEmi() +
                    this.getgTimb().getdEst() +
                    this.getgTimb().getdPunExp() +
                    this.getgTimb().getdNumDoc() +
                    this.getgDatGralOpe().getgEmis().getiTipCont().getVal() +
                    this.getgDatGralOpe().getdFeEmiDE().format(formatter) +
                    this.getgOpeDE().getiTipEmi().getVal() +
                    this.getgOpeDE().getdCodSeg();
        } catch (Exception e) {
            throw SifenExceptionUtil.fieldNotFound("Se produjo un error al generar el CDC. Verificar si todos los campos necesarios están presentes.");
        }

        // Se setean los valores generados en sus lugares correspondientes dentro de la clase
        this.dDVId = SifenUtil.generateDv(CDC);
        this.Id = CDC + this.dDVId;

        return this.Id;
    }

    //    INICIO CAMBIO AM
//    se agrego un overload del metodo obtener CDC para que reciba el parametro ReceivedCDC
    public String obtenerCDC(String ReceivedCDC) throws SifenException {
        this.Id = ReceivedCDC;
        return this.Id;
    }
//            FIN CAMBIO

    /**
     * Genera un XML completo en base al Documento Electrónico actual.
     *
     * @return XML del Documento Electrónico.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        generación del XML no pudo ser encontrado o, si la firma digital del DE falla.
     */
    public String generarXml(GenerationCtx generationCtx) throws SifenException {
        return this.generarXml(generationCtx, generationCtx.getSifenConfig());
    }

    /**
     * Genera un XML completo en base al Documento Electrónico actual.
     *
     * @param sifenConfig Configuración de Sifen a ser utilizada para la generación del XML.
     * @return XML del Documento Electrónico.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        generación del XML no pudo ser encontrado o, si la firma digital del DE falla.
     */
    public String generarXml(GenerationCtx generationCtx, SifenConfig sifenConfig) throws SifenException {
        if (sifenConfig == null) {
            throw SifenExceptionUtil.invalidConfiguration("Falta establecer la configuración del Sifen.");
        }

        String xml = null;
        try {
            SOAPMessage message = this.setupSOAPElements(generationCtx, 1, sifenConfig);
            xml = ResponseUtil.getXmlFromMessage(message, true);
        } catch (SOAPException e) {
            logger.warning("Se produjo un error al generar el XML.");
            e.printStackTrace();
        }

        return xml;
    }

    /**
     * Genera un XML completo en base al Documento Electrónico actual, y lo guarda como archivo en la ruta definida.
     *
     * @param rutaDestino Ruta absoluta (con nombre de archivo y extensión incluidos) en la que será creada el archivo
     *                    XML.<br> Ejemplo: "C:\Users\Roshka\Documents\de.xml"
     * @return <strong>true</strong> si el archivo fue creado correctamente, <strong>false</strong> de lo contrario.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        generación del XML no pudo ser encontrado o, si la firma digital del DE falla.
     */
    public boolean generarXml(GenerationCtx generationCtx, String rutaDestino) throws SifenException {
        return this.generarXml(generationCtx, rutaDestino, Sifen.getSifenConfig());
    }

    /**
     * Genera un XML completo en base al Documento Electrónico actual, y lo guarda como archivo en la ruta definida.
     *
     * @param rutaDestino Ruta absoluta (con nombre de archivo y extensión incluidos) en la que será creada el archivo
     *                    XML.<br> Ejemplo: "C:\Users\Roshka\Documents\de.xml"
     * @param sifenConfig Configuración de Sifen a ser utilizada para la generación del XML.
     * @return <strong>true</strong> si el archivo fue creado correctamente, <strong>false</strong> de lo contrario.
     * @throws SifenException Si la configuración de Sifen no fue establecida o, si algún dato necesario para la
     *                        generación del XML no pudo ser encontrado o, si la firma digital del DE falla.
     */
    public boolean generarXml(GenerationCtx generationCtx, String rutaDestino, SifenConfig sifenConfig) throws SifenException {
        // Obtenemos el xml en string
        String xml = this.generarXml(generationCtx, sifenConfig);

        // Creamos o modificamos el archivo, y escribimos en él el xml.
        boolean res = false;
        try {
            FileWriter fileWriter = new FileWriter(rutaDestino, false);
            fileWriter.write(xml);
            fileWriter.close();
            res = true;
        } catch (IOException e) {
            logger.warning("Se produjo un error al escribir en el archivo especificado.");
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Método interno, no usar.
     *
     * @param dId         -
     * @param sifenConfig -
     * @return -
     * @throws SOAPException  -
     * @throws SifenException -
     */
    public SOAPMessage setupSOAPElements(GenerationCtx generationCtx, long dId, SifenConfig sifenConfig) throws SOAPException, SifenException {
        SOAPMessage message = SoapHelper.createSoapMessage();
        SOAPBody soapBody = message.getSOAPBody();

        // Main Element
        SOAPBodyElement rResEnviDe = soapBody.addBodyElement(new QName(Constants.SIFEN_NS_URI, "rEnviDe"));
        rResEnviDe.addChildElement("dId").setTextContent(String.valueOf(dId));

        SOAPElement xDE = rResEnviDe.addChildElement("xDE");
        this.setupDE(generationCtx, xDE, sifenConfig);

        return message;
    }

    /**
     * Método interno, no usar.
     *
     * @param parentNode  -
     * @param sifenConfig -
     * @throws SOAPException  -
     * @throws SifenException -
     */
//    INICIO CAMBIO AM
//    se realizo un overload del metodo  setupDE para que reciba receivedCDC
    public void setupDE(GenerationCtx generationCtx, SOAPElement parentNode, SifenConfig sifenConfig, String receivedCDC) throws SOAPException, SifenException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        SOAPElement rDE = parentNode.addChildElement(new QName(Constants.SIFEN_NS_URI, "rDE"));

        rDE.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rDE.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:schemaLocation", Constants.SIFEN_NS_URI_RECEP_DE);
        rDE.addChildElement("dVerFor").setTextContent(SIFEN_CURRENT_VERSION);

        this.obtenerCDC(receivedCDC);

        SOAPElement DE = rDE.addChildElement("DE");
        DE.setAttribute("Id", this.getId());
        Attr idAttribute = DE.getAttributeNode("Id");
        DE.setIdAttributeNode(idAttribute, true);

        DE.addChildElement("dDVId").setTextContent(this.getdDVId());
        DE.addChildElement("dFecFirma").setTextContent(this.getdFecFirma().format(formatter));
        DE.addChildElement("dSisFact").setTextContent(String.valueOf(this.getdSisFact()));

        // Se prepara el cuerpo del documento electrónico
        TTiDE iTiDE = this.gTimb.getiTiDE();

        this.gOpeDE.setupSOAPElements(DE, iTiDE);
        this.gTimb.setupSOAPElements(DE);
        this.gDatGralOpe.setupSOAPElements(DE, iTiDE);
        this.gDtipDE.setupSOAPElements(generationCtx, DE, iTiDE, this.gDatGralOpe);

        if (iTiDE.getVal() != 7)
            this.gTotSub.setupSOAPElements(DE, iTiDE, this.getgDtipDE(), this.gDatGralOpe.getgOpeCom());

        if (this.gCamGen != null)
            this.gCamGen.setupSOAPElements(DE, iTiDE);

        if (iTiDE.getVal() == 4 || iTiDE.getVal() == 5 || iTiDE.getVal() == 6 || ((iTiDE.getVal() == 1 || iTiDE.getVal() == 7 || iTiDE.getVal() == 8) && this.gCamDEAsocList != null)) {
            boolean withholdingExists = false; // Retención
            if ((iTiDE.getVal() == 1 || iTiDE.getVal() == 4) && this.gDtipDE.getgCamCond().getgPaConEIniList() != null) {
                for (TgPaConEIni gPaConEIni : this.gDtipDE.getgCamCond().getgPaConEIniList()) {
                    if (gPaConEIni.getiTiPago().getVal() == 10) {
                        withholdingExists = true;
                        break;
                    }
                }
            }

            for (TgCamDEAsoc gCamDEAsoc : this.gCamDEAsocList) {
                gCamDEAsoc.setupSOAPElements(DE, this.gDatGralOpe.getgOpeCom() != null ? this.gDatGralOpe.getgOpeCom().getiTipTra() : null, withholdingExists);
            }
        }

        // Firma Digital del XML
        SignedInfo signedInfo = SignatureHelper.signDocument(sifenConfig, rDE, this.getId());

        // Preparación de la URL del QR
        this.enlaceQR = this.generateQRLink(signedInfo, sifenConfig);
        SOAPElement gCamFuFD = rDE.addChildElement("gCamFuFD");
        gCamFuFD.addChildElement("dCarQR").setTextContent(this.enlaceQR);
    }

    //    FIN CAMBIO
    public void setupDE(GenerationCtx generationCtx, SOAPElement parentNode, SifenConfig sifenConfig) throws SOAPException, SifenException {
        this.setupDE(generationCtx, parentNode, sifenConfig, this.obtenerCDC());
    }

    /**
     * Método interno, no usar.
     *
     * @param value -
     * @throws SifenException -
     */
    @Override
    public void setValueFromChildNode(Node value) throws SifenException {
        switch (value.getLocalName()) {
            case "dDVId":
                this.dDVId = ResponseUtil.getTextValue(value);
                break;
            case "dFecFirma":
                this.dFecFirma = ResponseUtil.getDateTimeValue(value);
                break;
            case "dSisFact":
                this.dSisFact = Short.parseShort(ResponseUtil.getTextValue(value));
                break;
            case "gOpeDE":
                this.gOpeDE = SifenObjectFactory.getFromNode(value, TgOpeDE.class);
                break;
            case "gTimb":
                this.gTimb = SifenObjectFactory.getFromNode(value, TgTimb.class);
                break;
            case "gDatGralOpe":
                this.gDatGralOpe = SifenObjectFactory.getFromNode(value, TdDatGralOpe.class);
                break;
            case "gDtipDE":
                this.gDtipDE = SifenObjectFactory.getFromNode(value, TgDtipDE.class);
                break;
            case "gTotSub":
                this.gTotSub = SifenObjectFactory.getFromNode(value, TgTotSub.class);
                break;
            case "gCamGen":
                this.gCamGen = SifenObjectFactory.getFromNode(value, TgCamGen.class);
                break;
            case "gCamDEAsoc":
                if (this.gCamDEAsocList == null) {
                    this.gCamDEAsocList = new ArrayList<>();
                }
                this.gCamDEAsocList.add(SifenObjectFactory.getFromNode(value, TgCamDEAsoc.class));
                break;
        }
    }

    private String generateQRLink(SignedInfo signedInfo, SifenConfig sifenConfig) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LinkedHashMap<String, String> queryParams = new LinkedHashMap<>();

        queryParams.put("nVersion", SIFEN_CURRENT_VERSION);
        queryParams.put("Id", this.getId());
        queryParams.put("dFeEmiDE", SifenUtil.bytesToHex(this.getgDatGralOpe().getdFeEmiDE().format(formatter).getBytes(StandardCharsets.UTF_8)));

        if (this.getgDatGralOpe().getgDatRec().getiNatRec().getVal() == 1) {
            queryParams.put("dRucRec", this.getgDatGralOpe().getgDatRec().getdRucRec());
        } else if (this.getgDatGralOpe().getgDatRec().getiTiOpe().getVal() != 4 && this.getgDatGralOpe().getgDatRec().getdNumIDRec() != null) {
            queryParams.put("dNumIDRec", this.getgDatGralOpe().getgDatRec().getdNumIDRec());
        } else {
            queryParams.put("dNumIDRec", "0");
        }

        if (this.getgTimb().getiTiDE().getVal() != 7) {
            queryParams.put("dTotGralOpe", String.valueOf(this.getgTotSub().getdTotGralOpe()));
            queryParams.put("dTotIVA",
                    this.getgDatGralOpe().getgOpeCom().getiTImp().getVal() == 1 || this.getgDatGralOpe().getgOpeCom().getiTImp().getVal() == 5
                            ? String.valueOf(this.getgTotSub().getdTotIVA())
                            : "0"
            );
        } else {
            queryParams.put("dTotGralOpe", "0");
            queryParams.put("dTotIVA", "0");
        }

        queryParams.put("cItems", String.valueOf(this.getgDtipDE().getgCamItemList().size()));

        byte[] digestValue = Base64.getEncoder().encode(((Reference) signedInfo.getReferences().get(0)).getDigestValue());
        queryParams.put("DigestValue", SifenUtil.bytesToHex(digestValue));
        queryParams.put("IdCSC", sifenConfig.getIdCSC());

        String urlParamsString = SifenUtil.buildUrlParams(queryParams);
        String hashedParams = SifenUtil.sha256Hex(urlParamsString + sifenConfig.getCSC());

        return sifenConfig.getUrlConsultaQr() + urlParamsString + "&cHashQR=" + hashedParams;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getdDVId() {
        return dDVId;
    }

    public LocalDateTime getdFecFirma() {
        return dFecFirma;
    }

    public void setdFecFirma(LocalDateTime dFecFirma) {
        this.dFecFirma = dFecFirma;
    }

    public short getdSisFact() {
        return dSisFact;
    }

    public void setdSisFact(short dSisFact) {
        this.dSisFact = dSisFact;
    }

    public TgOpeDE getgOpeDE() {
        return gOpeDE;
    }

    public void setgOpeDE(TgOpeDE gOpeDE) {
        this.gOpeDE = gOpeDE;
    }

    public TgTimb getgTimb() {
        return gTimb;
    }

    public void setgTimb(TgTimb gTimb) {
        this.gTimb = gTimb;
    }

    public TdDatGralOpe getgDatGralOpe() {
        return gDatGralOpe;
    }

    public void setgDatGralOpe(TdDatGralOpe gDatGralOpe) {
        this.gDatGralOpe = gDatGralOpe;
    }

    public TgDtipDE getgDtipDE() {
        return gDtipDE;
    }

    public void setgDtipDE(TgDtipDE gDtipDE) {
        this.gDtipDE = gDtipDE;
    }

    public TgTotSub getgTotSub() {
        return gTotSub;
    }

    public void setgTotSub(TgTotSub gTotSub) {
        this.gTotSub = gTotSub;
    }

    public TgCamGen getgCamGen() {
        return gCamGen;
    }

    public void setgCamGen(TgCamGen gCamGen) {
        this.gCamGen = gCamGen;
    }

    public List<TgCamDEAsoc> getgCamDEAsocList() {
        return gCamDEAsocList;
    }

    public void setgCamDEAsocList(List<TgCamDEAsoc> gCamDEAsocList) {
        this.gCamDEAsocList = gCamDEAsocList;
    }

    public String getEnlaceQR() {
        return enlaceQR;
    }

    public void setEnlaceQR(String enlaceQR) {
        this.enlaceQR = enlaceQR;
    }

}