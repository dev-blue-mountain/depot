package com.bm.learning;

import io.grpc.Grpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.TlsServerCredentials;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GrpcServer {
    private final int port;
    private final Server server;

    public GrpcServer(int port) throws IOException {
        this.port = port;
        // This part is needed if we want our server to be over TLS (just one way)
        TlsServerCredentials.Builder tlsServerCredentialBuilder = TlsServerCredentials.newBuilder()
                .keyManager(getFile("server.crt"), getFile("server.pem"));
        // This part is needed if we want mutual TLS
        boolean enableMutualTLS = true;
        if(enableMutualTLS)
        {
            tlsServerCredentialBuilder.trustManager(getFile("ca.crt"));
            tlsServerCredentialBuilder.clientAuth(TlsServerCredentials.ClientAuth.REQUIRE);
        }
        var greetingService = new GreetingServiceImpl();
        this.server = Grpc.newServerBuilderForPort(port, tlsServerCredentialBuilder.build())
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