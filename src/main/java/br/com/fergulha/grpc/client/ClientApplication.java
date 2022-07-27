package br.com.fergulha.grpc.client;

public class ClientApplication {

    public static void main(String[] args) throws Exception {
        HelloWorldClient client = new HelloWorldClient("localhost", 42420);
        String name = args.length > 0 ? args[0] : "unknown";

        try {
            client.greet(name);
        } finally {
            client.shutdown();
        }
    }
}
