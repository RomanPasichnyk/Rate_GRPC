package com.rate.streaming;

import com.rate.Rate;
import com.rate.RateRequest;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

public class PrivatRateGrabber implements BankGrabber {

    private RateValuesStreamer rateValuesStreamer = new RateValuesStreamer(new CurrencyProvider());

    @Override
    public StreamObserver<RateRequest> getRate(StreamObserver<Rate> responseObserver) {
        return rateValuesStreamer.stream(responseObserver);
    }
}
