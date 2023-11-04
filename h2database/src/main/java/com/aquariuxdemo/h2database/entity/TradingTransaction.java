package com.aquariuxdemo.h2database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TradingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol; // e.g., "ETHUSDT", "BTCUSDT"
    private float price;
    private float quantity;
    private long userTransactionId; //primary key of the User for tracking/history retrieval
    private LocalDateTime timestamp;
}
