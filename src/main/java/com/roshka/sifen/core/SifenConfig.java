package com.roshka.sifen.core;

import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.util.SifenUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import static com.roshka.sifen.internal.Constants.SDK_CURRENT_VERSION;

/**
 * Clase de configuración de las peticiones Sifen.<br>
 * <ul>
 *      <li>ambiente (TipoAmbiente): Tipo de ambiente a utilizar (desarrollo o producción). Dependiendo de esto se
 *      definen las urls a utilizar.</li>
 *
 *      <li>urlBase (String): URL a la que se hará la petición. Hay una para cada tipo de ambiente. Se puede sobreescribir.</li>
 *
 *      <li>urlRecibe, urlRecibeLote, urlEvento, urlConsultaLote, urlConsultaRUC, urlConsulta (String):
 *      URLs para las peticiones específicas. Tienen valores por defecto (obtenidos del MT), pero pueden ser sobreescritas.</li>
 *
 *      <li>usarCertificadoCliente (Boolean): Define si se utiliza o no el certificado proporcionado para la
 *      autenticación y firma de las peticiones.
 *      Se recomienda utilizar el valor por defecto (true), pero es posible sobreescribirlo en caso de estar
 *      realizando a otro nivel la autenticación por certificado (Ej.: Forward Proxy).</li>
 *
 *      <li>certificadoCliente, contrasenaCertificadoCliente (String): Certificado a utilizar (ruta del archivo o
 *      archivo codificado en Base64), junto a la contraseña.</li>
 *
 *      <li>tipoCertificadoCliente (TipoCertificadoCliente): Tipo de archivo del certificado. Solo PFX es soportado
 *      actualmente.</li>
 * </ul>
 */
public class SifenConfig {
    // Enums

    /**
     * Enum con los tipos de certificados disponibles para la conexión segura y la firma digital.
     */
    public enum TipoCertificadoCliente {PFX} // Implementar PEM y DER

    /**
     * Enum con los tipos de ambientes disponibles en Sifen.
     */
    public enum TipoAmbiente {DEV, PROD}

    // Atributos
    private static final String SIFEN_AMBIENTE_KEY = "sifen.ambiente";
    private TipoAmbiente ambiente;


    private static final String SIFEN_URL_BASE_KEY = "sifen.url_base";
    private String urlBase;

    private String urlBaseLocal;
    private String urlConsultaQr;

    private String pathRecibe;
    private String pathRecibeLote;
    private String pathEvento;
    private String pathConsultaLote;
    private String pathConsultaRUC;
    private String pathConsulta;

    private static final String SIFEN_HABILITAR_NOTA_TECNICA_13_KEY = "sifen.habilitar_nota_tecnica_13";
    private boolean habilitarNotaTecnica13;


    private static final String SIFEN_USAR_CERTIFICADO_CLIENTE_KEY = "sifen.certificado_cliente.usar";
    private boolean usarCertificadoCliente;
    private static final String SIFEN_TIPO_CERTIFICADO_CLIENTE_KEY = "sifen.certificado_cliente.tipo";
    private TipoCertificadoCliente tipoCertificadoCliente;
    private static final String SIFEN_ARCHIVO_CERTIFICADO_CLIENTE_KEY = "sifen.certificado_cliente.archivo";
    private String certificadoCliente;
    private static final String SIFEN_PASSWORD_CERTIFICADO_CLIENTE_KEY = "sifen.certificado_cliente.contrasena";
    private String contrasenaCertificadoCliente;

    private static final String SIFEN_ID_CSC_KEY = "sifen.csc.id";
    private String idCSC;
    private static final String SIFEN_CSC_KEY = "sifen.csc";
    private String CSC;

    private final int httpConnectTimeout;
    private final int httpReadTimeout;
    private final String userAgent;

    // Valores Finales
    private final String URL_BASE_DEV = "https://sifen-test.set.gov.py";
    private final String URL_BASE_PROD = "https://sifen.set.gov.py";

    private final String URL_CONSULTA_QR_DEV = "https://ekuatia.set.gov.py/consultas-test/qr?";
    private final String URL_CONSULTA_QR_PROD = "https://ekuatia.set.gov.py/consultas/qr?";

