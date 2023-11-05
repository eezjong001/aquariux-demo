package com.aquariuxdemo.h2database.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String TickerType;
    private String symbol;
    private float aggregatedPrice;
    private float bidPrice;
    private float bidQty;
    private float askPrice;
    private float askQty;
    private float open;
    private float high;
    private float close;
    private float amount;
    private float vol;
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTickerType() {
        return TickerType;
    }

    public void setTickerType(String TickerType) {
        this.TickerType = TickerType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public float getAggregatedPrice() {
        return aggregatedPrice;
    }

    public void setAggregatedPrice(float aggregatedPrice) {
        this.aggregatedPrice = aggregatedPrice;
    }
    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public float getBidQty() {
        return bidQty;
    }

    public void setBidQty(float bidQty) {
        this.bidQty = bidQty;
    }

    public float getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(float askPrice) {
        this.askPrice = askPrice;
    }

    public float getAskQty() {
        return askQty;
    }

    public void setAskQty(float askQty) {
        this.askQty = askQty;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getVol() {
        return vol;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
