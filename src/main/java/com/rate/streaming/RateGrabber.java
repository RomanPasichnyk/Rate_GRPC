package com.rate.streaming;

import com.rate.Currency;
import com.rate.Rate;
import com.rate.providers.CurrencyProvider;

public class RateGrabber {

    CurrencyProvider currencyProvider;

    public RateGrabber(CurrencyProvider currencyProvider) {
        this.currencyProvider = currencyProvider;
    }

    public Rate getRate(String bankName, Currency from, Currency to) {
        float rate = currencyProvider.getRate(from, to).getValue();
        return Rate.newBuilder().setFromCurrency(from).setToCurrency(to).setValue(rate).build();
    }

}
