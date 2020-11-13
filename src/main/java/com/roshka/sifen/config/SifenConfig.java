package com.roshka.sifen.config;

import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionUtil;
import com.roshka.sifen.util.PropertiesUtil;

import java.util.Properties;
import java.util.logging.Logger;

import static com.roshka.sifen.sdk.Constants.SDK_CURRENT_VERSION;
import static java.util.logging.Logger.getLogger;

public class SifenConfig {
    private final static Logger logger = getLogger(SifenConfig.class.toString());

    public static final String CFG_URL_BASE_KEY = "sifen.url_base";
    public static final String CFG_USE_CLIENT_CERTIFICATE_KEY = "sifen.use_client_certificate";

    public static final String CFG_URL_RECIBE_KEY = "sifen.url.recibe";
    public static final String CFG_URL_RECIBE_LOTE_KEY = "sifen.url.recibe-lote";
    public static final String CFG_URL_EVENTO_KEY = "sifen.url.evento";
    public static final String CFG_URL_CONSULTA_LOTE_KEY = "sifen.url.consulta-lote";
    public static final String CFG_URL_CONSULTA_RUC_KEY = "sifen.url.consulta-ruc";
    public static final String CFG_URL_CONSULTA_KEY = "sifen.url.consulta";

    public static final String URL_RECIBE_DEFAULT_PATH = "/de/ws/sync/recibe.wsdl";
    public static final String URL_RECIBE_LOTE_DEFAULT_PATH = "/de/ws/async/recibe-lote.wsdl";
    public static final String URL_EVENTO_DEFAULT_PATH = "/de/ws/eventos/evento.wsdl";
    public static final String URL_CONSULTA_LOTE_DEFAULT_PATH = "/de/ws/consultas/consulta-lote.wsdl";
    public static final String URL_CONSULTA_RUC_DEFAULT_PATH = "/de/ws/consultas/consulta-ruc.wsdl";
    public static final String URL_CONSULTA_DEFAULT_PATH = "/de/ws/consultas/consulta.wsdl";

    private String urlBase;
    private String urlRecibe;
    private String urlRecibeLote;
    private String urlEvento;
    private String urlConsultaLote;
    private String urlConsultaRUC;
    private String urlConsulta;
    private boolean useClientCertificate;
    private ClientCertConfig clientCertConfig;

    public static final String CFG_HTTP_CONNECT_TIMEOUT_KEY = "sifen.http.connect_timeout";
    public static final String CFG_HTTP_READ_TIMEOUT_KEY = "sifen.http.read_timeout";
    public static final String CFG_HTTP_USER_AGENT_KEY = "sifen.http.user_agent";

    // 15 segundos
    public static final int HTTP_CONNECT_TIMEOUT_DEFAULT = 15 * 1000;
    // 45 segundos
    public static final int HTTP_READ_TIMEOUT_DEFAULT = 45 * 1000;
    public static final String HTTP_USER_AGENT_DEFAULT = "rshk-jsifenlib" + "/" + SDK_CURRENT_VERSION + " (LVEA)";

    private int httpConnectTimeout;
    private int httpReadTimeout;
    private String userAgent;


    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public boolean isUseClientCertificate() {
        return useClientCertificate;
    }

    public void setUseClientCertificate(boolean useClientCertificate) {
        this.useClientCertificate = useClientCertificate;
    }

    public ClientCertConfig getClientCertConfig() {
        return clientCertConfig;
    }

    public void setClientCertConfig(ClientCertConfig clientCertConfig) {
        this.clientCertConfig = clientCertConfig;
    }

    public String getUrlRecibe() {
        return urlRecibe;
    }

    public void setUrlRecibe(String urlRecibe) {
        this.urlRecibe = urlRecibe;
    }

    public String getUrlRecibeLote() {
        return urlRecibeLote;
    }

    public void setUrlRecibeLote(String urlRecibeLote) {
        this.urlRecibeLote = urlRecibeLote;
    }

    public String getUrlEvento() {
        return urlEvento;
    }

    public void setUrlEvento(String urlEvento) {
        this.urlEvento = urlEvento;
    }

    public String getUrlConsultaLote() {
        return urlConsultaLote;
    }

