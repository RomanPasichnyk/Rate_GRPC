package com.rate.streaming;

import com.rate.RateRequest;
import com.rate.RateResponse;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateValuesStreamer {

    private final CurrencyProvider currencyProvider;

    private GenerateValueTask generateValueTask;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public RateValuesStreamer(CurrencyProvider currencyProvider) {
        this.currencyProvider = currencyProvider;
    }

    StreamObserver<RateRequest> stream(StreamObserver<RateResponse> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(RateRequest rateRequest) {
                initGenerateValueTask(rateRequest, responseObserver);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

    private void initGenerateValueTask(RateRequest rateRequest, StreamObserver<RateResponse> responseObserver) {
        if (generateValueTask == null) {
            generateValueTask = new GenerateValueTask(rateRequest.getFrom(), rateRequest.getTo(), rateRequest.getBank().getName());
            executorService.submit(generateValueTask);
        }
        else {
            generateValueTask.setBankName(rateRequest.getBank().getName());
            generateValueTask.setFrom(rateRequest.getFrom());
            generateValueTask.setTo(rateRequest.getTo());
        }
        generateValueTask.addRequest(rateRequest);
        generateValueTask.addObserverIfAbsent(responseObserver);
    }
}