    // Constructores
    public SifenConfig() {
        this.ambiente = TipoAmbiente.DEV;
        this.urlBaseLocal = URL_BASE_DEV;
        this.urlConsultaQr = URL_CONSULTA_QR_DEV;

        this.pathRecibe = "/de/ws/sync/recibe.wsdl";
        this.pathRecibeLote = "/de/ws/async/recibe-lote.wsdl";
        this.pathEvento = "/de/ws/eventos/evento.wsdl";
        this.pathConsultaLote = "/de/ws/consultas/consulta-lote.wsdl";
        this.pathConsultaRUC = "/de/ws/consultas/consulta-ruc.wsdl";
        this.pathConsulta = "/de/ws/consultas/consulta.wsdl";
        this.usarCertificadoCliente = true;

        this.idCSC = "0002";
        this.CSC = "EFGH0000000000000000000000000000";

        this.httpConnectTimeout = 15 * 1000; // 15 Segundos
        this.httpReadTimeout = 45 * 1000; // 45 Segundos
        this.userAgent = "rshk-jsifenlib" + "/" + SDK_CURRENT_VERSION + " (LVEA)";

        this.habilitarNotaTecnica13 = false;
    }

    public SifenConfig(TipoAmbiente tipoAmbiente, TipoCertificadoCliente tipoCertificadoCliente, String certificadoCliente,
                       String contrasenaCertificadoCliente) {
        this();
        this.setAmbiente(tipoAmbiente);

        this.usarCertificadoCliente = true;
        this.certificadoCliente = certificadoCliente;
        this.contrasenaCertificadoCliente = contrasenaCertificadoCliente;
        this.tipoCertificadoCliente = tipoCertificadoCliente;
    }

    public SifenConfig(TipoAmbiente tipoAmbiente, String idCSC, String CSC, TipoCertificadoCliente tipoCertificadoCliente,
                       String certificadoCliente, String contrasenaCertificadoCliente) {
        this(tipoAmbiente, tipoCertificadoCliente, certificadoCliente, contrasenaCertificadoCliente);

        this.setIdCSC(idCSC);
        this.CSC = CSC;
    }

    /**
     * Carga la configuración de Sifen a ser utilizada desde un conjunto de propiedades.
     *
     * @param propiedades Properties que contengan las configuraciones a ser utilizadas.
     * @return La clase de configuración de Sifen con los datos obtenidos de las propiedades.
     * @throws SifenException Si los valores proveídos son incorrectos.
     */
    public static SifenConfig cargarConfiguracion(Properties propiedades) throws SifenException {
        SifenConfig sifenConfig = new SifenConfig();

        try {
            sifenConfig.setAmbiente(TipoAmbiente.valueOf(propiedades.getProperty(SIFEN_AMBIENTE_KEY)));
        } catch (IllegalArgumentException e) {
            throw SifenExceptionUtil.invalidConfiguration("El tipo de ambiente especificado no existe.", e);
        }

        if (propiedades.containsKey(SIFEN_URL_BASE_KEY)) {
            sifenConfig.setUrlBase(propiedades.getProperty(SIFEN_URL_BASE_KEY));
        }

        if (propiedades.containsKey(SIFEN_USAR_CERTIFICADO_CLIENTE_KEY)) {
            sifenConfig.setUsarCertificadoCliente(Boolean.parseBoolean(propiedades.getProperty(SIFEN_USAR_CERTIFICADO_CLIENTE_KEY)));
        }

        try {
            sifenConfig.setTipoCertificadoCliente(TipoCertificadoCliente.valueOf(propiedades.getProperty(SIFEN_TIPO_CERTIFICADO_CLIENTE_KEY)));
        } catch (IllegalArgumentException e) {
            throw SifenExceptionUtil.invalidConfiguration("El tipo de ambiente especificado no existe.", e);
        }

        sifenConfig.setCertificadoCliente(propiedades.getProperty(SIFEN_ARCHIVO_CERTIFICADO_CLIENTE_KEY));
        sifenConfig.setContrasenaCertificadoCliente(propiedades.getProperty(SIFEN_PASSWORD_CERTIFICADO_CLIENTE_KEY));

        if (propiedades.containsKey(SIFEN_CSC_KEY)) {
            sifenConfig.setCSC(propiedades.getProperty(SIFEN_CSC_KEY));
        }

        if (propiedades.containsKey(SIFEN_ID_CSC_KEY)) {
            sifenConfig.setIdCSC(propiedades.getProperty(SIFEN_ID_CSC_KEY));
        }

        try {
            sifenConfig.habilitarNotaTecnica13 = Boolean.parseBoolean(propiedades.getProperty(SIFEN_HABILITAR_NOTA_TECNICA_13_KEY));
        } catch (IllegalArgumentException e) {
            throw SifenExceptionUtil.invalidConfiguration("El valor de la propiedad " + SIFEN_HABILITAR_NOTA_TECNICA_13_KEY + " no es válido.", e);
        }

        return sifenConfig;
    }

