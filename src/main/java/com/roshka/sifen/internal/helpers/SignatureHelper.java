package com.roshka.sifen.internal.helpers;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.internal.Constants;
import com.roshka.sifen.internal.util.SifenExceptionUtil;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.soap.SOAPElement;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | XMLSignatureException | MarshalException | KeyStoreException | UnrecoverableKeyException e) {
            throw SifenExceptionUtil.requestSigningError("Ocurrió un error al firmar la petición SOAP utilizando el certificado activo", e);
        }
    }
}