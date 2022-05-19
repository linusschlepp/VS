package option3.de.othr.vs.service;

import io.grpc.stub.StreamObserver;
import option3.de.othr.vs.util.ReadingStreamObserver;
import reading.api.Evaluation;
import reading.api.Reading;
import reading.api.ReadingServiceGrpc;


public class ReadingServiceImpl
        extends ReadingServiceGrpc.ReadingServiceImplBase {


    @Override
    public StreamObserver<Reading> compileEvaluation(StreamObserver<Evaluation> callback) {


        return new ReadingStreamObserver(callback);
    }
}