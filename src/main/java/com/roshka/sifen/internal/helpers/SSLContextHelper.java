package com.roshka.sifen.internal.helpers;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.util.SifenExceptionUtil;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Helper encargado de manejar la conexión SSL y los certificados.
 */
public class SSLContextHelper {
    private final static Logger logger = getLogger(SSLContextHelper.class.toString());
    private static SSLContext _sslContext;

    public static SSLContext getContextFromConfig(SifenConfig sifenConfig) throws SifenException {
        if (_sslContext != null)
            return _sslContext;

        logger.info("Contexto SSL no cargado aún. Empezando carga...");
        if (!isCertificateConfigurationValid(sifenConfig)) {
            try {
                logger.info("Se utilizará un contexto SSL por defecto.");
                return SSLContext.getDefault();
            } catch (NoSuchAlgorithmException e) {
                throw SifenExceptionUtil.invalidSSLContext("No se puede retornar el contexto SSL por defecto", e);
            }
        }

        KeyStore keyStore = getCertificateKeyStore(sifenConfig.getCertificadoCliente(), sifenConfig.getContrasenaCertificadoCliente());
        KeyManagerFactory keyManagerFactory;
        try {
            keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            throw SifenExceptionUtil.invalidSSLContext("No se puede obtener una instancia de administrador de claves de algoritmo: " + KeyManagerFactory.getDefaultAlgorithm(), e);
        }
        try {
            keyManagerFactory.init(keyStore, sifenConfig.getContrasenaCertificadoCliente().toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw SifenExceptionUtil.invalidSSLContext("No se puede inicializar el administrador de claves: " + e.getLocalizedMessage(), e);
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
            throw SifenExceptionUtil.invalidSSLContext("No se inicializar el contexto SSL: " + e.getLocalizedMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw SifenExceptionUtil.invalidSSLContext("No se puede obtener una instancia de contexto SSL TLS: " + e.getLocalizedMessage(), e);
        }
    }

    public static KeyStore getCertificateKeyStore(SifenConfig sifenConfig) throws SifenException {
        if (!isCertificateConfigurationValid(sifenConfig)) {
            throw SifenExceptionUtil.invalidConfiguration("Configuración del certificado no establecida. No se puede obtener la clave para la firma.");
        }

        return getCertificateKeyStore(sifenConfig.getCertificadoCliente(), sifenConfig.getContrasenaCertificadoCliente());
    }

    private static KeyStore getCertificateKeyStore(String certificate, String password) throws SifenException {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
        } catch (KeyStoreException e) {
            throw SifenExceptionUtil.invalidSSLContext("No se obtener una instancia de almacén de claves PKCS12: " + e.getLocalizedMessage(), e);
        }

        try {
            InputStream certInputStream;
            try {
                certInputStream = new FileInputStream(certificate);
            } catch (FileNotFoundException e) {
                logger.info("El certificado no es un archivo. Intentando decodificar...");
                certInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(certificate));
            }

            keyStore.load(certInputStream, password.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException | IllegalArgumentException e) {
            throw SifenExceptionUtil.invalidSSLContext("No se puede cargar el certificado de cliente: " + e.getLocalizedMessage(), e);
        }

        return keyStore;
    }

    private static boolean isCertificateConfigurationValid(SifenConfig sifenConfig) {
        if (sifenConfig.isUsarCertificadoCliente()) {
            if (sifenConfig.getTipoCertificadoCliente() == SifenConfig.TipoCertificadoCliente.PFX) {
                if (sifenConfig.getCertificadoCliente() == null || sifenConfig.getContrasenaCertificadoCliente() == null) {
                    logger.warning("Configuración del certificado no establecida.");
                    return false;
                }
                return true;
            } else {
                logger.warning("Tipo de certificado aún no soportado");
                return false;
            }
        }
        return false;
    }
}
