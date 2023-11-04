package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.TradingTransaction;
import com.aquariuxdemo.h2database.repository.TradingTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TradingTransactionController {
    @Autowired
    private TradingTransactionRepository transactionRepository;
    // Endpoint for buying crypto
    @PostMapping("/buy")
    public ResponseEntity<?> buyCrypto(@RequestBody TradingTransaction buyTransaction) {
        //pseudo till implemented
        //check if user has enough currency to buy
        //skip auth does not exist
        //calculate price*quantity and then update user balance
        //return success
        transactionRepository.save(buyTransaction);
        return new ResponseEntity<>("Buy transaction created successfully", HttpStatus.CREATED);
    }

    // Endpoint for selling crypto
    @PostMapping("/sell")
    public ResponseEntity<?> sellCrypto(@RequestBody TradingTransaction sellTransaction) {
        //pseudo till implemented
        //check if user has enough crypto to sell
        //skip auth does not exist
        //calculate price*quantity and then update user balance
        //return success
        transactionRepository.save(sellTransaction);
        return new ResponseEntity<>("Sell transaction successful", HttpStatus.CREATED);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TradingTransaction>> getUserTradingHistory(@PathVariable Long userId) {
         //pseudo till implemented -> just do simple retrieve versus userID against transactions

        List<TradingTransaction> history = null; // Retrieve the trading history from the repository

        return new ResponseEntity<>(history, HttpStatus.OK);
    }

}