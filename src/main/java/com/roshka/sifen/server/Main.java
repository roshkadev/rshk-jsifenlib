package com.roshka.sifen.server;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

    public static final String SIFEN_CONFIG_LOCATION_ENV = "SIFEN_CONFIG_LOCATION";
    public static final String SIFEN_CONFIG_DEFAULT_LOCATION = "config/sifen.properties";

    private static final Logger logger = Logger.getLogger(Main.class.toString());

    private SifenConfig sifenConfig;

    public Main() {

    }

    private void loadConfig(String configFileNameArgument) throws IOException {

        Map<String, String> env = System.getenv();
        List<String> configFiles = new ArrayList<>();

        logger.info("Buscando ubicaciones de archivo de configuración en orden");

        logger.info("1. buscando por la variable de ambiente " + SIFEN_CONFIG_LOCATION_ENV);
        if (env.containsKey(SIFEN_CONFIG_LOCATION_ENV)) {
            String configFileEnv = env.get(SIFEN_CONFIG_LOCATION_ENV);
            logger.info("VARIABLE DE AMBIENTE: " + configFileEnv);
            configFiles.add(configFileEnv);
        } else {
            logger.info("VARIABLE DE AMBIENTE NO ESTABLECIDA");
        }

        logger.info("2. parámetros por la línea de comandos");
        if (configFileNameArgument != null) {
            logger.info("ARGUMENTO de LÍNEA DE COMANDO: " + configFileNameArgument);
            configFiles.add(configFileNameArgument);
        }



        String currentPath = new java.io.File(".").getCanonicalPath();
        String configFileNameDefault = currentPath + FileSystems.getDefault().getSeparator() + SIFEN_CONFIG_DEFAULT_LOCATION;
        logger.info("3. ubicación por defecto: [" + configFileNameDefault + "]");
        configFiles.add(configFileNameDefault);

        for (String configFile : configFiles) {
            try {
                sifenConfig = SifenConfig.cargarConfiguracion(configFile);
                break;
            } catch (SifenException e) {
                logger.warning("No se pudo cargar configuración de [" + configFile + "] -> " + e.getMessage());
            }
        }

        if (sifenConfig == null) {
            throw new IOException("No se pudo cargar la configuración de ninguno de los lugares definidos. No se puede continuar!");
        }

    }

    private void run() {

        SifenServer sifenServer = new SifenServer(sifenConfig);
        sifenServer.launchServer();

    }

    public static void main(String[] args) {
        logger.info("Parseando línea de comandos");

        String configFileName = null;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-c":
                case "--config":
                    if (i + 1 < args.length) {
                        configFileName = args[i + 1];
                        i++;
                    }
                    break;
                default:
                    System.err.println("Unknown argument: " + arg);
            }
        }

        logger.info("Iniciando el servidor SIFEN");
        Main main = new Main();
        try {
            logger.info("Cargando configuración de SIFEN");
            main.loadConfig(configFileName);
            logger.info("Ejecutando el servidor SIFEN");
            main.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
