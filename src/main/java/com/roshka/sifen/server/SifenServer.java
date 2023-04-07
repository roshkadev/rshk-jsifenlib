package com.roshka.sifen.server;

import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.server.handlers.EmptyHandler;
import com.roshka.sifen.server.handlers.SifenServerBaseHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;

public class SifenServer {


    private final static Logger logger = Logger.getLogger(SifenServer.class.toString());

    private final SifenConfig sifenConfig;
    private Thread t;

    private HttpServer httpServer;

    private Executor executor;

    public SifenServer(SifenConfig sifenConfig) {
        this.sifenConfig = sifenConfig;
    }

    public void launchServer() {

        try {

            logger.info("Creando el pool de threads para manejar las peticiones");

            executor = Executors.newFixedThreadPool(200, new ThreadFactory() {
                private int n = 0;
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setName(String.format("sifen-server-t-%04d", n++));
                    return t;
                }
            });

            logger.info("Creando servidor HTTP en el puerto: " + sifenConfig.getServerPort());

            HttpServer server = HttpServer.create(new InetSocketAddress(sifenConfig.getServerPort()), 0);

            // setup paths
            server.createContext("/", new SifenServerBaseHandler(sifenConfig));

            server.setExecutor(executor);
            server.start();

        } catch (IOException e) {
            logger.throwing(SifenServer.class.toString(), "launchServer", e);
        }

    }

}
