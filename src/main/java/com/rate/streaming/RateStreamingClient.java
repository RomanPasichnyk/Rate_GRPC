package com.rate.streaming;

import com.rate.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class RateStreamingClient {

    private static final int AMOUNT_OF_CURRENCY = 10;

    private static final Map<Currency, Float> buyCurrencyMinLimits = new HashMap<>();

    private static User user = new User(10000);

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 8090).usePlaintext(true).build();
        RateStreamingServiceGrpc.RateStreamingServiceStub stub = RateStreamingServiceGrpc.newStub(channel);

        initMinValues();

        Semaphore exitSemaphore = new Semaphore(0);
        System.err.println(1);
        StreamObserver<RateRequest> requestStream = stub.observe(new StreamObserver<RateResponse>() {
            @Override
            public void onNext(RateResponse rateResponse) {
                System.err.println(6);
//                printUserMoney();
//                buyMoneyByUah(rateResponse);
                System.out.printf("Rate: %s%n", rateResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                exitSemaphore.release();
            }

            @Override
            public void onCompleted() {
                System.out.println("Call completed");
                exitSemaphore.release();
            }
        });
        RateRequest request = RateRequest.newBuilder()
                .setBank(Bank.newBuilder().setName("UKRSIB")).setFrom(Currency.EUR).setTo(Currency.UAH).build();

//        System.out.println(request);
        System.err.println(3);
        requestStream.onNext(request);

//        request = RateRequest.newBuilder().setBank(Bank.newBuilder().setName("PRIVAT")).setFrom(Currency.EUR).setTo(Currency.UAH).build();
//        requestStream.onNext(request);

        System.err.println(4);
        exitSemaphore.acquire();

    }

    public static void initMinValues() {
        buyCurrencyMinLimits.put(Currency.EUR, 28f);
        buyCurrencyMinLimits.put(Currency.USD, 26f);
        buyCurrencyMinLimits.put(Currency.RUB, 0.45f);
    }

//    public static void buyMoneyByUah(RateResponse rateResponse) {
//        float currentPrice = rateResponse.getCurrencies(0).getValue() * AMOUNT_OF_CURRENCY;
//        Currency currencyType = rateResponse.getCurrencies(0).getToCurrency();
//
//        if (user.getUah() >= currentPrice && rateResponse.getCurrencies(0).getValue() <= buyCurrencyMinLimits.get(currencyType)) {
//            user.increasedCurrency(currencyType, AMOUNT_OF_CURRENCY);
//            user.decreaseUah(currentPrice);
//        }
//    }
//
//    public static void printUserMoney() {
//        System.out.println("User UAH: " + user.getUah());
//        System.out.println("User USD: " + user.getCurrencies().get(Currency.USD));
//        System.out.println("User EUR: " + user.getCurrencies().get(Currency.EUR));
//        System.out.println("User RUB: " + user.getCurrencies().get(Currency.RUB));
//    }


}
