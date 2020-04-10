package com.rate.providers;

import com.rate.Currency;
import com.rate.Rate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CurrencyProvider {

    private final Map<Currency, Float> rateToUSD = new HashMap<>();

    private final Map<String, Float> bankRates = new HashMap<>();

    public CurrencyProvider() {
        initRateToUSD();
    }

    public Rate getRate(Currency from, Currency to, String bankName) {

        Random random = new Random();

        float value = rateToUSD.get(to) / rateToUSD.get(from);
        initBankRates(value);
        value += bankRates.get(bankName);

        return Rate.newBuilder().setFrom(from).setTo(to).setValue(value).build();
    }

    public void initBankRates(float value) {
        bankRates.put("UKRSIB", value * new Random().nextFloat());
        bankRates.put("PRIVAT", value * new Random().nextFloat());
    }

    public void initRateToUSD() {
        rateToUSD.put(Currency.EUR, 0.92f);
        rateToUSD.put(Currency.RUB, 65.52f);
        rateToUSD.put(Currency.UAH, 24.57f);
        rateToUSD.put(Currency.USD, 1f);
    }

}
