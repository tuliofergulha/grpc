package br.com.fergulha.grpc.client;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.fergulha.grpc.GreeterGrpc;
import br.com.fergulha.grpc.HelloRequest;
import br.com.fergulha.grpc.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldClient.class.getName());

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub stub;

    public HelloWorldClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext(true)
            .build();
        stub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        LOGGER.info("Trying to greet " + name);
        try {
            HelloRequest request = HelloRequest.newBuilder().setName(name).build();
            HelloResponse response = stub.sayHello(request);
            LOGGER.info("Response: " + response.getMessage());
        } catch (RuntimeException e) {
            LOGGER.log(Level.WARNING, "Request gRPC server failed", e);
        }
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
