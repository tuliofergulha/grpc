package br.com.fergulha.grpc.server;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class ServerApplication {

    private static final Logger LOGGER = Logger.getLogger(ServerApplication.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        LOGGER.info("Server startup. Args = " + Arrays.toString(args));
        final HelloWorldServer helloWorldServer = new HelloWorldServer();

        helloWorldServer.start();
        helloWorldServer.blockUntilShutdown();
    }

}
