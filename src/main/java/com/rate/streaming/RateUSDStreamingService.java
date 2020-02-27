package com.rate.streaming;

import com.rate.Bank;
import com.rate.Rate;
import com.rate.RateResponse;
import com.rate.providers.USDProvider;
import io.grpc.stub.StreamObserver;

public class RateUSDStreamingService{

    private final ValuesStreamer<Rate> valuesStreamer =
            new ValuesStreamer<>(new USDProvider());

    public StreamObserver<Bank> observe(StreamObserver<Rate> responseObserver) {
        return valuesStreamer.stream(responseObserver);
    }
}
