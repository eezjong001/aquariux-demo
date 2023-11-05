package com.aquariuxdemo.h2database.controller;

import com.aquariuxdemo.h2database.entity.Ticker;
import com.aquariuxdemo.h2database.repository.TickerRepository;
import com.aquariuxdemo.h2database.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.aquariuxdemo.h2database.util.calculateLatestBestAggregatedPrice;

@RestController
@RequestMapping("/tickers")
public class TickerController {
    private final TickerRepository tickerRepository;
    @Autowired
    private TickerService tickerService;
    public TickerController(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    @PostMapping("/fetch-binance")
    public ResponseEntity<String> fetchAndStoreBinancePrices() {
        tickerService.fetchAndStoreBinancePrices();
        return new ResponseEntity<>("Binance Prices fetched and stored successfully", HttpStatus.OK);
    }

    @PostMapping("/fetch-huobi")
    public ResponseEntity<String> fetchAndStoreHuobiPrices() {
        tickerService.fetchAndStoreHuobiPrices();
        return new ResponseEntity<>("Huobi Prices fetched and stored successfully", HttpStatus.OK);
    }
    @PostMapping("/fetch-bestprices")
    public ResponseEntity<String> fetchBestPrices(@RequestParam String symbol) {
        Float bestPrice = tickerService.fetchBestPrices(symbol);

        if(bestPrice == 0.00F){
            return new ResponseEntity<>("-", HttpStatus.OK);
        }
        return new ResponseEntity<>(bestPrice.toString(), HttpStatus.OK);
    }

    @GetMapping
    public List<Ticker> getAllTickers() {
        return tickerRepository.findAll();
    }
}
