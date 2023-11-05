package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.Ticker;
import com.aquariuxdemo.h2database.entity.Wallet;
import com.aquariuxdemo.h2database.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    @Autowired
    private WalletRepository walletRepository;


    @GetMapping("/create")
    public ResponseEntity<?> createDemoWallet(@RequestParam Long userId) {

        Wallet wallet = new Wallet();
        wallet.setUserWalletId(userId);
        wallet.setCurrency("USDT");
        wallet.setBalance(50000);

        walletRepository.save(wallet);

        return new ResponseEntity<>("Create Demo Wallet Success", HttpStatus.OK);
    }
    @GetMapping("/balance")
    public ResponseEntity<List<Wallet>> getAllByUserWalletId(@RequestParam String id) {
        List<Wallet> wallets = walletRepository.findByUserWalletId(id);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
}