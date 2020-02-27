package com.rate.streaming;

import com.rate.Bank;
import com.rate.Rate;
import com.rate.providers.EuroProvider;
import io.grpc.stub.StreamObserver;

public class RateEuroStreamingService {

    private final ValuesStreamer<Rate> valuesStreamer =
            new ValuesStreamer<>(new EuroProvider());

    public StreamObserver<Bank> observe(StreamObserver<Rate> responseObserver) {
        return valuesStreamer.stream(responseObserver);
    }
}
