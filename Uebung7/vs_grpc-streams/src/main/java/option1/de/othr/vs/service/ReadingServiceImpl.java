package option1.de.othr.vs.service;

import io.grpc.stub.StreamObserver;
import reading.api.Evaluation;
import reading.api.Reading;
import reading.api.ReadingServiceGrpc;

import java.util.Date;

public class ReadingServiceImpl extends  ReadingServiceGrpc.ReadingServiceImplBase {


    @Override
    public void insertReading(Reading reading, StreamObserver<Evaluation> callback) {
        System.out.println("New Reading: "+ reading);

        String evaluationString = (reading.getData() > 8) ? "Schleuse oeffnen" : "Alles gut!";
        Evaluation evaluation = Evaluation.newBuilder().
                setEvaluation(evaluationString).
                setDate(new Date().toString()).build();

        callback.onNext(evaluation);
        callback.onCompleted();

    }
}
