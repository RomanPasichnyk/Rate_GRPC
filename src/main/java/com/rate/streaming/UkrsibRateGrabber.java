package com.rate.streaming;

import com.rate.Currency;
import com.rate.Rate;

public class UkrsibRateGrabber implements BankGrabber {

    public static final String UKRSIB_BANK_NAME = "Ukrsib";
    private RateGrabber rateGrabber;

    public UkrsibRateGrabber(RateGrabber rateGrabber) {
        this.rateGrabber = rateGrabber;
    }

    @Override
    public Rate getRate(Currency from, Currency to) {
        return rateGrabber.getRate(UKRSIB_BANK_NAME, from, to);
    }

}
