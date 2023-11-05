package com.aquariuxdemo.h2database.repository;

import com.aquariuxdemo.h2database.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

}