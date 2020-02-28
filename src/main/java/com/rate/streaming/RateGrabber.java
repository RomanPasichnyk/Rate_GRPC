package com.rate.streaming;

import com.rate.Currency;
import com.rate.Rate;
import com.rate.RateRequest;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

public class RateGrabber {

    private final RateValuesStreamer rateValuesStreamer = new RateValuesStreamer(new CurrencyProvider());

    public RateGrabber() {

    }

    //    public StreamObserver<RateRequest> getRate(String bankName, Currency from, Currency to) {
//    public StreamObserver<RateRequest> getRate(StreamObserver<Rate> responseObserver, BankGrabber bank) {
////        float rate = currencyProvider.getRate(from, to).getValue();
//        return rateValuesStreamer.stream(responseObserver, bank);
////        return Rate.newBuilder().setFrom(from).setTo(to).setValue(rate).build();
//    }

}
