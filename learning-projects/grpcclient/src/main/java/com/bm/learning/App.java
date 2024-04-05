package com.bm.learning;

import com.bm.learning.grpc.greetings.GreetingServiceGrpc;
import com.bm.learning.grpc.greetings.HelloRequest;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var managedChannel = ManagedChannelBuilder.forAddress("localhost", 20503)
                .usePlaintext()
                .build();
        var greetingServiceBlockingStub = GreetingServiceGrpc.newBlockingStub(managedChannel);

        var helloRequest =
                HelloRequest.newBuilder()
                        .setFirstName("blue")
                        .setLastName("mountain")
                        .build();

        var greetingResponse = greetingServiceBlockingStub.hello(helloRequest);
        System.out.println(greetingResponse.getGreeting());
        managedChannel.shutdown();

    }
}
