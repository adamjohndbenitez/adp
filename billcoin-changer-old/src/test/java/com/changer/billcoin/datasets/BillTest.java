package com.changer.billcoin.datasets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {
    @Test
    void getValue() {
        assertEquals(1, Bill.ONE.getValue());
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