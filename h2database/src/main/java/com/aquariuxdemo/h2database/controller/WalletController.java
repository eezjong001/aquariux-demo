package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}