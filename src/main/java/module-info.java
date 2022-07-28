module RshkJsifenlib {
    requires java.base;
    requires java.logging;
    requires jakarta.xml.ws;
    requires jakarta.xml.soap;
    requires java.xml.crypto;
    requires java.xml.soap;
    requires java.xml;
    requires jakarta.activation;

    exports com.roshka.sifen;
    exports com.roshka.sifen.core;
}
