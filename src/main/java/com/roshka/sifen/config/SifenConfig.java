package com.roshka.sifen.config;

public class SifenConfig {

    private String urlBase;
    private boolean useClientCertificate;
    private ClientCertConfig clientCertConfig;

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
}
