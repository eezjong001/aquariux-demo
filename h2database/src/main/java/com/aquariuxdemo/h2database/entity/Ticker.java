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
}
