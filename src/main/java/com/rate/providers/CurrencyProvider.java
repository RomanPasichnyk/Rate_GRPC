package com.rate.providers;

import com.rate.Currency;
import com.rate.Rate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CurrencyProvider {

    private static final float RATE_EUR = 0.92f;

    private static final float RATE_RUB = 65.52f;

    private static final float RATE_UAH = 24.57f;

    private static final float COEFFICIENT = 0.3f;

    private static final float LOW_VALUE = 0.001f;

    private final Map<Currency, Float> rateToUsd = new HashMap<>();

    private final Map<String, Float> bankRates = new HashMap<>();

    public CurrencyProvider() {
        initRateToUsd();
    }

    /**
     * Method return rate. Param from request.
     *
     * @param from     - convert from currency type.
     * @param to       - convert to currency type.
     * @param bankName - bank name.
     * @return - rate.
     */
    public Rate getRate(Currency from, Currency to, String bankName) {
        float value = rateToUsd.get(to) / rateToUsd.get(from);
        initBankRates(value);
        value += bankRates.get(bankName);
        return Rate.newBuilder().setFrom(from).setTo(to).setValue(value).build();
    }

    public void initBankRates(float value) {
        bankRates.put("UKRSIB", value * generateRandomValue());
        bankRates.put("PRIVAT", value * generateRandomValue());
    }

    /**
     * Generate value between 0.001 and 0.3.
     *
     * @return some value.
     */
    public float generateRandomValue() {
        float value = new Random().nextFloat() + LOW_VALUE;
        if (value > COEFFICIENT) {
            value = COEFFICIENT;
        }
        return value;
    }

    /**
     * Initialize rate to USD.
     */
    public void initRateToUsd() {
        rateToUsd.put(Currency.EUR, RATE_EUR);
        rateToUsd.put(Currency.RUB, RATE_RUB);
        rateToUsd.put(Currency.UAH, RATE_UAH);
        rateToUsd.put(Currency.USD, 1f);
    }

}
