package com.changer.billcoin.controller;

import com.changer.billcoin.Greeting;
import com.changer.billcoin.datasets.Bill;
import com.changer.billcoin.datasets.Coin;
import com.changer.billcoin.model.Request;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BillCoinController {
//    private static final String template = "Hello, %s!";
//
    private final AtomicLong counter = new AtomicLong();
//
//    //    @CrossOrigin(origins = "http://rest-service.guides.spring.io")
////	@CrossOrigin(origins = "http://localhost:9000")
//    @CrossOrigin(origins = "http://localhost:8080")
//    @GetMapping("/change")
//    public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
//        System.out.println("==== get greeting ====");
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }
//
//    @GetMapping("/greeting-javaconfig")
//    public Greeting greetingWithJavaconfig(@RequestParam(required = false, defaultValue = "World") String name) {
//        System.out.println("==== in greeting ====");
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }

    private static Map<Coin, Double> coinMap = new EnumMap<>(Coin.class);

    public BillCoinController() {
        // Coin value, Coin pieces
        coinMap.put(Coin.ONE, Coin.ONE.getValue() * 100.0);
        coinMap.put(Coin.FIVE, Coin.FIVE.getValue() * 100.0);
        coinMap.put(Coin.TEN, Coin.TEN.getValue() * 100.0);
        coinMap.put(Coin.TWENTY_FIVE, Coin.TWENTY_FIVE.getValue() * 100.0);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/change")
    public Request request(@RequestParam(required = false, defaultValue = "No Value") int bill) {
        System.out.println("==== a User to request change for a given bill ====");
        // Validation first before computation
        return new Request(bill);
    }
}
