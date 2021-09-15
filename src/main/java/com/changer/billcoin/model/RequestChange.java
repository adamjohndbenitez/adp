package com.changer.billcoin.model;

import com.changer.billcoin.datasets.Coin;

import java.util.Map;

public class RequestChange {
    private Map<Coin, Integer> change;

    public RequestChange(Map<Coin, Integer>  change) {
        this.change = change;
    }

    public RequestChange() {
    }

    public Map<Coin, Integer> getChange() {
        return change;
    }

    public void setChange(Map<Coin, Integer>  change) {
        this.change = change;
    }
}
