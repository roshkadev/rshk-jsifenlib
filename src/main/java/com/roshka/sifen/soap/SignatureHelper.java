package com.roshka.sifen.soap;

import com.roshka.sifen.config.SifenConfig;
import com.roshka.sifen.exceptions.SifenException;
import com.roshka.sifen.model.Constants;
import com.roshka.sifen.model.envi.RSignedEnviBase;
import com.roshka.sifen.ssl.SSLContextHelper;
import com.roshka.sifen.util.SifenExceptionUtil;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignatureHelper {
    
    private static final XMLSignatureFactory _xmlSignatureFactory = XMLSignatureFactory.getInstance();
    private static final List<Transform> transforms;
    private static boolean readyToSign = false;

    static {
        transforms = new ArrayList<>();
        try {
            transforms.add(_xmlSignatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null));
            transforms.add(_xmlSignatureFactory.newTransform(CanonicalizationMethod.EXCLUSIVE, (TransformParameterSpec) null));
            readyToSign = true;
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No se puede inicializar contexto para las firmas: " + e.getMessage());
            e.printStackTrace(System.err);
        } catch (InvalidAlgorithmParameterException e) {
            System.err.println("No se puede inicializar contexto para las firmas: " + e.getMessage());
            e.printStackTrace(System.err);
        }

    }

    private static void doSignDocument(SifenConfig sifenConfig, RSignedEnviBase enviBase) throws SifenException {
        try {

            // create reference
            Reference ref = _xmlSignatureFactory.newReference(
                    "#" + enviBase.getSignedNodeId(),
                    _xmlSignatureFactory.newDigestMethod(DigestMethod.SHA256, null),
                    transforms,
                    null,
                    null
            );

            // SignedInfo
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

            DOMSignContext signatureContext = new DOMSignContext(privateKey, enviBase.getSignatureParentNode(), enviBase.getSignatureNextSiblingNode());
            signature.sign(signatureContext);

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | XMLSignatureException | MarshalException | KeyStoreException | UnrecoverableKeyException e) {
            throw SifenExceptionUtil.requestSigningError("Ocurrió un error al firmar la petición SOAP utilizando el certificado activo", e);
        }

    }


    public static void signDocument(SifenConfig sifenConfig, RSignedEnviBase enviBase) throws SifenException {
        if (!readyToSign) {
            throw SifenExceptionUtil.requestSigningError("El contexto para firmar es inválido");
        }
        doSignDocument(sifenConfig, enviBase);
    }

}
