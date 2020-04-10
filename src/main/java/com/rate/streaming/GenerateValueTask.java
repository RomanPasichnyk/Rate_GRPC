package com.rate.streaming;

import com.rate.Bank;
import com.rate.Currency;
import com.rate.Rate;
import com.rate.RateRequest;
import com.rate.RateResponse;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GenerateValueTask implements Runnable {

    private Currency from;

    private Currency to;

    private String bankName;

    private final Set<StreamObserver<RateResponse>> observers = ConcurrentHashMap.newKeySet();

    private boolean isScheduled = false;

    private CurrencyProvider currencyProvider = new CurrencyProvider();

    private final Set<RateRequest> rateRequests = new HashSet<>();

    public GenerateValueTask(Currency from, Currency to, String bankName) {
        this.from = from;
        this.to = to;
        this.bankName = bankName;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void addObserverIfAbsent(StreamObserver<RateResponse> responseObserver) {
        observers.add(responseObserver);
    }

    @Override
    public void run() {
        do {
            if (!Thread.interrupted()) {
                for (RateRequest rateRequest : rateRequests) {
                    Rate value = currencyProvider.getRate(rateRequest.getFrom(), rateRequest.getTo(), rateRequest.getBank().getName());
                    RateResponse rateResponse = RateResponse.newBuilder().setCurrencies(value).setBank(Bank.newBuilder().setName(rateRequest.getBank().getName())).build();
                    observers.forEach(o -> o.onNext(rateResponse));
                }
                try {
                    Thread.sleep(1000 + new Random().nextInt(3000));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                return;
            }
        }
        while (true);
    }

    public void addRequest(RateRequest rateRequest) {
        rateRequests.add(rateRequest);
    }


}
