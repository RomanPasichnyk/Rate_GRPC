package com.rate.streaming;

import com.rate.Currency;

public interface BankDecorator {

    void getRate(Currency from, Currency to);

}
