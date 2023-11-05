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

    private String transactionType;
    private LocalDateTime timestamp;

    // Add getters and setters for each field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public long getUserTransactionId() {
        return userTransactionId;
    }

    public void setUserTransactionId(long userTransactionId) {
        this.userTransactionId = userTransactionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
