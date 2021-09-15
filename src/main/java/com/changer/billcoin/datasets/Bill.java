package com.changer.billcoin.datasets;

/**
 * Available bills are (1, 2, 5, 10, 20, 50, 100)
 */
public enum Bill {
    ONE     (1),
    TWO     (2),
    FIVE    (5),
    TEN     (10),
    TWENTY  (20),
    FIFTY   (50),
    HUNDRED (100);

    private final int value;

    Bill(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
