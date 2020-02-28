package com.rate.streaming;

import com.google.common.collect.ImmutableList;
import com.rate.*;
import com.rate.RateResponse.Builder;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;

import static java.util.Arrays.asList;

public class RateStreamingService extends RateStreamingServiceGrpc.RateStreamingServiceImplBase {

    private final UkrsibStreamingService ukrsibStreamingService;

    public RateStreamingService(UkrsibStreamingService ukrsibStreamingService) {
        this.ukrsibStreamingService = ukrsibStreamingService;
    }

    @Override
    public StreamObserver<RateRequest> observe(StreamObserver<RateResponse> responseObserver) {

        System.err.println(2);
        AutoClosableLock lock = new AutoClosableLock(new ReentrantLock());

//        StreamObserver<RateRequest> ukrSibClientStream =
//                UkrsibRateGrabber.observe(newStreamObserver(responseObserver, lock, Builder::addCurrencies));

        StreamObserver<RateRequest> ukrsibClientStream = ukrsibStreamingService.observe(newStreamObserver(responseObserver, lock, Builder::addCurrencies));



        List<StreamObserver<RateRequest>> clientsStreams =
                ImmutableList.copyOf(asList(ukrsibClientStream));


        return new StreamObserver<RateRequest>() {
            @Override
            public void onNext(RateRequest rateRequest) {
                System.err.println(5);
                clientsStreams.forEach(s -> s.onNext(rateRequest));
            }

            @Override
            public void onError(Throwable throwable) {
                clientsStreams.forEach(s -> s.onError(throwable));
            }

            @Override
            public void onCompleted() {
                clientsStreams.forEach(StreamObserver::onCompleted);
            }
        };
    }

    private static <T> StreamObserver<T> newStreamObserver(
            StreamObserver<RateResponse> responseObserver,
            AutoClosableLock observerLock,
            BiFunction<Builder, T, Builder> convert) {
        return new StreamObserver<T>() {
            @Override
            public void onNext(T value) {
                RateResponse response = convert.apply(RateResponse.newBuilder(), value).build();
                try (AutoClosableLock ignored = observerLock.lock()) {
                    responseObserver.onNext(response);
                    System.out.println(response);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                try (AutoClosableLock ignored = observerLock.lock()) {
                    responseObserver.onError(throwable);
                }
            }

            @Override
            public void onCompleted() {
                try (AutoClosableLock ignored = observerLock.lock()) {
                    responseObserver.onCompleted();
                }
            }
        };
    }

    private final class AutoClosableLock implements AutoCloseable {
        private final Lock lock;

        private AutoClosableLock(Lock lock) {
            this.lock = lock;
        }

        AutoClosableLock lock() {
            lock.lock();
            return this;
        }

        @Override
        public void close() {
            lock.unlock();
        }
    }


}
