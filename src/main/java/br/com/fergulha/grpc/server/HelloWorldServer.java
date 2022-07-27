package br.com.fergulha.grpc.server;

import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class HelloWorldServer {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldServer.class.getName());

    private Server server;

    public void start() throws IOException {
        LOGGER.info("Starting gRPC server...");

        int port = 42420;
        server = ServerBuilder.forPort(port)
            .addService(new GreeterImpl())
            .build()
            .start();

        LOGGER.info("Server started. Listening port: " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Turning off gRPC server...");
            stop();
            System.err.println("Shutdown complete!");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
