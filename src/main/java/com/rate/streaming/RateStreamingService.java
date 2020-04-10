package com.rate.streaming;

import static java.util.Arrays.asList;
import com.google.common.collect.ImmutableList;
import com.rate.RateRequest;
import com.rate.RateResponse;
import com.rate.RateStreamingServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.List;

public class RateStreamingService extends RateStreamingServiceGrpc.RateStreamingServiceImplBase {

    private final UkrsibRateGrabber ukrsibRateGrabber;

    private final PrivatRateGrabber privatRateGrabber;

    public RateStreamingService(UkrsibRateGrabber ukrsibRateGrabber, PrivatRateGrabber privatRateGrabber) {
        this.ukrsibRateGrabber = ukrsibRateGrabber;
        this.privatRateGrabber = privatRateGrabber;
    }

    @Override
    public StreamObserver<RateRequest> observe(StreamObserver<RateResponse> responseObserver) {

        StreamObserver<RateRequest> ukrsibClientStream = ukrsibRateGrabber.getRate(responseObserver);

        StreamObserver<RateRequest> privatClientStream = privatRateGrabber.getRate(responseObserver);

        List<StreamObserver<RateRequest>> clientsStreams =
            ImmutableList.copyOf(asList(ukrsibClientStream, privatClientStream));
        return new StreamObserver<>() {
            @Override
            public void onNext(RateRequest rateRequest) {
                System.out.println(rateRequest.getBank().getName());
                clientsStreams.forEach(s -> s.onNext(rateRequest));
            }

            @Override
            public void onError(Throwable throwable) {
                clientsStreams.forEach(s -> s.onError(throwable));
            }

            @Override
            public void onCompleted() {
                clientsStreams.forEach(StreamObserver::onCompleted);
            }
        };
    }

}
