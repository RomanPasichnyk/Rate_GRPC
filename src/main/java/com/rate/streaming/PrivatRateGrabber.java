package com.rate.streaming;

import com.rate.RateRequest;
import com.rate.RateResponse;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

public class PrivatRateGrabber implements BankGrabber {

    private RateValuesStreamer rateValuesStreamer = new RateValuesStreamer(new CurrencyProvider());

    @Override
    public StreamObserver<RateRequest> getRate(StreamObserver<RateResponse> responseObserver) {
        return rateValuesStreamer.stream(responseObserver);
    }
}
