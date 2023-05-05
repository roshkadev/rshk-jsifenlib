package com.roshka.sifen.core.types;

import java.util.Arrays;

/**
 * Este enumerado contiene los países oficiales según la SET,
 * además de sus descripiones
 */
public enum PaisType {

        /**
     * Argelia
     */
    DZA("Argelia"),
    /**
     * Egipto
     */
    EGY("Egipto"),
    /**
     * Libia
     */
    LBY("Libia"),
    /**
     * Marruecos
     */
    MAR("Marruecos"),
    /**
     * Sudán
     */
    SDN("Sudán"),
    /**
     * Túnez
     */
    TUN("Túnez"),
    /**
     * Sáhara Occidental
     */
    ESH("Sáhara Occidental"),
    /**
     * Territorio Británico del Océano Índico
     */
    IOT("Territorio Británico del Océano Índico"),
    /**
     * Burundi
     */
    BDI("Burundi"),
    /**
     * Comoras
     */
    COM("Comoras"),
    /**
     * Djibouti
     */
    DJI("Djibouti"),
    /**
     * Eritrea
     */
    ERI("Eritrea"),
    /**
     * Etiopía
     */
    ETH("Etiopía"),
    /**
     * Territorio de las Tierras Australes Francesas
     */
    ATF("Territorio de las Tierras Australes Francesas"),
    /**
     * Kenya
     */
    KEN("Kenya"),
    /**
     * Madagascar
     */
    MDG("Madagascar"),
    /**
     * Malawi
     */
    MWI("Malawi"),
    /**
     * Mauricio
     */
    MUS("Mauricio"),
    /**
     * Mayotte
     */
    MYT("Mayotte"),
    /**
     * Mozambique
     */
    MOZ("Mozambique"),
    /**
     * Reunión
     */
    REU("Reunión"),
    /**
     * Rwanda
     */
    RWA("Rwanda"),
    /**
     * Seychelles
     */
    SYC("Seychelles"),
    /**
     * Somalia
     */
    SOM("Somalia"),
    /**
     * Sudán del Sur
     */
    SSD("Sudán del Sur"),
    /**
     * Uganda
     */
    UGA("Uganda"),
    /**
     * República Unida de Tanzanía
     */
    TZA("República Unida de Tanzanía"),
    /**
     * Zambia
     */
    ZMB("Zambia"),
    /**
     * Zimbabwe
     */
    ZWE("Zimbabwe"),
    /**
     * Angola
     */
    AGO("Angola"),
    /**
     * Camerún
     */
    CMR("Camerún"),
    /**
     * República Centroafricana
     */
    CAF("República Centroafricana"),
    /**
     * Chad
     */
    TCD("Chad"),
    /**
     * Congo
     */
    COG("Congo"),
    /**
     * República Democrática del Congo
     */
    COD("República Democrática del Congo"),
    /**
     * Guinea Ecuatorial
     */
    GNQ("Guinea Ecuatorial"),
    /**
     * Gabón
     */
    GAB("Gabón"),
    /**
     * Santo Tomé y Príncipe
     */
    STP("Santo Tomé y Príncipe"),
    /**
     * Botswana
     */
    BWA("Botswana"),
    /**
     * Lesotho
     */
    LSO("Lesotho"),
    /**
     * Namibia
     */
    NAM("Namibia"),
    /**
     * Sudáfrica
     */
    ZAF("Sudáfrica"),
    /**
     * Swazilandia
     */
    SWZ("Swazilandia"),
    /**
     * Benin
     */
    BEN("Benin"),
    /**
     * Burkina Faso
     */
    BFA("Burkina Faso"),
    /**
     * Cabo Verde
     */
    CPV("Cabo Verde"),
    /**
     * Côte d'Ivoire
     */
    CIV("Côte d'Ivoire"),
    /**
     * Gambia
     */
    GMB("Gambia"),
    /**
     * Ghana
     */
    GHA("Ghana"),
    /**
     * Guinea
     */
    GIN("Guinea"),
    /**
     * Guinea-Bissau
     */
    GNB("Guinea-Bissau"),
    /**
     * Liberia
     */
    LBR("Liberia"),
    /**
     * Malí
     */
    MLI("Malí"),
    /**
     * Mauritania
     */
    MRT("Mauritania"),
    /**
     * Níger
     */
    NER("Níger"),
    /**
     * Nigeria
     */
    NGA("Nigeria"),
    /**
     * Santa Elena
     */
    SHN("Santa Elena"),
    /**
     * Senegal
     */
    SEN("Senegal"),
    /**
     * Sierra Leona
     */
    SLE("Sierra Leona"),
    /**
     * Togo
     */
    TGO("Togo"),
    /**
     * Anguila
     */
    AIA("Anguila"),
    /**
     * Antigua y Barbuda
     */
    ATG("Antigua y Barbuda"),
    /**
     * Aruba
     */
    ABW("Aruba"),
    /**
     * Bahamas
     */
    BHS("Bahamas"),
    /**
     * Barbados
     */
    BRB("Barbados"),
    /**
     * Bonaire, San Eustaquio y Saba
     */
    BES("Bonaire, San Eustaquio y Saba"),
    /**
     * Islas Vírgenes Británicas
     */
    VGB("Islas Vírgenes Británicas"),
    /**
     * Islas Caimán
     */
    CYM("Islas Caimán"),
    /**
     * CUBA
     */
    CUB("CUBA"),
    /**
     * Curaçao
     */
    CUW("Curaçao"),
    /**
     * Dominica
     */
    DMA("Dominica"),
    /**
     * República Dominicana
     */
    DOM("República Dominicana"),
    /**
     * Granada
     */
    GRD("Granada"),
    /**
     * Guadalupe
     */
    GLP("Guadalupe"),
    /**
     * Haití
     */
    HTI("Haití"),
    /**
     * Jamaica
     */
    JAM("Jamaica"),
    /**
     * Martinica
     */
    MTQ("Martinica"),
    /**
     * Montserrat
     */
    MSR("Montserrat"),
    /**
     * Puerto Rico
     */
    PRI("Puerto Rico"),
    /**
     * San Bartolomé
     */
    BLM("San Bartolomé"),
    /**
     * Saint Kitts y Nevis
     */
    KNA("Saint Kitts y Nevis"),
    /**
     * Santa Lucía
     */
    LCA("Santa Lucía"),
    /**
     * San Martín (parte francesa)
     */
    MAF("San Martín (parte francesa)"),
    /**
     * San Vicente y las Granadinas
     */
    VCT("San Vicente y las Granadinas"),
    /**
     * San Martín (parte holandés)
     */
    SXM("San Martín (parte holandés)"),
    /**
     * Trinidad y Tabago
     */
    TTO("Trinidad y Tabago"),
    /**
     * Islas Turcas y Caicos
     */
    TCA("Islas Turcas y Caicos"),
    /**
     * Islas Vírgenes de los Estados Unidos
     */
    VIR("Islas Vírgenes de los Estados Unidos"),
    /**
     * Belice
     */
    BLZ("Belice"),
    /**
     * Costa Rica
     */
    CRI("Costa Rica"),
    /**
     * El Salvador
     */
    SLV("El Salvador"),
    /**
     * Guatemala
     */
    GTM("Guatemala"),
    /**
     * Honduras
     */
    HND("Honduras"),
    /**
     * México
     */
    MEX("México"),
    /**
     * Nicaragua
     */
    NIC("Nicaragua"),
    /**
     * Panamá
     */
    PAN("Panamá"),
    /**
     * Argentina
     */
    ARG("Argentina"),
    /**
     * Bolivia (Estado Plurinacional de)
     */
    BOL("Bolivia (Estado Plurinacional de)"),
    /**
     * Brasil
     */
    BRA("Brasil"),
    /**
     * Chile
     */
    CHL("Chile"),
    /**
     * Colombia
     */
    COL("Colombia"),
    /**
     * Ecuador
     */
    ECU("Ecuador"),
    /**
     * Islas Malvinas (Falkland)
     */
    FLK("Islas Malvinas (Falkland)"),
    /**
     * Guayana Francesa
     */
    GUF("Guayana Francesa"),
    /**
     * Guyana
     */
    GUY("Guyana"),
    /**
     * Paraguay
     */
    PRY("Paraguay"),
    /**
     * Perú
     */
    PER("Perú"),
    /**
     * Georgia del Sur y las Islas Sandwich del Sur
     */
    SGS("Georgia del Sur y las Islas Sandwich del Sur"),
    /**
     * Suriname
     */
    SUR("Suriname"),
    /**
     * Uruguay
     */
    URY("Uruguay"),
    /**
     * Venezuela (República Bolivariana de)
     */
    VEN("Venezuela (República Bolivariana de)"),
    /**
     * Bermuda
     */
    BMU("Bermuda"),
    /**
     * Canadá
     */
    CAN("Canadá"),
    /**
     * Groenlandia
     */
    GRL("Groenlandia"),
    /**
     * Saint Pierre y Miquelon
     */
    SPM("Saint Pierre y Miquelon"),
    /**
     * Estados Unidos de América
     */
    USA("Estados Unidos de América"),
    /**
     * Antártida
     */
    ATA("Antártida"),
    /**
     * Kazajstán
     */
    KAZ("Kazajstán"),
    /**
     * Kirguistán
     */
    KGZ("Kirguistán"),
    /**
     * Tayikistán
     */
    TJK("Tayikistán"),
    /**
     * Turkmenistán
     */
    TKM("Turkmenistán"),
    /**
     * Uzbekistán
     */
    UZB("Uzbekistán"),
    /**
     * China
     */
    CHN("China"),
    /**
     * China, región administrativa especial de Hong Kong
     */
    HKG("China, región administrativa especial de Hong Kong"),
    /**
     * China, región administrativa especial de Macao
     */
    MAC("China, región administrativa especial de Macao"),
    /**
     * República Popular Democrática de Corea
     */
    PRK("República Popular Democrática de Corea"),
    /**
     * Japón
     */
    JPN("Japón"),
    /**
     * Mongolia
     */
    MNG("Mongolia"),
    /**
     * República de Corea
     */
    KOR("República de Corea"),
    /**
     * Brunei Darussalam
     */
    BRN("Brunei Darussalam"),
    /**
     * Camboya
     */
    KHM("Camboya"),
    /**
     * Indonesia
     */
    IDN("Indonesia"),
    /**
     * República Democrática Popular Lao
     */
    LAO("República Democrática Popular Lao"),
    /**
     * Malasia
     */
    MYS("Malasia"),
    /**
     * Myanmar
     */
    MMR("Myanmar"),
    /**
     * Filipinas
     */
    PHL("Filipinas"),
    /**
     * Singapur
     */
    SGP("Singapur"),
    /**
     * Tailandia
     */
    THA("Tailandia"),
    /**
     * Timor-Leste
     */
    TLS("Timor-Leste"),
    /**
     * Viet Nam
     */
    VNM("Viet Nam"),
    /**
     * Afganistán
     */
    AFG("Afganistán"),
    /**
     * Bangladesh
     */
    BGD("Bangladesh"),
    /**
     * Bhután
     */
    BTN("Bhután"),
    /**
     * India
     */
    IND("India"),
    /**
     * Irán (República Islámica del)
     */
    IRN("Irán (República Islámica del)"),
    /**
     * Maldivas
     */
    MDV("Maldivas"),
    /**
     * Nepal
     */
    NPL("Nepal"),
    /**
     * Pakistán
     */
    PAK("Pakistán"),
    /**
     * Sri Lanka
     */
    LKA("Sri Lanka"),
    /**
     * Armenia
     */
    ARM("Armenia"),
    /**
     * Azerbaiyán
     */
    AZE("Azerbaiyán"),
    /**
     * Bahrein
     */
    BHR("Bahrein"),
    /**
     * Chipre
     */
    CYP("Chipre"),
    /**
     * Georgia
     */
    GEO("Georgia"),
    /**
     * Iraq
     */
    IRQ("Iraq"),
    /**
     * Israel
     */
    ISR("Israel"),
    /**
     * Jordania
     */
    JOR("Jordania"),
    /**
     * Kuwait
     */
    KWT("Kuwait"),
    /**
     * Líbano
     */
    LBN("Líbano"),
    /**
     * Omán
     */
    OMN("Omán"),
    /**
     * Qatar
     */
    QAT("Qatar"),
    /**
     * Arabia Saudita
     */
    SAU("Arabia Saudita"),
    /**
     * Estado de Palestina
     */
    PSE("Estado de Palestina"),
    /**
     * República Árabe Siria
     */
    SYR("República Árabe Siria"),
    /**
     * Turquía
     */
    TUR("Turquía"),
    /**
     * Emiratos Árabes Unidos
     */
    ARE("Emiratos Árabes Unidos"),
    /**
     * Yemen
     */
    YEM("Yemen"),
    /**
     * Belarús
     */
    BLR("Belarús"),
    /**
     * Bulgaria
     */
    BGR("Bulgaria"),
    /**
     * Chequia
     */
    CZE("Chequia"),
    /**
     * Hungría
     */
    HUN("Hungría"),
    /**
     * Polonia
     */
    POL("Polonia"),
    /**
     * República de Moldova
     */
    MDA("República de Moldova"),
    /**
     * Rumania
     */
    ROU("Rumania"),
    /**
     * Federación de Rusia
     */
    RUS("Federación de Rusia"),
    /**
     * Eslovaquia
     */
    SVK("Eslovaquia"),
    /**
     * Ucrania
     */
    UKR("Ucrania"),
    /**
     * Islas Åland
     */
    ALA("Islas Åland"),
    /**
     * Guernsey
     */
    GGY("Guernsey"),
    /**
     * Jersey
     */
    JEY("Jersey"),
    /**
     * Dinamarca
     */
    DNK("Dinamarca"),
    /**
     * Estonia
     */
    EST("Estonia"),
    /**
     * Islas Feroe
     */
    FRO("Islas Feroe"),
    /**
     * Finlandia
     */
    FIN("Finlandia"),
    /**
     * Islandia
     */
    ISL("Islandia"),
    /**
     * Irlanda
     */
    IRL("Irlanda"),
    /**
     * Isla de Man
     */
    IMN("Isla de Man"),
    /**
     * Letonia
     */
    LVA("Letonia"),
    /**
     * Lituania
     */
    LTU("Lituania"),
    /**
     * Noruega
     */
    NOR("Noruega"),
    /**
     * Islas Svalbard y Jan Mayen
     */
    SJM("Islas Svalbard y Jan Mayen"),
    /**
     * Suecia
     */
    SWE("Suecia"),
    /**
     * Reino Unido de Gran Bretaña e Irlanda del Norte
     */
    GBR("Reino Unido de Gran Bretaña e Irlanda del Norte"),
    /**
     * Albania
     */
    ALB("Albania"),
    /**
     * Andorra
     */
    AND("Andorra"),
    /**
     * Bosnia y Herzegovina
     */
    BIH("Bosnia y Herzegovina"),
    /**
     * Croacia
     */
    HRV("Croacia"),
    /**
     * Gibraltar
     */
    GIB("Gibraltar"),
    /**
     * Grecia
     */
    GRC("Grecia"),
    /**
     * Santa Sede
     */
    VAT("Santa Sede"),
    /**
     * Italia
     */
    ITA("Italia"),
    /**
     * Malta
     */
    MLT("Malta"),
    /**
     * Montenegro
     */
    MNE("Montenegro"),
    /**
     * Portugal
     */
    PRT("Portugal"),
    /**
     * San Marino
     */
    SMR("San Marino"),
    /**
     * Serbia
     */
    SRB("Serbia"),
    /**
     * Eslovenia
     */
    SVN("Eslovenia"),
    /**
     * España
     */
    ESP("España"),
    /**
     * ex República Yugoslava de Macedonia
     */
    MKD("ex República Yugoslava de Macedonia"),
    /**
     * Austria
     */
    AUT("Austria"),
    /**
     * Bélgica
     */
    BEL("Bélgica"),
    /**
     * Francia
     */
    FRA("Francia"),
    /**
     * Alemania
     */
    DEU("Alemania"),
    /**
     * Liechtenstein
     */
    LIE("Liechtenstein"),
    /**
     * Luxemburgo
     */
    LUX("Luxemburgo"),
    /**
     * Mónaco
     */
    MCO("Mónaco"),
    /**
     * Países Bajos
     */
    NLD("Países Bajos"),
    /**
     * Suiza
     */
    CHE("Suiza"),
    /**
     * Australia
     */
    AUS("Australia"),
    /**
     * Isla de Navidad
     */
    CXR("Isla de Navidad"),
    /**
     * Islas Cocos (Keeling)
     */
    CCK("Islas Cocos (Keeling)"),
    /**
     * Islas Heard y McDonald
     */
    HMD("Islas Heard y McDonald"),
    /**
     * Nueva Zelandia
     */
    NZL("Nueva Zelandia"),
    /**
     * Islas Norfolk
     */
    NFK("Islas Norfolk"),
    /**
     * Fiji
     */
    FJI("Fiji"),
    /**
     * Nueva Caledonia
     */
    NCL("Nueva Caledonia"),
    /**
     * Papua Nueva Guinea
     */
    PNG("Papua Nueva Guinea"),
    /**
     * Islas Salomón
     */
    SLB("Islas Salomón"),
    /**
     * Vanuatu
     */
    VUT("Vanuatu"),
    /**
     * Guam
     */
    GUM("Guam"),
    /**
     * Kiribati
     */
    KIR("Kiribati"),
    /**
     * Islas Marshall
     */
    MHL("Islas Marshall"),
    /**
     * Micronesia (Estados Federados de)
     */
    FSM("Micronesia (Estados Federados de)"),
    /**
     * Nauru
     */
    NRU("Nauru"),
    /**
     * Islas Marianas Septentrionales
     */
    MNP("Islas Marianas Septentrionales"),
    /**
     * Palau
     */
    PLW("Palau"),
    /**
     * Islas menores alejadas de Estados Unidos
     */
    UMI("Islas menores alejadas de Estados Unidos"),
    /**
     * Samoa Americana
     */
    ASM("Samoa Americana"),
    /**
     * Islas Cook
     */
    COK("Islas Cook"),
    /**
     * Polinesia Francesa
     */
    PYF("Polinesia Francesa"),
    /**
     * Niue
     */
    NIU("Niue"),
    /**
     * Pitcairn
     */
    PCN("Pitcairn"),
    /**
     * Samoa
     */
    WSM("Samoa"),
    /**
     * Tokelau
     */
    TKL("Tokelau"),
    /**
     * Tonga
     */
    TON("Tonga"),
    /**
     * Tuvalu
     */
    TUV("Tuvalu"),
    /**
     * Islas Wallis y Futuna
     */
    WLF("Islas Wallis y Futuna"),
    /**
     * NO EXISTE
     */
    NN("NO EXISTE");

    private String nombre;

    PaisType(String nombre) {
        this.nombre = nombre;
    }

    public static PaisType getByName(String name) {
        return Arrays.stream(PaisType.values()).filter(e -> e.name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "{\"nombre\":\"" + nombre + "\"}";
    }
}