package com.changer.billcoin.model;

public class Request {
    private double change;

    public Request(double change) {
        this.change = change;
    }

    public Request() {
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}
