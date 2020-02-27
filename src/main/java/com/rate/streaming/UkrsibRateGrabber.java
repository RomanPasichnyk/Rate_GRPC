package com.rate.streaming;

import com.rate.Currency;

public class UkrsibRateGrabber implements BankDecorator {

    public static final String UKRSIB_BANK_NAME = "Ukrsib";
    private RateGrabber rateGrabber;

    public UkrsibRateGrabber(RateGrabber rateGrabber) {
        this.rateGrabber = rateGrabber;
    }

    @Override
    public void getRate(Currency from, Currency to) {
        rateGrabber.getRate(UKRSIB_BANK_NAME, from, to);
    }

}
