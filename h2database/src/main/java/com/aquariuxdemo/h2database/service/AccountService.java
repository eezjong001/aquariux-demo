package com.aquariuxdemo.h2database.service;

import com.aquariuxdemo.h2database.entity.Account;
import com.aquariuxdemo.h2database.entity.Wallet;
import com.aquariuxdemo.h2database.repository.AccountRepository;
import com.aquariuxdemo.h2database.repository.TickerRepository;
import com.aquariuxdemo.h2database.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

}
