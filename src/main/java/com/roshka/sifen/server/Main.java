package com.roshka.sifen.server;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    enum Status {
        STARTING,
        SERVER_RUNNING,
        STOP_REQUESTED,
        STOPPING,
        STOPPED
    }

    public static final String SIFEN_CONFIG_LOCATION_ENV = "SIFEN_CONFIG_LOCATION";
    public static final String SIFEN_CONFIG_DEFAULT_LOCATION = "config/sifen.properties";

    private static final Logger logger = Logger.getLogger(Main.class.toString());

    private SifenConfig sifenConfig;

    private Status status;

    public Main() {
        this.status = Status.STARTING;
    }

    private void loadConfig(CmdLineArgs args) throws IOException {

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
        if (args.configFileName != null) {
            logger.info("ARGUMENTO de LÍNEA DE COMANDO: " + args.configFileName);
            configFiles.add(args.configFileName);
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

    private void setupLogger(CmdLineArgs cmdLineArgs) {
        boolean readConfiguration = false;
        if (cmdLineArgs.loggingConfigFileName != null) {
            logger.info("Leyendo configuración de logger desde archivo [" + cmdLineArgs.loggingConfigFileName + "]");
            try {
                LogManager.getLogManager().readConfiguration(
                        new FileInputStream((cmdLineArgs.loggingConfigFileName))
                );
                readConfiguration = true;
            } catch (IOException e) {
                logger.warning("No se pudo leer configuración del archivo: " + e.getMessage());
            }
        }

        if (!readConfiguration) {
            try {
                LogManager.getLogManager().readConfiguration(
                        Main.class.getResourceAsStream("/logging.properties")
                );
            } catch (IOException e) {
                logger.warning("No se pudo leer configuración del archivo: " + e.getMessage() + " que debería estar en la librería");
            }
        }
    }

    private void run() {

        logger.info("Ejecutando SIFEN SERVER");

        SifenServer sifenServer = new SifenServer(sifenConfig);
        sifenServer.launchServer();

        status = Status.SERVER_RUNNING;

        while (status == Status.SERVER_RUNNING) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                status = Status.STOP_REQUESTED;
                // stop server and exit
                sifenServer.stop();
                status = Status.STOPPED;
            }
        }

        logger.info("Parando SIFEN SERVER");

    }

    public static void main(String[] args) {
        logger.info("Parseando línea de comandos");

        CmdLineArgs cmdLineArgs = new CmdLineArgs();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-c":
                case "--config":
                    if (i + 1 < args.length) {
                        cmdLineArgs.configFileName = args[i + 1];
                        i++;
                    }
                    break;
                case "-l":
                case "--logging-config":
                    if (i + 1 < args.length) {
                        cmdLineArgs.loggingConfigFileName = args[i + 1];
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
            main.loadConfig(cmdLineArgs);
            logger.info("Configurando logger");
            main.setupLogger(cmdLineArgs);
            logger.info("Ejecutando el servidor SIFEN");
            main.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static class CmdLineArgs {
        private String configFileName;
        private String loggingConfigFileName;
    }

}
