package com.aquariuxdemo.h2database.service;

import com.aquariuxdemo.h2database.entity.Wallet;
import com.aquariuxdemo.h2database.repository.AccountRepository;
import com.aquariuxdemo.h2database.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createNewDemoWallet(Long id) {
        Wallet wallet = new Wallet();
        wallet.setUserWalletId(id);
        wallet.setCurrency("USDT");
        wallet.setBalance(50000);

        walletRepository.save(wallet);
    }
}
