package com.rate.streaming;

import com.rate.Currency;
import com.rate.Rate;

public interface BankGrabber {

    Rate getRate(Currency from, Currency to);

}
