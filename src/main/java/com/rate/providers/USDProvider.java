package com.rate.providers;

import com.rate.Currency;
import com.rate.Rate;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.function.Supplier;

public class USDProvider implements Supplier<Rate> {

    private final Random random = new Random();

    @Override
    public Rate get() {
        return Rate.newBuilder().setValue(random.nextFloat() * 5 + 25).setToCurrency(Currency.USD).build();


//        UkrsibRateGrabber ukrsibRateGrabber = new UkrsibRateGrabber(new RateGrabber());
//        float usdRate = ukrsibRateGrabber.getRate("USD", "EUR");



    }

}


