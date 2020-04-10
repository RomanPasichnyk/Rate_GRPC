package com.rate.streaming;

import com.rate.RateRequest;
import com.rate.RateResponse;
import io.grpc.stub.StreamObserver;

public interface BankGrabber {

    StreamObserver<RateRequest> getRate(StreamObserver<RateResponse> responseObserver);

}
