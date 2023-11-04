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
}
