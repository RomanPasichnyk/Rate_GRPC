package com.rate.providers;

import com.rate.Bank;
import com.rate.Currency;
import com.rate.Rate;
import com.rate.RateResponse;
import com.rate.streaming.BankGrabber;

import java.util.HashMap;
import java.util.Map;

public class CurrencyProvider {

    private final Map<Currency, Float> rateToUSD = new HashMap<>();

    public CurrencyProvider() {
        initRateToUSD();
    }

    public Rate getRate(Currency from, Currency to, String bankName) {

        float value = rateToUSD.get(to) / rateToUSD.get(from);
        if (bankName.equals("UKRSIB")) {
            value += value * 0.15f;
        } else if (bankName.equals("PRIVAT")) {
            value += value * 0.18f;
        }

        return Rate.newBuilder().setFrom(from).setTo(to).setValue(value).build();
    }

    public void initRateToUSD() {
        rateToUSD.put(Currency.EUR, 0.92f);
        rateToUSD.put(Currency.RUB, 65.52f);
        rateToUSD.put(Currency.UAH, 24.57f);
        rateToUSD.put(Currency.USD, 1f);
    }

}
