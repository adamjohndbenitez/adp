package com.changer.billcoin.datasets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {
    @Test
    void getValue() {
        assertEquals(0.01, Coin.ONE.getValue());
    }

    @Test
    void values() {
        for (Bill b :
                Bill.values()) {
            assertNotNull(b.getValue());
        }
    }

    @Test
    void valueOf() {
        assertEquals(Bill.ONE, Bill.valueOf(Bill.ONE.name()));
    }
}