package com.aquariuxdemo.h2database.service;

import com.aquariuxdemo.h2database.entity.Ticker;
import com.aquariuxdemo.h2database.repository.TickerRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import static com.aquariuxdemo.h2database.util.calculateLatestBestAggregatedPrice;
@Service
public class TickerService {
    @Autowired
    private TickerRepository tickerRepository;
    @Autowired
    private RestTemplate restTemplate; // You need to configure and inject RestTemplate

    public void fetchAndStoreBinancePrices() {
        // Fetch prices from Binance and Huobi using their APIs
        try {
            // Fetch prices from Binance and Huobi using their APIs
            ResponseEntity<String> binanceResponse = restTemplate.getForEntity("https://api.binance.com/api/v3/ticker/bookTicker", String.class);

            // Parse the JSON responses
            JSONArray binanceArray = new JSONArray(binanceResponse.getBody());

            // Create a list to store Ticker objects
            List<Ticker> tickers = new ArrayList<>();

            // Process Binance data
            for (int i = 0; i < binanceArray.length(); i++) {
                JSONObject binanceObject = binanceArray.getJSONObject(i);

                String symbol = binanceObject.getString("symbol");
                String tickerType = "binance";

                Ticker existingTicker = tickerRepository.findBySymbolAndTickerType(symbol.toLowerCase(),tickerType.toLowerCase());

                if(existingTicker == null) {
                    Ticker ticker = new Ticker();
                    ticker.setTickerType("binance");
                    ticker.setSymbol(binanceObject.getString("symbol"));
                    ticker.setBidPrice(binanceObject.getFloat("bidPrice"));
                    ticker.setBidQty(binanceObject.getFloat("bidQty"));
                    ticker.setAskPrice(binanceObject.getFloat("askPrice"));
                    ticker.setAskQty(binanceObject.getFloat("askQty"));
                    tickers.add(ticker);
                }else if(existingTicker.getSymbol().equalsIgnoreCase(symbol) && existingTicker.getTickerType().equalsIgnoreCase(tickerType)) {
                    if (existingTicker.getBidPrice() != binanceObject.getFloat("bidPrice")) {
                        existingTicker.setBidPrice(binanceObject.getFloat("bidPrice"));
                    }
                    if (existingTicker.getBidQty() != binanceObject.getFloat("bidQty")) {
                        existingTicker.setBidQty(binanceObject.getFloat("bidQty"));
                    }
                    if (existingTicker.getAskPrice() != binanceObject.getFloat("askPrice")) {
                        existingTicker.setAskPrice(binanceObject.getFloat("askPrice"));
                    }
                    if (existingTicker.getAskQty() != binanceObject.getFloat("askQty")) {
                        existingTicker.setAskQty(binanceObject.getFloat("askQty"));
                    }
                    tickers.add(existingTicker);
                }

            }

            // Save the list of Ticker objects to the database
            tickerRepository.saveAll(tickers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchAndStoreHuobiPrices() {
        // Fetch prices from Binance and Huobi using their APIs
        try {
            // Fetch prices from Binance and Huobi using their APIs
            ResponseEntity<String> huobiResponse = restTemplate.getForEntity("https://api.huobi.pro/market/tickers", String.class);
            JSONObject responseJson = new JSONObject(huobiResponse.getBody());
            String dataJsonString = responseJson.optString("data");

            // Parse the JSON responses
            JSONArray huobiArray = new JSONArray(dataJsonString);

            // Create a list to store Ticker objects
            List<Ticker> tickers = new ArrayList<>();

            // Process Huobi data
            for (int i = 0; i < huobiArray.length(); i++) {
                JSONObject huobiObject = huobiArray.getJSONObject(i);
                String symbol = huobiObject.getString("symbol");
                String tickerType = "huobi";
                // Check if a Ticker with the same symbol already exists in the database

                Ticker existingTicker = tickerRepository.findBySymbolAndTickerType(symbol.toLowerCase(),tickerType.toLowerCase());

                    if(existingTicker == null) {
                        Ticker ticker = new Ticker();
                        ticker.setTickerType("huobi");
                        ticker.setSymbol(huobiObject.getString("symbol"));
                        ticker.setBidPrice(huobiObject.getFloat("bid"));
                        ticker.setBidQty(huobiObject.getFloat("bidSize"));
                        ticker.setAskPrice(huobiObject.getFloat("ask"));
                        ticker.setAskQty(huobiObject.getFloat("askSize"));
                        // Set other fields as needed
                        tickers.add(ticker);
                    }else if(existingTicker.getSymbol().equalsIgnoreCase(symbol) && existingTicker.getTickerType().equalsIgnoreCase(tickerType)){
                        if(existingTicker.getBidPrice() != huobiObject.getFloat("bid")){
                        existingTicker.setBidPrice(huobiObject.getFloat("bid"));
                        }
                        if(existingTicker.getBidQty() != huobiObject.getFloat("bidSize")){
                            existingTicker.setBidQty(huobiObject.getFloat("bidSize"));
                        }
                        if(existingTicker.getAskPrice() != huobiObject.getFloat("ask")){
                            existingTicker.setAskPrice(huobiObject.getFloat("ask"));
                        }
                        if(existingTicker.getAskQty() != huobiObject.getFloat("askSize")){
                            existingTicker.setAskQty(huobiObject.getFloat("askSize"));
                        }
                        tickers.add(existingTicker);
                    }
            }

            // Save the list of Ticker objects to the database
            tickerRepository.saveAll(tickers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
