package com.changer.billcoin.enums;

/**
 * Available coins are (0.01, 0.05, 0.10, 0.25)
 */
public enum Coin {
    ONE          (0.01),
    FIVE         (0.05),
    TEN          (0.10),
    TWENTY_FIVE  (0.25);

    private final Double value;

    Coin(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
