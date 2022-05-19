package option3.de.othr.vs.util;

import io.grpc.stub.StreamObserver;
import reading.api.Evaluation;
import reading.api.Reading;

import java.util.Date;

public class ReadingStreamObserver implements StreamObserver<reading.api.Reading> {


   StreamObserver<Evaluation> callback;

    public ReadingStreamObserver(StreamObserver<Evaluation> callback) {
       this.callback = callback;
    }

    @Override
    public void onNext(Reading reading) {

        System.out.println("New Reading: " + reading);
        if (reading.getData() - 8 > 0) {
            Evaluation evaluation = Evaluation.newBuilder().
                    setEvaluation((reading.getData() - 8) + "m zu hoher Wasserstand, Schleuse oeffnen").
                    setDate(new Date().toString()).build();

            callback.onNext(evaluation);
        }
    }



    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {

    }
}
