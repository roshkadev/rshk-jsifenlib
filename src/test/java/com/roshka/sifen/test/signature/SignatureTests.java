package com.roshka.sifen.test.signature;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SignatureTests {

    @Test
    @Ignore
    public void testValidSignature() throws MarshalException, XMLSignatureException, ParserConfigurationException, IOException, SAXException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException, CertificateException, KeyException, TransformerException {

        // parse xml

        System.out.println("PARSING XML!");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File("tmp/ex00-nosignature.xml");
        Document doc = builder.parse(file);

        Element parent = (Element) doc.getDocumentElement().getElementsByTagName("rDE").item(0);
        Element signedElement = (Element) parent.getElementsByTagName("DE").item(0);
        Element nextSibling = (Element) parent.getElementsByTagName("gCamFuFD").item(0);

        // loading keys

        file = new File("tmp/taxare.pem");

        String key = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());

        String privateKeyPEM = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "");

        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        final PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        FileInputStream is = new FileInputStream ("tmp/taxare.x509");
        X509Certificate keyInfoValue = (X509Certificate) fact.generateCertificate(is);

        XMLSignatureFactory xmlSignatureFactory = XMLSignatureFactory.getInstance();
        DOMSignContext domSignContext = new DOMSignContext(privateKey, parent, nextSibling);
        String requestId = signedElement.getAttribute("Id");
        signedElement.setIdAttribute("Id", true);
        List<Transform> transforms = new LinkedList<>();
        transforms.add(xmlSignatureFactory.newTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature", (TransformParameterSpec)null));
        transforms.add(xmlSignatureFactory.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", (C14NMethodParameterSpec)null));
        Reference reference = xmlSignatureFactory.newReference("#" + requestId, xmlSignatureFactory.newDigestMethod("http://www.w3.org/2001/04/xmlenc#sha256", (DigestMethodParameterSpec)null), transforms, (String)null, (String)null);


        CanonicalizationMethod canonicalizationMethod = xmlSignatureFactory.newCanonicalizationMethod("http://www.w3.org/2001/10/xml-exc-c14n#", (C14NMethodParameterSpec)null);

        SignatureMethod signatureMethod = xmlSignatureFactory.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", (SignatureMethodParameterSpec)null);
        SignedInfo signedInfo = xmlSignatureFactory.newSignedInfo(canonicalizationMethod, signatureMethod, Collections.singletonList(reference));
        KeyInfoFactory keyInfoFactory = xmlSignatureFactory.getKeyInfoFactory();
        KeyInfo keyInfo = null;
        if (keyInfoValue instanceof PublicKey) {
            keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(keyInfoFactory.newKeyValue((PublicKey)keyInfoValue)));
        } else {
            if (!(keyInfoValue instanceof X509Certificate)) {
                throw new IllegalArgumentException("Unsupported keyinfo type [" + keyInfoValue.getClass() + "]");
            }
            keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(keyInfoFactory.newX509Data(Collections.singletonList(keyInfoValue))));
        }

        XMLSignature xmlSignature = xmlSignatureFactory.newXMLSignature(signedInfo, keyInfo);
        xmlSignature.sign(domSignContext);

        System.out.println(signedInfo.toString());

        // output the resulting document
        OutputStream os = System.out;

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.transform(new DOMSource(doc), new StreamResult(os));
    }
}