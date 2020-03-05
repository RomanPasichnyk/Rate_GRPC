package com.rate.streaming;

import com.rate.*;
import com.rate.Currency;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GenerateValueTask extends TimerTask {

    private Currency from;
    private Currency to;
    private String bankName;
    private final Set<StreamObserver<Rate>> observers = ConcurrentHashMap.newKeySet();
    private boolean isScheduled = false;

    private CurrencyProvider currencyProvider = new CurrencyProvider();
    private final Set<RateRequest> rateRequests = new HashSet<>();

    public GenerateValueTask(Currency from, Currency to, String bankName) {
        this.from = from;
        this.to = to;
        this.bankName = bankName;
//        rateRequests.add(RateRequest.newBuilder().setFrom(from).setTo(to).setBank(Bank.newBuilder().setName(bankName)).build());
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

    public void addObserverIfAbsent(StreamObserver<Rate> responseObserver) {
        observers.add(responseObserver);
    }

    @Override
    public void run() {
        for (RateRequest rateRequest : rateRequests) {
            System.out.println("Bank name TEST TEST " + rateRequest.getBank().getName());
            Rate value = currencyProvider.getRate(rateRequest.getFrom(), rateRequest.getTo(), rateRequest.getBank().getName());
            observers.forEach(o -> o.onNext(value));
        }
    }

    public void init() {
        if (!isScheduled) {
            long randomDelayInMillis = TimeUnit.SECONDS.toMillis(1 + new Random().nextInt(4));
            Timer timer = new Timer();
            timer.schedule(this, randomDelayInMillis, randomDelayInMillis);
            isScheduled = true;
        }
    }

    public void addRequest(RateRequest rateRequest) {
        rateRequests.add(rateRequest);
    }


}
