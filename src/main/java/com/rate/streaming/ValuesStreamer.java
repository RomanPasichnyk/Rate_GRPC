package com.rate.streaming;

import io.grpc.stub.StreamObserver;

import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ValuesStreamer<ResponseType> {

    private final Set<StreamObserver<ResponseType>> observers = ConcurrentHashMap.newKeySet();
    private final Supplier<ResponseType> valueProvider;

    public ValuesStreamer(Supplier<ResponseType> valueProvider) {
        this.valueProvider = valueProvider;

        long randomDelayInMillis = TimeUnit.SECONDS.toMillis(1 + new Random().nextInt(4));
        Timer timer = new Timer();
        timer.schedule(new ValuesStreamer.GenerateValueTask(), randomDelayInMillis, randomDelayInMillis);
    }

    <RequestType> StreamObserver<RequestType> stream(StreamObserver<ResponseType> responseObserver) {

        observers.add(responseObserver);

        return new StreamObserver<RequestType>() {
            @Override
            public void onNext(RequestType requestType) {
                responseObserver.onNext(valueProvider.get());
            }

            @Override
            public void onError(Throwable throwable) {
                boolean removed = observers.remove(responseObserver);
                if (removed) {
                    responseObserver.onError(throwable);
                }
            }

            @Override
            public void onCompleted() {
                boolean removed = observers.remove(responseObserver);
                if (removed) {
                    responseObserver.onCompleted();
                }
            }
        };

    }


    private class GenerateValueTask extends TimerTask {
        @Override
        public void run() {
            ResponseType value = valueProvider.get();
            observers.forEach(o -> o.onNext(value));
        }
    }


}
