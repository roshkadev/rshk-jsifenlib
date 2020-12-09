package com.roshka.sifen.model.envi;

import javax.xml.soap.Node;

public abstract class RSignedEnviBase extends REnviBase {

    private boolean readyToSign;

    public abstract String getSignedNodeId();
    public abstract Node getSignatureParentNode();
    public abstract Node getSignatureNextSiblingNode();

    public boolean isReadyToSign() {
        return readyToSign;
    }

    public void setReadyToSign(boolean readyToSign) {
        this.readyToSign = readyToSign;
    }
}
