package option2.de.othr.vs.util;

import io.grpc.stub.StreamObserver;
import reading.api.Evaluation;

public class BlockingStreamObserver implements StreamObserver<reading.api.Evaluation> {


    private Evaluation retEvaluation;
    private final Object monitor;

    public BlockingStreamObserver(Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void onNext(Evaluation evaluation) {
        retEvaluation = evaluation;
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {


        synchronized (monitor) {
            System.out.println(retEvaluation);
            monitor.notifyAll();
        }

    }



}
