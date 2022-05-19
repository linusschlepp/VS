package option2.de.othr.vs.client;

import io.grpc.stub.StreamObserver;
import option2.de.othr.vs.server.ReadingServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import option2.de.othr.vs.util.BlockingStreamObserver;
import reading.api.Evaluation;
import reading.api.Reading;
import reading.api.ReadingServiceGrpc;


import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(ReadingServer.SERVER_GRPC_HOST,
                        ReadingServer.SERVER_GRPC_PORT)
                .usePlaintext()
                .build();

        Reading reading = Reading
                .newBuilder().setReading("Wasserstand Neumarkt")
                .setData((float) 8.40).build();

        ReadingServiceGrpc.ReadingServiceStub stub =
                ReadingServiceGrpc.newStub(channel);
        System.out.println("Received reading: ");
        Object monitor = new Object();
        BlockingStreamObserver blockingStreamObserver = new BlockingStreamObserver(monitor);


        stub.insertReading(reading, blockingStreamObserver);

        synchronized (monitor) {
            monitor.wait();
        }

        System.out.println("Thank you!");

        channel.awaitTermination(30L, TimeUnit.SECONDS);

    }


}
