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
        List<Wallet> wallets = walletRepository.findByUserWalletId("1");

        Optional<Wallet> matchingWallet = wallets.stream()
                .filter(wallet -> wallet.getCurrency().equals(buyTransaction.getSymbol()))
                .findFirst();

        Optional<Wallet> usdtWallet = wallets.stream()
                .filter(wallet -> wallet.getCurrency().equals("USDT"))
                .findFirst();
        if(usdtWallet.isPresent()) {
            Wallet walletmain = usdtWallet.get();

            if (matchingWallet.isPresent()) {
                Wallet wallet = matchingWallet.get();
                Float totalCost = buyTransaction.getPrice() * buyTransaction.getQuantity();

                wallet.setBalance(wallet.getBalance() + totalCost);

                if (walletmain.getBalance() >= (buyTransaction.getPrice() * buyTransaction.getQuantity())) {
                    // Deduct the total cost from the wallet's balance
                    walletmain.setBalance(walletmain.getBalance() - totalCost);
                } else {
                    return new ResponseEntity<>("Insufficient balance in the wallet.", HttpStatus.BAD_REQUEST);
                }

            } else {
                // Create a new wallet if it doesn't exist
                Wallet newWallet = new Wallet();
                newWallet.setUserWalletId(buyTransaction.getUserTransactionId());
                newWallet.setCurrency(buyTransaction.getSymbol());
                System.out.println("buy price :" + buyTransaction.getPrice());
                System.out.println("buy qty :" + buyTransaction.getQuantity());
                newWallet.setBalance(buyTransaction.getPrice() * buyTransaction.getQuantity());

                //Float totalCost = buyTransaction.getPrice()*(buyTransaction.getQuantity());

                // Deduct the total cost from the wallet's balance
                //newWallet.setBalance(newWallet.getBalance()-totalCost);

                // Add the new wallet to the list of wallets
                wallets.add(newWallet);
                walletRepository.saveAll(wallets);
            }

        transactionRepository.save(buyTransaction);
        return new ResponseEntity<>("Buy transaction created successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("USDT wallet not found.", HttpStatus.BAD_REQUEST);
        }
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

        Optional<Wallet> usdtWallet = wallets.stream()
                .filter(wallet -> wallet.getCurrency().equals("USDT"))
                .findFirst();
        if(usdtWallet.isPresent()) {
            Wallet walletmain = usdtWallet.get();

            if (matchingWallet.isPresent()) {
                Wallet wallet = matchingWallet.get();
                Float totalCost = sellTransaction.getPrice() * sellTransaction.getQuantity();

                // Check if the wallet has enough balance
                if (wallet.getBalance() >= (sellTransaction.getPrice() * sellTransaction.getQuantity())) {
                    // Deduct the total cost from the wallet's balance
                    wallet.setBalance(wallet.getBalance() - totalCost);
                    walletmain.setBalance(walletmain.getBalance() + totalCost);
                } else {
                    return new ResponseEntity<>("Insufficient balance in the wallet.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Insufficient balance in the wallet.", HttpStatus.BAD_REQUEST);
                //Wallet must already exist to sell

            }

            transactionRepository.save(sellTransaction);
            return new ResponseEntity<>("Sell transaction successful", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("USDT wallet not found.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<TradingTransaction>> getUserTradingHistory(@RequestParam Long userTransactionId) {
        List<TradingTransaction> history = transactionRepository.findByUserTransactionId(userTransactionId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}