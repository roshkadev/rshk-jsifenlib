module com.roshka.jsifenlib {

    // Para el API simple de JSIFEN-LIB
    requires jdk.httpserver;

    // Para el manejo de SOAP
    requires java.xml;
    requires java.xml.crypto;
    requires jakarta.xml.soap;
    requires jakarta.activation;

    // Logging
    requires java.logging;
}