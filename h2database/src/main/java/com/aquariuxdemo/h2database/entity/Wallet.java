package com.aquariuxdemo.h2database.entity;

import jakarta.persistence.*;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userWalletId; //primary key of User for linking
    private float balance;
    private String currency; // match ticker e.g. ETC/BTC -> match to tradingtrans symbol

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserWalletId() {
        return userWalletId;
    }

    public void setUserWalletId(Long userWalletId) {
        this.userWalletId = userWalletId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
