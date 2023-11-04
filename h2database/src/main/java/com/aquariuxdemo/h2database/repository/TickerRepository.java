package com.aquariuxdemo.h2database.repository;
import com.aquariuxdemo.h2database.entity.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerRepository extends JpaRepository<Ticker, Long> {
}