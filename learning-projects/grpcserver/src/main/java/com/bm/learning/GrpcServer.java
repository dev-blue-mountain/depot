package com.bm.learning;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GrpcServer {
    private final int port;
    private final Server server;

    public GrpcServer(int port) {
        this.port = port;
        var greetingService = new GreetingServiceImpl();
        this.server = ServerBuilder
                .forPort(port)
                // this is the piece that enables TLS on this gRPC server
                .useTransportSecurity(
                        getFile("server.crt"),
                        getFile("server.pem")
                )
                .addService(greetingService)
                .build();
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
    public static File getFile(String fileName)
    {
        ClassLoader classLoader = GrpcServer.class.getClassLoader();
        // or
        //ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null)
        {
            throw new IllegalArgumentException("file is not found!");
        } else
        {
            return new File(resource.getFile());
        }
    }

}