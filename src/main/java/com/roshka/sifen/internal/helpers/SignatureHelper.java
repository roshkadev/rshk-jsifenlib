package com.roshka.sifen.internal.helpers;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.Constants;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPElement;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Helper encargado de la firma digital de los documentos XML.
 */
public class SignatureHelper {
    private static final XMLSignatureFactory _xmlSignatureFactory = XMLSignatureFactory.getInstance();
    private static final List<Transform> transforms;

    static {
        transforms = new ArrayList<>();
        try {
            transforms.add(_xmlSignatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
            transforms.add(_xmlSignatureFactory.newTransform(CanonicalizationMethod.EXCLUSIVE, (TransformParameterSpec) null));
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            System.err.println("No se puede inicializar contexto para las firmas: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public static SignedInfo signDocument(SifenConfig sifenConfig, SOAPElement signatureParentNode, String signedNodeId) throws SifenException {
        try {
            Reference ref = _xmlSignatureFactory.newReference("#" + signedNodeId,
                    _xmlSignatureFactory.newDigestMethod(DigestMethod.SHA256, null),
                    transforms, null, null);

            SignedInfo signedInfo = _xmlSignatureFactory.newSignedInfo(
                    _xmlSignatureFactory.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
                    _xmlSignatureFactory.newSignatureMethod(Constants.RSA_SHA256, null),
                    Collections.singletonList(ref)
            );

            KeyStore keyStore = SSLContextHelper.getCertificateKeyStore(sifenConfig);
            String alias = keyStore.aliases().nextElement();
            X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);

            KeyInfoFactory keyInfoFactory = _xmlSignatureFactory.getKeyInfoFactory();
            X509Data x509Data = keyInfoFactory.newX509Data(Collections.singletonList(certificate));
            KeyInfo keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));

            XMLSignature signature = _xmlSignatureFactory.newXMLSignature(signedInfo, keyInfo);
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, sifenConfig.getContrasenaCertificadoCliente().toCharArray());

            DOMSignContext signatureContext = new DOMSignContext(privateKey, signatureParentNode);
            signature.sign(signatureContext);

            return signedInfo;
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | XMLSignatureException |
                 MarshalException | KeyStoreException | UnrecoverableKeyException e) {
            throw SifenExceptionUtil.requestSigningError("Ocurrió un error al firmar la petición SOAP utilizando el certificado activo", e);
        }
    }

    public static boolean validateSignature(File xml) throws SifenException {
        try {
            // Parse the document to be validated
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            Document document;
            try {
                document = builderFactory.newDocumentBuilder().parse(xml);
            } catch (SAXException e) {
                throw SifenExceptionUtil.invalidSignatureError("Ocurrió un error al formatear el XML. Revisar que el " +
                        "archivo sea un XML válido, y que tenga un solo nodo raíz.");
            }

            // Find Signature
            NodeList signatureNodes = document.getElementsByTagName("Signature");
            if (signatureNodes.getLength() == 0) {
                throw SifenExceptionUtil.invalidSignatureError("No se encontró la firma digital en el Documento Electrónico");
            }

            // Validate the Signature
            DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(), signatureNodes.item(0));
            NodeList DENodes = document.getElementsByTagName("DE");
            if (DENodes.getLength() > 0) {
                valContext.setIdAttributeNS((Element) DENodes.item(0), null, "Id");
            } else {
                throw SifenExceptionUtil.invalidSignatureError("No se encontró el nodo 'DE' en el Documento Electrónico");
            }

            XMLSignature signature = _xmlSignatureFactory.unmarshalXMLSignature(valContext);

            return signature.validate(valContext);
        } catch (MarshalException | XMLSignatureException | ParserConfigurationException | IOException e) {
            throw SifenExceptionUtil.invalidSignatureError("Ocurrió un error al validar la firma digital del Documento Electrónico");
        }
    }

    public static boolean validateSignature(String xml) throws SifenException {
        try {
            // Create file from string
            File xmlFile = File.createTempFile(String.valueOf(UUID.randomUUID()), ".xml");

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    Files.newOutputStream(xmlFile.toPath()), StandardCharsets.UTF_8
            ));
            writer.write(xml);
            writer.close();

            // Validate signature
            return validateSignature(xmlFile);
        } catch (IOException e) {
            throw SifenExceptionUtil.invalidSignatureError("Ocurrió un error al validar la firma digital del Documento Electrónico");
        }
    }

    private static class X509KeySelector extends KeySelector {
        public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod method,
                                        XMLCryptoContext context) throws KeySelectorException {
            for (Object value : keyInfo.getContent()) {
                XMLStructure info = (XMLStructure) value;
                if (!(info instanceof X509Data)) continue;

                X509Data x509Data = (X509Data) info;
                for (Object o : x509Data.getContent()) {
                    if (!(o instanceof X509Certificate)) continue;

                    final PublicKey key = ((X509Certificate) o).getPublicKey();
                    if (key.getAlgorithm().equalsIgnoreCase("RSA") && method.getAlgorithm().equalsIgnoreCase(Constants.RSA_SHA256)) {
                        return () -> key;
                    }
                }
            }
            throw new KeySelectorException("No key was found");
        }
    }
}