package com.bm.learning;

import com.bm.learning.grpc.greetings.GreetingServiceGrpc;
import com.bm.learning.grpc.greetings.HelloRequest;
import io.grpc.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        // This is for NON-TLS gRPC server
        /*
        var managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 20503)
                .usePlaintext()
                .build();

         */

        // This is for TLS gRPC server

        // First build a trust store trusting the certificate of the CA
        ChannelCredentials channelCredentials = TlsChannelCredentials.newBuilder()
                .trustManager(getFile("ca.crt"))
                // this is needed if we want mutual TLS where client needs to prove his identity to server
                .keyManager(getFile("client.crt"), getFile("client.pem"))
                .build();
        // Second, build the channel using the channel credentials created above.
        ManagedChannel managedChannel = Grpc.newChannelBuilderForAddress("localhost", 20503, channelCredentials)
                .build();



        var greetingServiceBlockingStub = GreetingServiceGrpc.newBlockingStub(managedChannel);

        var helloRequest =
                HelloRequest.newBuilder()
                        .setFirstName("blue is my first name")
                        .setLastName("mountain")
                        .build();

        var greetingResponse = greetingServiceBlockingStub.hello(helloRequest);
        System.out.println(greetingResponse.getGreeting());

        // Simulate failure by sending firstname="" to server
        var helloRequest2 =
                HelloRequest.newBuilder()
                        .setFirstName("")
                        .setLastName("mountain")
                        .build();
        var greetingResponse2 = greetingServiceBlockingStub.hello(helloRequest2);
        System.out.println(greetingResponse2.getGreeting());

        managedChannel.shutdown();

    }
    public static File getFile(String fileName)
    {
        ClassLoader classLoader = App.class.getClassLoader();
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
