package com.aquariuxdemo.h2database.repository;

import com.aquariuxdemo.h2database.entity.TradingTransaction;
import com.aquariuxdemo.h2database.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradingTransactionRepository extends JpaRepository<TradingTransaction, Long> {
    //List<TradingTransaction> findByUser(User user);
    List<TradingTransaction> findByUserTransactionId(long userTransactionId);
}