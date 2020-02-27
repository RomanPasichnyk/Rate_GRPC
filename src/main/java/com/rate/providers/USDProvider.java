package com.rate.providers;

import com.rate.Currency;
import com.rate.Rate;

import java.util.Random;
import java.util.function.Supplier;

public class USDProvider implements Supplier<Rate> {

    private final Random random = new Random();

    @Override
    public Rate get() {
        return Rate.newBuilder().setValue(random.nextFloat() * 5 + 25).setCurrency(Currency.USD).build();
    }

}


