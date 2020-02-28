package com.rate.streaming;

import com.rate.*;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateValuesStreamer {

    private final Set<StreamObserver<Rate>> observers = ConcurrentHashMap.newKeySet();
    private final CurrencyProvider currencyProvider;

    public RateValuesStreamer(CurrencyProvider currencyProvider) {
        this.currencyProvider = currencyProvider;

        long randomDelayInMillis = TimeUnit.SECONDS.toMillis(1 + new Random().nextInt(4));
        Timer timer = new Timer();
        timer.schedule(new GenerateValueTask(), randomDelayInMillis, randomDelayInMillis);

    }

    StreamObserver<RateRequest> stream(StreamObserver<Rate> responseObserver) {
        observers.add(responseObserver);

        return new StreamObserver<RateRequest>() {
            @Override
            public void onNext(RateRequest rateRequest) {
                responseObserver.onNext(currencyProvider.getRate(rateRequest.getFrom(), rateRequest.getTo(), rateRequest.getBank().getName()));
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

    private class GenerateValueTask extends TimerTask {
        @Override
        public void run() {
            Rate value = currencyProvider.getRate(Currency.USD, Currency.USD, "UKRSIB");
            observers.forEach(o -> o.onNext(value));
        }
    }


}
