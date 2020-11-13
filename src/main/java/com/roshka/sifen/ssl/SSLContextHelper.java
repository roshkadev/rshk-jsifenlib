package com.roshka.sifen.ssl;

import com.roshka.sifen.config.ClientCertConfig;
import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.exceptions.SifenExceptionUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class SSLContextHelper {
    private final static Logger logger = getLogger(SSLContextHelper.class.toString());
    private static SSLContext _sslContext;

    public static SSLContext getContextFromConfig(SifenConfig sifenConfig) throws SifenException {
        if (_sslContext != null)
            return _sslContext;

        logger.info("Aun no hay contexto SSL cargado. Cargando uno...");

        ClientCertConfig clientCertConfig = sifenConfig.getClientCertConfig();
        if (clientCertConfig == null)
        {
            logger.warning("La configuración del certificado de cliente es nula (no está establecida). Utilizando un contexto SSL por defecto");
            try {
                return SSLContext.getDefault();
            } catch (NoSuchAlgorithmException e) {
                throw SifenExceptionUtil.contextoSSLInvalido("No se puede retornar el contexto SSL por defecto", e);
            }
        }
        logger.info("Archivo de certificado cliente: " + sifenConfig.getClientCertConfig());

        KeyStore keyStore = getCertificateKeyStore(clientCertConfig.getClientCertificateFile(), clientCertConfig.getClientCertificatePassword());
        KeyManagerFactory keyManagerFactory;
        try {
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            throw SifenExceptionUtil.contextoSSLInvalido("No se puede obtener una instancia de administrador de claves de algoritmo: " + KeyManagerFactory.getDefaultAlgorithm(), e);
        }
        try {
            keyManagerFactory.init(keyStore, clientCertConfig.getClientCertificatePassword().toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw SifenExceptionUtil.contextoSSLInvalido("No se puede inicializar el administrador de claves: " + e.getLocalizedMessage(), e);
        }

        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(
                    keyManagerFactory.getKeyManagers(),
                    null,
                    new SecureRandom()
            );
            return _sslContext = sslContext;
        } catch (KeyManagementException e) {
            throw SifenExceptionUtil.contextoSSLInvalido("No se inicializar el contexto SSL: " + e.getLocalizedMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw SifenExceptionUtil.contextoSSLInvalido("No se puede obtener una instancia de contexto SSL TLS: " + e.getLocalizedMessage(), e);
        }
    }

    public static KeyPair getCertificateKeyPair(SifenConfig sifenConfig) throws SifenException {
        ClientCertConfig clientCertConfig = sifenConfig.getClientCertConfig();
        if (clientCertConfig == null) {
            throw SifenExceptionUtil.configuracionInvalida("La configuración del certificado de cliente es nula. No se puede obtener la clave.");
        }

        try {
            KeyStore keyStore = getCertificateKeyStore(
                    clientCertConfig.getClientCertificateFile(),
                    clientCertConfig.getClientCertificatePassword()
            );
            String alias = keyStore.aliases().nextElement();

            PublicKey publicKey = keyStore.getCertificate(alias).getPublicKey();
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, clientCertConfig.getClientCertificatePassword().toCharArray());

            return new KeyPair(publicKey, privateKey);
        } catch (KeyStoreException e) {
            throw SifenExceptionUtil.configuracionInvalida("La configuración del certificado de cliente es nula. No se puede obtener la clave.");
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException e) {
            throw SifenExceptionUtil.contextoSSLInvalido("No se puede obtener una instancia de administrador de claves de algoritmo: " + KeyManagerFactory.getDefaultAlgorithm(), e);
        }
    }

    private static KeyStore getCertificateKeyStore(String certificatePath, String password) throws SifenException {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
        } catch (KeyStoreException e) {
            throw SifenExceptionUtil.contextoSSLInvalido("No se obtener una instancia de almacén de claves PKCS12: " + e.getLocalizedMessage(), e);
        }

        try {
            keyStore.load(new FileInputStream(certificatePath), password.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            throw SifenExceptionUtil.contextoSSLInvalido("No se puede cargar archivo del certificado de cliente: " + e.getLocalizedMessage(), e);
        }

        return keyStore;
    }
}
