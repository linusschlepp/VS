package option1.de.othr.vs.client;

import option1.de.othr.vs.server.ReadingServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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
                        .setData((float) 5.40).build();

        ReadingServiceGrpc.ReadingServiceBlockingStub blockingStub =
               ReadingServiceGrpc.newBlockingStub(channel);
        System.out.println("Received reading: " +  blockingStub.insertReading(reading));
        System.out.println("Thank u!");


        channel.awaitTermination(30L, TimeUnit.SECONDS);
    }
}
