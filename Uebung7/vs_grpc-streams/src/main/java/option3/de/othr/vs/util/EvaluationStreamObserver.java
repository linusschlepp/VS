package option3.de.othr.vs.util;

import io.grpc.stub.StreamObserver;
import reading.api.Evaluation;

public class EvaluationStreamObserver implements StreamObserver<reading.api.Evaluation> {


    @Override
    public void onNext(Evaluation evaluation) {
        System.out.println(evaluation);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        System.out.println("Terminated");

    }



}