    /**
     * Carga la configuración de Sifen a ser utilizada desde un archivo de propiedades.
     *
     * @param archivo File referenciando al archivo de propiedades a ser utilizado como configuración.
     * @return La clase de configuración de Sifen con los datos obtenidos del archivo de propiedades.
     * @throws SifenException Si el archivo utilizado no existe, no pudo ser abierto o es un directorio.
     *                        Si los valores proveídos son incorrectos.
     */
    public static SifenConfig cargarConfiguracion(File archivo) throws SifenException {
        Properties props;
        try {
            props = new Properties();
            props.load(new FileReader(archivo));
        } catch (IOException e) {
            throw SifenExceptionUtil.invalidConfiguration("El archivo utilizado no existe, no pudo ser abierto o es un directorio.");
        }
        return SifenConfig.cargarConfiguracion(props);
    }

    public static SifenConfig cargarConfiguracion(InputStream inputStream) throws SifenException {
        Properties props;
        try {
            props = new Properties();
            props.load(inputStream);
        } catch (IOException e) {
            throw SifenExceptionUtil.invalidConfiguration("InputStream es inválido");
        }
        return SifenConfig.cargarConfiguracion(props);
    }

    /**
     * Carga la configuración de Sifen a ser utilizada desde la ruta de un archivo de propiedades.
     *
     * @param rutaArchivo Ruta del archivo de propiedades a ser utilizado como configuración.
     * @return La clase de configuración de Sifen con los datos obtenidos del archivo de propiedades.
     * @throws SifenException Si el archivo utilizado no existe, no pudo ser abierto o es un directorio.
     *                        Si los valores proveídos son incorrectos.
     */
    public static SifenConfig cargarConfiguracion(String rutaArchivo) throws SifenException {
        return SifenConfig.cargarConfiguracion(new File(rutaArchivo));
    }

    /**
     * Carga la configuración de Sifen a ser utilizada desde un archivo de propiedades ubicado en la ruta Path.
     *
     * @param ruta Path referenciando al archivo de propiedades a ser utilizado como configuración.
     * @return La clase de configuración de Sifen con los datos obtenidos del archivo de propiedades.
     * @throws SifenException Si el archivo utilizado no existe, no pudo ser abierto o es un directorio.
     *                        Si los valores proveídos son incorrectos.
     */
    public SifenConfig cargarConfiguracion(Path ruta) throws SifenException {
        return SifenConfig.cargarConfiguracion(ruta.toFile());
    }

    @Override
    public String toString() {
        return "SifenConfig{" +
                "ambiente=" + ambiente +
                ", urlBase='" + urlBase + '\'' +
                ", urlBaseLocal='" + urlBaseLocal + '\'' +
                ", urlConsultaQr='" + urlConsultaQr + '\'' +
                ", pathRecibe='" + pathRecibe + '\'' +
                ", pathRecibeLote='" + pathRecibeLote + '\'' +
                ", pathEvento='" + pathEvento + '\'' +
                ", pathConsultaLote='" + pathConsultaLote + '\'' +
                ", pathConsultaRUC='" + pathConsultaRUC + '\'' +
                ", pathConsulta='" + pathConsulta + '\'' +
                ", usarCertificadoCliente=" + usarCertificadoCliente +
                ", tipoCertificadoCliente=" + tipoCertificadoCliente +
                ", certificadoCliente='" + certificadoCliente + '\'' +
                ", contrasenaCertificadoCliente='" + contrasenaCertificadoCliente + '\'' +
                ", idCSC='" + idCSC + '\'' +
                ", CSC='" + CSC + '\'' +
                ", httpConnectTimeout=" + httpConnectTimeout +
                ", httpReadTimeout=" + httpReadTimeout +
                ", userAgent='" + userAgent + '\'' +
                ", habilitarNotaTecnica13=" + habilitarNotaTecnica13 +
                ", URL_BASE_DEV='" + URL_BASE_DEV + '\'' +
                ", URL_BASE_PROD='" + URL_BASE_PROD + '\'' +
                ", URL_CONSULTA_QR_DEV='" + URL_CONSULTA_QR_DEV + '\'' +
                ", URL_CONSULTA_QR_PROD='" + URL_CONSULTA_QR_PROD + '\'' +
                '}';
    }

