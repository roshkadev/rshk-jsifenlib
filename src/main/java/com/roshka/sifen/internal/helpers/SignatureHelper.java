package com.roshka.sifen.internal.helpers;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.beans.ValidezFirmaDigital;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.Constants;
import com.roshka.sifen.internal.util.SifenExceptionUtil;
import com.roshka.sifen.internal.util.SifenUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.security.auth.x500.X500Principal;
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
import jakarta.xml.soap.SOAPElement;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static ValidezFirmaDigital validateSignature(String xml, String type) {
        File xmlFile;
        if (type.equals("XML")) {
            try {
                // Create file from xml string
                xmlFile = File.createTempFile(String.valueOf(UUID.randomUUID()), ".xml");

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        Files.newOutputStream(xmlFile.toPath()), StandardCharsets.UTF_8
                ));
                writer.write(xml);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return ValidezFirmaDigital.create(false, "Ocurrió un error al crear el archivo XML.");
            }
        } else {
            xmlFile = new File(xml);
        }

        // Validate signature
        return validateSignature(xmlFile);
    }

    public static ValidezFirmaDigital validateSignature(File xml) {
        try {
            // Parse the document to be validated
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            Document document;
            try {
                document = builderFactory.newDocumentBuilder().parse(xml);
            } catch (SAXException e) {
                e.printStackTrace();
                return ValidezFirmaDigital.create(false, "Ocurrió un error al parsear el " +
                        "archivo XML. Revise que el archivo sea un XML válido, y que tenga un solo nodo raíz.");
            }

            // Find Signature
            NodeList signatureNodes = document.getElementsByTagName("Signature");
            if (signatureNodes.getLength() == 0) {
                return ValidezFirmaDigital.create(false, "No se encontró la firma digital en " +
                        "el Documento Electrónico.");
            }

            // Get signed element from document
            DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(), signatureNodes.item(0));
            NodeList DENodes = document.getElementsByTagName("DE");
            if (DENodes.getLength() > 0) {
                valContext.setIdAttributeNS((Element) DENodes.item(0), null, "Id");
            } else {
                return ValidezFirmaDigital.create(false, "No se encontró el nodo 'DE' en el " +
                        "Documento Electrónico.");
            }
            XMLSignature signature = _xmlSignatureFactory.unmarshalXMLSignature(valContext);

            // Get subjects from certificate for further validation
            List<ValidezFirmaDigital.SujetoCertificado> certificateSubjects = getCertificateSubjects(signature.getKeyInfo());

            // Validate the Signature
            boolean isValid = signature.validate(valContext);
            if (!isValid) {
                return ValidezFirmaDigital.create(false, "La firma digital es inválida.",
                        certificateSubjects);
            }

            return checkDocumentIssuer(document, certificateSubjects);
        } catch (MarshalException | XMLSignatureException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            return ValidezFirmaDigital.create(false, "Ocurrió un error al validar la firma " +
                    "digital del Documento Electrónico.");
        }
    }

    private static ValidezFirmaDigital checkDocumentIssuer(Document document, List<ValidezFirmaDigital.SujetoCertificado> certificateSubjects) {
        // Get Issuer RUC from Electronic Document
        NodeList dRucEmNodes = document.getElementsByTagName("dRucEm");
        if (dRucEmNodes.getLength() == 0) {
            return ValidezFirmaDigital.create(false, "No se encontró el nodo 'dRucEm' en " +
                    "el Documento Electrónico.", certificateSubjects);
        }

        NodeList dDVEmiNodes = document.getElementsByTagName("dDVEmi");
        if (dRucEmNodes.getLength() == 0) {
            return ValidezFirmaDigital.create(false, "No se encontró el nodo 'dDVEmi' en " +
                    "el Documento Electrónico.", certificateSubjects);
        }

        String issuerRuc = dRucEmNodes.item(0).getTextContent();
        String issuerDv = dDVEmiNodes.item(0).getTextContent();

        for (ValidezFirmaDigital.SujetoCertificado subject : certificateSubjects) {
            if (subject.getNumeroDocumento().equals(issuerRuc + "-" + issuerDv)) {
                return ValidezFirmaDigital.create(true, certificateSubjects);
            }
        }

        return ValidezFirmaDigital.create(false, "El RUC emisor del Documento Electrónico " +
                "no coincide con el encontrado en la firma digital.", certificateSubjects);
    }

    private static List<ValidezFirmaDigital.SujetoCertificado> getCertificateSubjects(KeyInfo keyInfo) {
        List<ValidezFirmaDigital.SujetoCertificado> certificateSubjects = new ArrayList<>();

        // Get certificate from Electronic Document
        X509Certificate certificate = X509KeySelector.getCertificate(keyInfo);
        if (certificate == null) return certificateSubjects;

        X500Principal subjectX500Principal = certificate.getSubjectX500Principal();

        if (subjectX500Principal == null) return certificateSubjects;

        // Get main subject information from certificate
        try {
            String subject = subjectX500Principal.getName();

            certificateSubjects.add(ValidezFirmaDigital.SujetoCertificado.create(
                    getAttributeFromSubject(subject, "SERIALNUMBER"),
                    SifenUtil.coalesce(getAttributeFromSubject(subject, "CN"), getAttributeFromSubject(subject, "O"))
            ));
        } catch (Exception ignored) {
        }

        // Get alternatives subjects from certificate
        try {

            Collection<List<?>> subjectAlternativeNames = certificate.getSubjectAlternativeNames();


            /*
            TODO: TEST it
            List<GeneralName> names = certificate.getSubjectAlternativeNameExtension().get("subject_name").names();
            for (GeneralName name : names) {
                if (!(name.getName() instanceof X500Name)) continue;

                String subject = name.getName().toString();

                certificateSubjects.add(ValidezFirmaDigital.SujetoCertificado.create(
                        getAttributeFromSubject(subject, "SERIALNUMBER"),
                        SifenUtil.coalesce(getAttributeFromSubject(subject, "CN"), getAttributeFromSubject(subject, "O"))
                ));
            }

             */
        } catch (Exception ignored) {
        }

        return certificateSubjects;
    }

    private static String getAttributeFromSubject(String subject, String attributeName) {
        Pattern pattern = Pattern.compile("(?<=" + attributeName + "=)[\\w\\s-]+");
        Matcher matcher = pattern.matcher(subject);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private static class X509KeySelector extends KeySelector {
        public KeySelectorResult select(KeyInfo keyInfo, KeySelector.Purpose purpose, AlgorithmMethod method,
                                        XMLCryptoContext context) throws KeySelectorException {
            X509Certificate certificate = getCertificate(keyInfo);
            if (certificate != null) {
                final PublicKey key = certificate.getPublicKey();
                if (key.getAlgorithm().equalsIgnoreCase("RSA") && method.getAlgorithm().equalsIgnoreCase(Constants.RSA_SHA256)) {
                    return () -> key;
                }
            }
            throw new KeySelectorException("No key was found");
        }

        public static X509Certificate getCertificate(KeyInfo keyInfo) {
            for (Object value : keyInfo.getContent()) {
                XMLStructure info = (XMLStructure) value;
                if (!(info instanceof X509Data)) continue;

                X509Data x509Data = (X509Data) info;
                for (Object certificate : x509Data.getContent()) {
                    if (!(certificate instanceof X509Certificate)) continue;

                    return ((X509Certificate) certificate);
                }
            }
            return null;
        }
    }
}