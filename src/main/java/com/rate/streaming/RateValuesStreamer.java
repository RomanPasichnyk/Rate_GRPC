package com.rate.streaming;

import com.rate.*;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

public class RateValuesStreamer {

//    private final Set<StreamObserver<Rate>> observers = ConcurrentHashMap.newKeySet();
    private final CurrencyProvider currencyProvider;
    private GenerateValueTask generateValueTask;

    public RateValuesStreamer(CurrencyProvider currencyProvider) {
        this.currencyProvider = currencyProvider;

    }

    StreamObserver<RateRequest> stream(StreamObserver<Rate> responseObserver) {
//        observers.add(responseObserver);

        return new StreamObserver<RateRequest>() {
            @Override
            public void onNext(RateRequest rateRequest) {
                if (generateValueTask == null) {
                    generateValueTask = new GenerateValueTask(rateRequest.getFrom(), rateRequest.getTo(), rateRequest.getBank().getName());
                    generateValueTask.init();
                }
                else {
                    generateValueTask.setBankName(rateRequest.getBank().getName());
                    generateValueTask.setFrom(rateRequest.getFrom());
                    generateValueTask.setTo(rateRequest.getTo());
                }

                generateValueTask.addObserverIfAbsent(responseObserver);
//                responseObserver.onNext(currencyProvider.getRate(rateRequest.getFrom(), rateRequest.getTo(), rateRequest.getBank().getName()));
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

//    private class GenerateValueTask extends TimerTask {
//        @Override
//        public void run() {
//            Rate value = currencyProvider.getRate(Currency.USD, Currency.USD, "UKRSIB");
//            observers.forEach(o -> o.onNext(value));
//        }
//    }


}
