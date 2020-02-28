package com.rate.streaming;

import com.rate.Rate;
import com.rate.RateRequest;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

public class UkrsibStreamingService {

    private final RateValuesStreamer valuesStreamer =
            new RateValuesStreamer(new CurrencyProvider());

    public StreamObserver<RateRequest> observe(StreamObserver<Rate> responseObserver) {
        return valuesStreamer.stream(responseObserver);
    }

}
