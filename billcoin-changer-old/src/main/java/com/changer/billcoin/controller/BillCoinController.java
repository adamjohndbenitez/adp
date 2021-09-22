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

    @GetMapping("/refillCoins")
    public RequestChange refillCoins(@RequestParam(required = false, defaultValue = "0") Integer one,
                                     @RequestParam(required = false, defaultValue = "0") Integer five,
                                     @RequestParam(required = false, defaultValue = "0") Integer ten,
                                     @RequestParam(required = false, defaultValue = "0") Integer twentyFive
    ) {
        // FIXME: Add value to existing value. (resolved)
        coinMap.put(Coin.ONE, coinMap.get(Coin.ONE) + one);
        coinMap.put(Coin.FIVE, coinMap.get(Coin.FIVE) + five);
        coinMap.put(Coin.TEN, coinMap.get(Coin.TEN) + ten);
        coinMap.put(Coin.TWENTY_FIVE, coinMap.get(Coin.TWENTY_FIVE) + twentyFive);
        return new RequestChange(coinMap);
    }

    // TODO: I think this will be a PostMapping (Put - Update) instead of GetMapping ?
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
                // 400
            }
        }

        // TODO: To integrate with recursive function.

        change(bill);
        return new RequestChange(usersChange);
    }

    private double change(double bill) {
        // Change should be made by utilizing the least amount of coins
        double nBill = recursiveCondition(bill, Coin.TWENTY_FIVE); //least amount of coins.

        if (nBill != 0.0) nBill = recursiveCondition(bill, Coin.TEN);
        if (nBill != 0.0) nBill = recursiveCondition(bill, Coin.FIVE);
        if (nBill != 0.0) nBill = recursiveCondition(bill, Coin.ONE);
        // FIXME: Need to add some breaking condition to stop recursive call
        //        when bill is almost empty.
        return nBill;
    }

    private double recursiveCondition(double nBill, Coin coin) {
        if (nBill < coin.getValue()) return nBill;
        if (coinMap.get(coin) != 0 && nBill >= coin.getValue()) {
            coinMap.put(coin, coinMap.get(coin) - 1);
            usersChange.put(coin, counter.incrementAndGet());
            double retVal = nBill - coin.getValue();
            return change(retVal);
        } else {
            counter.set(0);
        }
        return nBill;
    }
}
