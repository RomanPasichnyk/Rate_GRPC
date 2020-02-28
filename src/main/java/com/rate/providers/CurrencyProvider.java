package com.rate.providers;

import com.rate.Currency;
import com.rate.Rate;
import com.rate.RateResponse;

import java.util.HashMap;
import java.util.Map;

public class CurrencyProvider {

    private final Map<Currency, Float> rateToUSD = new HashMap<>();

    public CurrencyProvider() {
        initRateToUSD();
    }

    public Rate getRate(Currency from, Currency to) {
        float value = rateToUSD.get(to) / rateToUSD.get(from);
        return Rate.newBuilder().setFrom(from).setTo(to).setValue(value).build();
    }

    public void initRateToUSD() {
        rateToUSD.put(Currency.EUR, 0.92f);
        rateToUSD.put(Currency.RUB, 65.52f);
        rateToUSD.put(Currency.UAH, 24.57f);
        rateToUSD.put(Currency.USD, 1f);
    }

}
