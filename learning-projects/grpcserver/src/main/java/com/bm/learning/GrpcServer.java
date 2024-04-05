package com.bm.learning;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GrpcServer {
    private final int port;
    private final Server server;

    public GrpcServer(int port) {
        this.port = port;
        var greetingService = new GreetingServiceImpl();
        this.server = ServerBuilder.forPort(port).addService(greetingService).build();
    }

    public void start() throws IOException {

        server.start();
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread(
                                () -> {
                                    try {
                                        this.stop();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutDown() throws InterruptedException {
        if (this.server != null) {
            server.awaitTermination();
        }
    }
}