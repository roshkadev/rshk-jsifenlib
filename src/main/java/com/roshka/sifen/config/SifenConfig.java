package com.roshka.sifen.config;

import static com.roshka.sifen.model.Constants.SDK_CURRENT_VERSION;

public class SifenConfig {
    // Enums
    public enum TipoCertificadoCliente {PFX, PEM, DER}
    public enum TipoAmbiente {DEV, PROD}

    // Atributos
    private TipoAmbiente ambiente;
    private String urlBase;
    private String urlBaseLocal;
    private String urlRecibe;
    private String urlRecibeLote;
    private String urlEvento;
    private String urlConsultaLote;
    private String urlConsultaRUC;
    private String urlConsulta;

    private boolean usarCertificadoCliente;
    private String rutaCertificadoCliente;
    private String contrasenaCertificadoCliente;
    private TipoCertificadoCliente tipoCertificadoCliente;

    private final int httpConnectTimeout;
    private final int httpReadTimeout;
    private final String userAgent;

    // Valores Finales
    private final String urlBaseDesarrollo = "https://sifen-test.set.gov.py";
    private final String urlBaseProduccion = "https://sifen.set.gov.py";

    // Constructores
    public SifenConfig() {
        this.ambiente = TipoAmbiente.DEV;
        this.urlBaseLocal = urlBaseDesarrollo;
        this.urlRecibe = "/de/ws/sync/recibe.wsdl";
        this.urlRecibeLote = "/de/ws/async/recibe-lote.wsdl";
        this.urlEvento = "/de/ws/eventos/evento.wsdl";
        this.urlConsultaLote = "/de/ws/consultas/consulta-lote.wsdl";
        this.urlConsultaRUC = "/de/ws/consultas/consulta-ruc.wsdl";
        this.urlConsulta = "/de/ws/consultas/consulta.wsdl";
        this.usarCertificadoCliente = true;

        this.httpConnectTimeout = 15 * 1000; // 15 Segundos
        this.httpReadTimeout = 45 * 1000; // 45 Segundos
        this.userAgent = "rshk-jsifenlib" + "/" + SDK_CURRENT_VERSION + " (LVEA)";
    }

    public SifenConfig(String rutaCertificadoCliente, String contrasenaCertificadoCliente,
                       TipoCertificadoCliente tipoCertificadoCliente) {
        this();
        this.usarCertificadoCliente = true;
        this.rutaCertificadoCliente = rutaCertificadoCliente;
        this.contrasenaCertificadoCliente = contrasenaCertificadoCliente;
        this.tipoCertificadoCliente = tipoCertificadoCliente;
    }

    public SifenConfig(TipoAmbiente tipoAmbiente, String rutaCertificadoCliente, String contrasenaCertificadoCliente,
                       TipoCertificadoCliente tipoCertificadoCliente) {
        this(rutaCertificadoCliente, contrasenaCertificadoCliente, tipoCertificadoCliente);
        this.ambiente = tipoAmbiente;
    }

    // Getters y Setters
    public void setAmbiente(TipoAmbiente ambiente) {
        this.ambiente = ambiente;

        if (this.ambiente == TipoAmbiente.DEV)
            this.urlBaseLocal = urlBaseDesarrollo;
        else if (this.ambiente == TipoAmbiente.PROD)
            this.urlBaseLocal = urlBaseProduccion;
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

    public boolean isUsarCertificadoCliente() {
        return usarCertificadoCliente;
    }

    public void setUsarCertificadoCliente(boolean usarCertificadoCliente) {
        this.usarCertificadoCliente = usarCertificadoCliente;
    }

    public String getRutaCertificadoCliente() {
        return rutaCertificadoCliente;
    }

    public void setRutaCertificadoCliente(String rutaCertificadoCliente) {
        this.rutaCertificadoCliente = rutaCertificadoCliente;
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
}
