package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.TradingTransaction;
import com.aquariuxdemo.h2database.entity.Wallet;
import com.aquariuxdemo.h2database.repository.TradingTransactionRepository;
import com.aquariuxdemo.h2database.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TradingTransactionController {
    @Autowired
    private TradingTransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    // Endpoint for buying crypto
    @PostMapping("/buy")
    public ResponseEntity<?> buyCrypto(@RequestBody TradingTransaction buyTransaction) {
        buyTransaction.setTransactionType("buy");
        // Fetch user's wallets
        List<Wallet> wallets = walletRepository.findByUserWalletId(String.valueOf(buyTransaction.getUserTransactionId()));

        Optional<Wallet> matchingWallet = wallets.stream()
                .filter(wallet -> wallet.getCurrency().equals(buyTransaction.getSymbol()))
                .findFirst();



        if (matchingWallet.isPresent()) {
            Wallet wallet = matchingWallet.get();
            Float totalCost = buyTransaction.getPrice() * buyTransaction.getQuantity();

            // Check if the wallet has enough balance
            if (wallet.getBalance() >= 0) {
                // Deduct the total cost from the wallet's balance
                wallet.setBalance(wallet.getBalance()-totalCost);
            } else {
                return new ResponseEntity<>("Insufficient balance in the wallet.", HttpStatus.BAD_REQUEST);
            }
        } else {
            // Create a new wallet if it doesn't exist
            Wallet newWallet = new Wallet();
            newWallet.setUserWalletId(buyTransaction.getUserTransactionId());
            newWallet.setCurrency(buyTransaction.getSymbol());
            newWallet.setBalance(buyTransaction.getPrice() * buyTransaction.getQuantity());

            Float totalCost = buyTransaction.getPrice()*(buyTransaction.getQuantity());

            // Deduct the total cost from the wallet's balance
            newWallet.setBalance(newWallet.getBalance()-totalCost);

            // Add the new wallet to the list of wallets
            wallets.add(newWallet);
        }

        transactionRepository.save(buyTransaction);
        return new ResponseEntity<>("Buy transaction created successfully", HttpStatus.CREATED);
    }

    // Endpoint for selling crypto
    @PostMapping("/sell")
    public ResponseEntity<?> sellCrypto(@RequestBody TradingTransaction sellTransaction) {
        sellTransaction.setTransactionType("sell");
        // Fetch user's wallets
        List<Wallet> wallets = walletRepository.findByUserWalletId(String.valueOf(sellTransaction.getUserTransactionId()));

        Optional<Wallet> matchingWallet = wallets.stream()
                .filter(wallet -> wallet.getCurrency().equals(sellTransaction.getSymbol()))
                .findFirst();



        if (matchingWallet.isPresent()) {
            Wallet wallet = matchingWallet.get();
            Float totalCost = sellTransaction.getPrice() * sellTransaction.getQuantity();

            // Check if the wallet has enough balance
            if (wallet.getBalance() >= 0) {
                // Deduct the total cost from the wallet's balance
                wallet.setBalance(wallet.getBalance()-totalCost);
            } else {
                return new ResponseEntity<>("Insufficient balance in the wallet.", HttpStatus.BAD_REQUEST);
            }
        } else {
            // Create a new wallet if it doesn't exist
            Wallet newWallet = new Wallet();
            newWallet.setUserWalletId(sellTransaction.getUserTransactionId());
            newWallet.setCurrency(sellTransaction.getSymbol());
            newWallet.setBalance(sellTransaction.getPrice() * sellTransaction.getQuantity());

            Float totalCost = sellTransaction.getPrice()*(sellTransaction.getQuantity());

            // Deduct the total cost from the wallet's balance
            newWallet.setBalance(newWallet.getBalance()-totalCost);

            // Add the new wallet to the list of wallets
            wallets.add(newWallet);
        }

        transactionRepository.save(sellTransaction);
        return new ResponseEntity<>("Sell transaction successful", HttpStatus.CREATED);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TradingTransaction>> getUserTradingHistory(@RequestParam Long userTransactionId) {
        List<TradingTransaction> history = transactionRepository.findByUserTransactionId(userTransactionId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}