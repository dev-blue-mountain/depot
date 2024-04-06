package com.bm.learning;

import com.bm.learning.grpc.greetings.GreetingServiceGrpc;
import com.bm.learning.grpc.greetings.HelloRequest;
import com.bm.learning.grpc.greetings.HelloResponse;
import io.grpc.Status;


public class GreetingServiceImpl extends  GreetingServiceGrpc.GreetingServiceImplBase{
    @Override
    public void hello(HelloRequest request, io.grpc.stub.StreamObserver<HelloResponse> responseObserver) {
        String firstName = request.getFirstName();
        if(firstName.isEmpty())
        {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("first name cannot be empty")
                    .augmentDescription("first name is empty")
                    .asRuntimeException());
            return;
        }
        HelloResponse response= HelloResponse.newBuilder()
                .setGreeting("Hello"+ firstName)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
