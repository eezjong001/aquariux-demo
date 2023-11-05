package com.aquariuxdemo.h2database.repository;

import com.aquariuxdemo.h2database.entity.Account;
import com.aquariuxdemo.h2database.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    //List<Wallet> findByUser(User user);
}