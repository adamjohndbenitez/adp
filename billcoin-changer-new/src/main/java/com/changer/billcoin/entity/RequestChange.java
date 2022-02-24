package com.changer.billcoin.entity;

import com.changer.billcoin.enums.Coin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class RequestChange {
    private @Id @GeneratedValue Long id;
    private Map<Coin, Double> change;

    public RequestChange(Map<Coin, Double> change) {
        this.change = change;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Coin, Double> getChange() {
        return change;
    }

    public void setChange(Map<Coin, Double> change) {
        this.change = change;
    }
}
