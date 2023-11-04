package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.Ticker;
import com.aquariuxdemo.h2database.repository.TickerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickers")
public class TickerController {
    private final TickerRepository tickerRepository;
    public TickerController(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    @GetMapping
    public List<Ticker> getAllTickers() {
        return tickerRepository.findAll();
    }
}
