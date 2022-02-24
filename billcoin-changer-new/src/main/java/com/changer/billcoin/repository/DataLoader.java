package com.changer.billcoin.repository;

import com.changer.billcoin.entity.RequestChange;
import com.changer.billcoin.enums.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.EnumMap;
import java.util.Map;

@Configurable
public class DataLoader {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);
    private final Double INIT_PIECES = 100.00;

    @Bean
    public CommandLineRunner initialLoad(RequestChangeRepository repo) {
        return args -> {
            Map<Coin, Double> coinMap = new EnumMap<>(Coin.class);
            {
                coinMap.put(Coin.ONE, INIT_PIECES);
                coinMap.put(Coin.FIVE, INIT_PIECES);
                coinMap.put(Coin.TEN, INIT_PIECES);
                coinMap.put(Coin.TWENTY_FIVE, INIT_PIECES);
            }
            log.info("Preloading " + repo.save(new RequestChange(coinMap)));
        };
    }
}
