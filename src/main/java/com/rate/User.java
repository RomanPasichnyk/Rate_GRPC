package com.rate;

import java.util.HashMap;
import java.util.Map;

public class User {

    private float uah = 0;

    private final Map<Currency, Float> currencies = new HashMap<>();

    public User(float uah) {
        initCurrencies();
        this.uah = uah;
    }

    public Map<Currency, Float> getCurrencies() {
        return currencies;
    }

    public void initCurrencies() {
        currencies.put(Currency.EUR, 0f);
        currencies.put(Currency.USD, 0f);
        currencies.put(Currency.RUB, 0f);
    }

    public float getUah() {
        return uah;
    }

    public void setUah(float uah) {
        this.uah = uah;
    }

    public void decreaseUah(float amountOfDecrease) {
        uah -= amountOfDecrease;
    }

    public void increasedCurrency(Currency currencyType, float amountOfCurrency) {
        currencies.put(currencyType, currencies.get(currencyType) + amountOfCurrency);
    }
}
