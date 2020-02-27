package com.rate.providers;

import com.rate.Currency;
import com.rate.Rate;
import com.rate.RateResponse;

import java.util.HashMap;
import java.util.Map;

public class CurrencyProvider {

    Map<Currency, Float> rateToUSD = new HashMap<>();

    public Rate getRate(Currency fromCurrency, Currency toCurrency) {
        float value = rateToUSD.get(toCurrency) / rateToUSD.get(fromCurrency);
        return Rate.newBuilder().setFromCurrency(fromCurrency).setToCurrency(toCurrency).setValue(value).build();
    }

    public void initRateToUSD() {
        rateToUSD.put(Currency.EUR, 0.92f);
        rateToUSD.put(Currency.RUB, 65.52f);
        rateToUSD.put(Currency.UAH, 24.57f);
        rateToUSD.put(Currency.USD, 1f);
    }

}