    // Getters y Setters
    public void setAmbiente(TipoAmbiente ambiente) {
        this.ambiente = ambiente;

        if (this.ambiente == TipoAmbiente.DEV) {
            this.urlBaseLocal = URL_BASE_DEV;
            this.urlConsultaQr = URL_CONSULTA_QR_DEV;
        } else if (this.ambiente == TipoAmbiente.PROD) {
            this.urlBaseLocal = URL_BASE_PROD;
            this.urlConsultaQr = URL_CONSULTA_QR_PROD;
        }
    }

    public TipoAmbiente getAmbiente() {
        return ambiente;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getUrlBaseLocal() {
        return urlBaseLocal;
    }

    public String getUrlConsultaQr() {
        return urlConsultaQr;
    }

    public String getPathRecibe() {
        return pathRecibe;
    }

    public void setPathRecibe(String pathRecibe) {
        this.pathRecibe = pathRecibe;
    }

    public String getPathRecibeLote() {
        return pathRecibeLote;
    }

    public void setPathRecibeLote(String pathRecibeLote) {
        this.pathRecibeLote = pathRecibeLote;
    }

    public String getPathEvento() {
        return pathEvento;
    }

    public void setPathEvento(String pathEvento) {
        this.pathEvento = pathEvento;
    }

    public String getPathConsultaLote() {
        return pathConsultaLote;
    }

    public void setPathConsultaLote(String pathConsultaLote) {
        this.pathConsultaLote = pathConsultaLote;
    }

    public String getPathConsultaRUC() {
        return pathConsultaRUC;
    }

    public void setPathConsultaRUC(String pathConsultaRUC) {
        this.pathConsultaRUC = pathConsultaRUC;
    }

    public String getPathConsulta() {
        return pathConsulta;
    }

    public void setPathConsulta(String pathConsulta) {
        this.pathConsulta = pathConsulta;
    }

    public boolean isUsarCertificadoCliente() {
        return usarCertificadoCliente;
    }

    public void setUsarCertificadoCliente(boolean usarCertificadoCliente) {
        this.usarCertificadoCliente = usarCertificadoCliente;
    }

    public String getCertificadoCliente() {
        return certificadoCliente;
    }

    public void setCertificadoCliente(String certificadoCliente) {
        this.certificadoCliente = certificadoCliente;
    }

    public String getContrasenaCertificadoCliente() {
        return contrasenaCertificadoCliente;
    }

    public void setContrasenaCertificadoCliente(String contrasenaCertificadoCliente) {
        this.contrasenaCertificadoCliente = contrasenaCertificadoCliente;
    }

    public TipoCertificadoCliente getTipoCertificadoCliente() {
        return tipoCertificadoCliente;
    }

    public void setTipoCertificadoCliente(TipoCertificadoCliente tipoCertificadoCliente) {
        this.tipoCertificadoCliente = tipoCertificadoCliente;
    }

    public int getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public int getHttpReadTimeout() {
        return httpReadTimeout;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIdCSC() {
        return idCSC;
    }

    public void setIdCSC(String idCSC) {
        this.idCSC = SifenUtil.leftPad(idCSC, '0', 4);
    }

    public String getCSC() {
        return CSC;
    }

    public void setCSC(String CSC) {
        this.CSC = CSC;
    }

    public boolean isHabilitarNotaTecnica13() {
        return habilitarNotaTecnica13;
    }

    public void setHabilitarNotaTecnica13(boolean habilitarNotaTecnica13) {
        this.habilitarNotaTecnica13 = habilitarNotaTecnica13;
    }
}
