package com.changer.billcoin.controller;

import com.changer.billcoin.datasets.Bill;
import com.changer.billcoin.datasets.Coin;
import com.changer.billcoin.model.RequestChange;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class BillCoinController {

    private final AtomicInteger counter = new AtomicInteger();
    private static Map<Coin, Integer> usersChange = new EnumMap<>(Coin.class);

    private static Map<Coin, Integer> coinMap;
    private static final Integer INIT_PIECES = 100;

    static {
        coinMap = new EnumMap<>(Coin.class);
        // Coin value, Coin pieces, -- Coin amount
        coinMap.put(Coin.ONE, INIT_PIECES);
        coinMap.put(Coin.FIVE, INIT_PIECES);
        coinMap.put(Coin.TEN, INIT_PIECES);
        coinMap.put(Coin.TWENTY_FIVE, INIT_PIECES);
    }

    @GetMapping("/checkcoins")
    public RequestChange checkcoins() {
        return new RequestChange(coinMap);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/change")
    public RequestChange requestChange(@RequestParam(required = false, defaultValue = "0") Integer bill) {

        System.out.println("==== a User to request change for a given bill ====");
        // Validation first before computation
        int found = 0;
        for (Bill b : Bill.values()) {
            // Should throw exception instead?
            if (bill.equals(b.getValue())) found++;
            // Nothing found, Bill is invalid.
            if (found == 0) {
                System.out.println("Bill is invalid. Available bills are (1, 2, 5, 10, 20, 50, 100)");
                return new RequestChange();
            }
        }

        change(bill);

        return new RequestChange(usersChange);
    }

    private void change(double nBill) {
        recursiveCondition(nBill, Coin.ONE);
        recursiveCondition(nBill, Coin.FIVE);
        recursiveCondition(nBill, Coin.TEN);
        recursiveCondition(nBill, Coin.TWENTY_FIVE);
    }

    private void recursiveCondition(double nBill, Coin coin) {
        if (nBill < coin.getValue()) return;
        if (coinMap.get(coin) != 0 && nBill > coin.getValue()) {
            coinMap.put(coin, coinMap.get(coin) - 1);
            usersChange.put(coin, counter.incrementAndGet());
            change(nBill - coin.getValue());
        } else {
            counter.set(0);
        }
    }
}
