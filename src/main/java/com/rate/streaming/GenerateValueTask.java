package com.rate.streaming;

import com.rate.Currency;
import com.rate.Rate;
import com.rate.providers.CurrencyProvider;
import io.grpc.stub.StreamObserver;

import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class GenerateValueTask extends TimerTask {

    private Currency from;
    private Currency to;
    private String bankName;
    private final Set<StreamObserver<Rate>> observers = ConcurrentHashMap.newKeySet();
    private boolean isScheduled = false;

    private CurrencyProvider currencyProvider = new CurrencyProvider();

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

    public void addOberverIfAbsent(StreamObserver<Rate> responseObserver) {
        observers.add(responseObserver);
    }

    @Override
    public void run() {
        Rate value = currencyProvider.getRate(from, to, bankName);
        observers.forEach(o -> o.onNext(value));
    }

    public void init () {
        if (!isScheduled) {
            long randomDelayInMillis = TimeUnit.SECONDS.toMillis(1 + new Random().nextInt(4));
            Timer timer = new Timer();
            timer.schedule(new GenerateValueTask(from, to, bankName), randomDelayInMillis, randomDelayInMillis);
            isScheduled = true;
        }
    }
}
