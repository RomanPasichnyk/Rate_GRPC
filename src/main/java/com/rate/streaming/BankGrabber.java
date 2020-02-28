package com.rate.streaming;

import com.rate.Currency;
import com.rate.Rate;
import com.rate.RateRequest;
import io.grpc.stub.StreamObserver;

public interface BankGrabber {

    StreamObserver<RateRequest> getRate(StreamObserver<Rate> responseObserver);

}
