package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.Wallet;
import com.aquariuxdemo.h2database.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    @Autowired
    private WalletRepository walletRepository;

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Float> getUserWalletBalance(@PathVariable Long userId) {
        // pseudo for now
        // balance check happens here

        float balance = 0; //todo get balance source

        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity<?> createDemoWallet(@RequestParam Long userId) {

        Wallet wallet = new Wallet();
        wallet.setUserWalletId(userId);
        wallet.setCurrency("USDT");
        wallet.setBalance(50000);

        walletRepository.save(wallet);

        return new ResponseEntity<>("Create Demo Wallet Success", HttpStatus.OK);
    }

}