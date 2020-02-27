package com.rate.streaming;

import com.rate.Bank;
import com.rate.Rate;
import com.rate.providers.RubProvider;
import io.grpc.stub.StreamObserver;

public class RateRubStreamingService {

    private final ValuesStreamer<Rate> valuesStreamer = new ValuesStreamer<>(new RubProvider());

    public StreamObserver<Bank> observe(StreamObserver<Rate> responseObserver) {
        return valuesStreamer.stream(responseObserver);
    }
}
