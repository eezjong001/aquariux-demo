package com.aquariuxdemo.h2database.repository;
import com.aquariuxdemo.h2database.entity.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TickerRepository extends JpaRepository<Ticker, Long> {
    Ticker findBySymbol(String symbol);

    @Query("SELECT t FROM Ticker t WHERE t.symbol = :symbol AND t.TickerType = :TickerType")
    Ticker findBySymbolAndTickerType(String symbol, String TickerType);
}