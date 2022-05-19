package option3.de.othr.vs.client;

import io.grpc.stub.StreamObserver;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import option3.de.othr.vs.server.ReadingServer;
import option3.de.othr.vs.util.EvaluationStreamObserver;
import option3.de.othr.vs.util.ReadingStreamObserver;
import reading.api.Evaluation;
import reading.api.Reading;
import reading.api.ReadingServiceGrpc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(ReadingServer.SERVER_GRPC_HOST,
                        ReadingServer.SERVER_GRPC_PORT)
                .usePlaintext()
                .build();


        ReadingServiceGrpc.ReadingServiceStub stub =
                ReadingServiceGrpc.newStub(channel);
        EvaluationStreamObserver evaluationStreamObserver = new EvaluationStreamObserver();

        StreamObserver<Reading> readingStreamObserver = stub.compileEvaluation(evaluationStreamObserver);

        Arrays.asList(6.25f, 7.90f, 9.40f, 8.40f, 10.3f, 7.3f, 5.6f, 10.9f).forEach(f -> {

                    readingStreamObserver.onNext(Reading
                            .newBuilder().setReading("Wasserstand Neumarkt")
                            .setData(f).build());

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );


        System.out.println("Thank you!");

        channel.awaitTermination(30L, TimeUnit.SECONDS);

    }


}
