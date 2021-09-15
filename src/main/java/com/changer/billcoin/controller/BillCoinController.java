package com.changer.billcoin.controller;

import com.changer.billcoin.datasets.Bill;
import com.changer.billcoin.datasets.Coin;
import com.changer.billcoin.exception.InvalidBillAmountException;
import com.changer.billcoin.model.RequestChange;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class BillCoinController {

    private final AtomicInteger counter = new AtomicInteger();
    private static Map<Coin, Integer> usersChange = new EnumMap<>(Coin.class);

    private static Map<Coin, Integer> coinMap = new EnumMap<>(Coin.class);
    private static final Integer INIT_PIECES = 100;

    static {
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

    // TODO: I think this will be a PostMapping instead of GetMapping ?
    @GetMapping("/refillCoins")
    public RequestChange refillCoins(@RequestParam(required = false, defaultValue = "0") Integer one,
                                     @RequestParam(required = false, defaultValue = "0") Integer five,
                                     @RequestParam(required = false, defaultValue = "0") Integer ten,
                                     @RequestParam(required = false, defaultValue = "0") Integer twentyFive
    ) {
        coinMap.put(Coin.ONE, one);
        coinMap.put(Coin.FIVE, five);
        coinMap.put(Coin.TEN, ten);
        coinMap.put(Coin.TWENTY_FIVE, twentyFive);
        return new RequestChange(coinMap);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/change")
    public RequestChange requestChange(
            @RequestParam(required = false, defaultValue = "0") Integer bill,
            @RequestParam(required = false, defaultValue = "0") Boolean takeHugeFirst
    ) throws InvalidBillAmountException {

        System.out.println("==== a User to request change for a given bill ====");
        // Validation first before computation
        int found = 0;
        for (Bill b : Bill.values()) {
            // Should throw exception instead?
            if (bill.equals(b.getValue())) found++;
            // Nothing found, Bill is invalid.
            if (found == 0) {
                throw new InvalidBillAmountException("Bill is invalid. Available bills are (1, 2, 5, 10, 20, 50, 100).");
            }
        }

        // TODO: To integrate with recursive function.
        if (takeHugeFirst) {
            Arrays.sort(Coin.values());
        } else {
            Arrays.sort(Coin.values(), Collections.reverseOrder());
        }

        change(bill);
        return new RequestChange(usersChange);
    }

    private void change(double nBill) {
        // Change should be made by utilizing the least amount of coins
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
