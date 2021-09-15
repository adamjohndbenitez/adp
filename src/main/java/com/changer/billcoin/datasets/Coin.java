package com.changer.billcoin.datasets;

/**
 * Available coins are (0.01, 0.05, 0.10, 0.25)
 */
public enum Coin {
    ONE  (0.01),
    FIVE  (0.05),
    TEN (0.10),
    TWENTY_FIVE  (0.25);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }


}
