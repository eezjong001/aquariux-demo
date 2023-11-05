package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.Account;
import com.aquariuxdemo.h2database.repository.AccountRepository;
import com.aquariuxdemo.h2database.service.AccountService;
import com.aquariuxdemo.h2database.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> registerAccount(@RequestParam String username, String password) {
        try {
            if(accountRepository.findByUsername(username) == null) {
            Account newAccount = new Account();
            newAccount.setUsername(username);
            newAccount.setPassword(password);

            accountRepository.save(newAccount);


                return new ResponseEntity<>("Demo Account created successfully", HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Failed to create account , user exists", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create account: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-demo")
    public ResponseEntity<?> registerDemoAccount(@RequestParam String username, String password) {
        try {

            if(accountRepository.findByUsername(username) == null) {
                Account newAccount = new Account();
                newAccount.setUsername(username);
                newAccount.setPassword(password);

                accountRepository.save(newAccount);
                Long id = accountRepository.findByUsername(username).getId();

                walletService.createNewDemoWallet(id);


                return new ResponseEntity<>("Demo Account created successfully", HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Failed to create account , user exists", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create account: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-demo-credentials")
    public ResponseEntity<Map<String, String>> getAccountCredentials() {
        // Fetch the account credentials from your database
        String username = accountRepository.findByUsername("demo").getUsername();
        String password = accountRepository.findByUsername("demo").getPassword();

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        System.out.println("Sending Account Details " + username + " / " + password);

        return ResponseEntity.ok(credentials);
    }

}