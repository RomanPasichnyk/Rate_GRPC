package com.rate.streaming;

import com.rate.Currency;
import com.rate.Rate;
import com.rate.RateRequest;
import com.rate.RateResponse;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

public class UkrsibRateGrabber implements BankGrabber {

    private RateValuesStreamer rateValuesStreamer = new RateValuesStreamer(new CurrencyProvider());

    @Override
    public StreamObserver<RateRequest> getRate(StreamObserver<Rate> responseObserver) {
        return rateValuesStreamer.stream(responseObserver);
    }
}