    public void setUrlConsultaLote(String urlConsultaLote) {
        this.urlConsultaLote = urlConsultaLote;
    }

    public String getUrlConsultaRUC() {
        return urlConsultaRUC;
    }

    public void setUrlConsultaRUC(String urlConsultaRUC) {
        this.urlConsultaRUC = urlConsultaRUC;
    }

    public String getUrlConsulta() {
        return urlConsulta;
    }

    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }

    private String urlOrDefault(Properties properties, String key, String defaultPath) {
        // Si el KEY está establecido, utilizarlo como valor de URL, de lo contrario, usar el URL
        // con los valores por defecto
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        } else {
            return getUrlBase() + defaultPath;
        }
    }

    public static SifenConfig loadFromProperties(Properties properties)
            throws SifenException
    {
        SifenConfig sifenConfig = new SifenConfig();
        sifenConfig.setUrlBase(properties.getProperty(CFG_URL_BASE_KEY));

        if (sifenConfig.getUrlBase() == null) {
            String msg = "Se necesita un valor para URL BASE en el archivo. Clave: " + CFG_URL_BASE_KEY;
            logger.severe(msg);
            throw SifenExceptionUtil.configuracionInvalida(msg);
        }

        boolean useClientCertificate = true;
        if (properties.containsKey(CFG_USE_CLIENT_CERTIFICATE_KEY)) {
            useClientCertificate = Boolean.parseBoolean(properties.getProperty(CFG_USE_CLIENT_CERTIFICATE_KEY));
        }
        sifenConfig.setUseClientCertificate(useClientCertificate);

        // URL_CONSULTA
        sifenConfig.setUrlConsulta(
            sifenConfig.urlOrDefault(properties, CFG_URL_CONSULTA_KEY, URL_CONSULTA_DEFAULT_PATH)
        );
        // URL_CONSULTA_LOTE
        sifenConfig.setUrlConsultaLote(
                sifenConfig.urlOrDefault(properties, CFG_URL_CONSULTA_LOTE_KEY, URL_CONSULTA_LOTE_DEFAULT_PATH)
        );
        // URL_CONSULTA_RUC
        sifenConfig.setUrlConsultaRUC(
                sifenConfig.urlOrDefault(properties, CFG_URL_CONSULTA_RUC_KEY, URL_CONSULTA_RUC_DEFAULT_PATH)
        );
        // URL_RECIBE
        sifenConfig.setUrlRecibe(
                sifenConfig.urlOrDefault(properties, CFG_URL_RECIBE_KEY, URL_RECIBE_DEFAULT_PATH)
        );
        // URL_RECIBE_LOTE
        sifenConfig.setUrlRecibeLote(
                sifenConfig.urlOrDefault(properties, CFG_URL_RECIBE_LOTE_KEY, URL_RECIBE_LOTE_DEFAULT_PATH)
        );
        // URL_EVENTO
        sifenConfig.setUrlEvento(
                sifenConfig.urlOrDefault(properties, CFG_URL_EVENTO_KEY, URL_EVENTO_DEFAULT_PATH)
        );

        // Propiedades de las conexiones HTTP
        sifenConfig.setHttpConnectTimeout(PropertiesUtil.getIntOrDefault(properties, CFG_HTTP_CONNECT_TIMEOUT_KEY, HTTP_CONNECT_TIMEOUT_DEFAULT));
        sifenConfig.setHttpReadTimeout(PropertiesUtil.getIntOrDefault(properties, CFG_HTTP_READ_TIMEOUT_KEY, HTTP_READ_TIMEOUT_DEFAULT));
        sifenConfig.setUserAgent(properties.getProperty(CFG_HTTP_USER_AGENT_KEY, HTTP_USER_AGENT_DEFAULT));

        sifenConfig.setClientCertConfig(ClientCertConfig.loadFromProperties(properties));
        return sifenConfig;
    }

    public int getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public void setHttpConnectTimeout(int httpConnectTimeout) {
        this.httpConnectTimeout = httpConnectTimeout;
    }

    public int getHttpReadTimeout() {
        return httpReadTimeout;
    }

    public void setHttpReadTimeout(int httpReadTimeout) {
        this.httpReadTimeout = httpReadTimeout;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
