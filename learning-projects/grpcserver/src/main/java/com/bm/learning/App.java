package com.bm.learning;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer(20503);
        server.start();
        server.blockUntilShutDown();
        System.out.println( "Hello World!" );
    }
}
