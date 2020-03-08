package com.roshka.sifen.config;

import com.roshka.sifen.exceptions.SifenException;

import java.util.Properties;
import java.util.logging.Logger;

public class ClientCertConfig {

    private final static Logger logger = Logger.getLogger(ClientCertConfig.class.toString());

    public enum ClientCertFileType
    {
        PFX,
        PEM,
        DER
    }

    public static final String CFG_CLIENT_CERT_FILE_TYPE_KEY = "sifen.cert.file_type";
    public static final String CFG_CLIENT_CERT_FILE_NAME_KEY = "sifen.cert.file_name";
    public static final String CFG_CLIENT_CERT_PASSWORD_KEY = "sifen.cert.password";

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

    public static ClientCertConfig loadFromProperties(Properties properties)
        throws SifenException
    {
        ClientCertConfig clientCertConfig = new ClientCertConfig();

        ClientCertFileType clientCertFileType = ClientCertFileType.PFX;
        try {
            clientCertFileType =
                ClientCertConfig.ClientCertFileType.valueOf(
                    properties.getProperty(CFG_CLIENT_CERT_FILE_TYPE_KEY, "PFX")
                );
        } catch (IllegalArgumentException e) {
            logger.severe("Invalid client certificate type: " + properties.getProperty(CFG_CLIENT_CERT_FILE_TYPE_KEY));
        }
        clientCertConfig.setClientCertFileType(clientCertFileType);
        clientCertConfig.setClientCertificateFile(properties.getProperty(CFG_CLIENT_CERT_FILE_NAME_KEY));
        clientCertConfig.setClientCertificatePassword(properties.getProperty(CFG_CLIENT_CERT_PASSWORD_KEY));

        return clientCertConfig;
    }
}
