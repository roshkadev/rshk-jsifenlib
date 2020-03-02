package com.roshka.sifen.config;

public class ClientCertConfig {

    public enum ClientCertFileType
    {
        PFX,
        PEM,
        DER
    }

    private String clientCertificateFile;
    private String clientCertificatePassword;
    private ClientCertFileType clientCertFileType;

    public String getClientCertificateFile() {
        return clientCertificateFile;
    }

    public void setClientCertificateFile(String clientCertificateFile) {
        this.clientCertificateFile = clientCertificateFile;
    }

    public ClientCertFileType getClientCertFileType() {
        return clientCertFileType;
    }

    public void setClientCertFileType(ClientCertFileType clientCertFileType) {
        this.clientCertFileType = clientCertFileType;
    }

    public String getClientCertificatePassword() {
        return clientCertificatePassword;
    }

    public void setClientCertificatePassword(String clientCertificatePassword) {
        this.clientCertificatePassword = clientCertificatePassword;
    }
}
