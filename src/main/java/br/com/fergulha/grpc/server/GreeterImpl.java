package br.com.fergulha.grpc.server;

import java.util.logging.Logger;

import br.com.fergulha.grpc.GreeterGrpc;
import br.com.fergulha.grpc.HelloRequest;
import br.com.fergulha.grpc.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    private static final Logger LOGGER = Logger.getLogger(GreeterImpl.class.getName());

    @Override
    public void sayHello(HelloRequest request,
        StreamObserver<HelloResponse> responseObserver) {

        LOGGER.info("Server receive message from: " + request.getName());

        HelloResponse response = HelloResponse.newBuilder().setMessage("Hello " + request.getName())
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
