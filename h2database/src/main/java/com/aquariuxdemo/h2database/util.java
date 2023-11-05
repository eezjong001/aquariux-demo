package com.aquariuxdemo.h2database;

import org.json.JSONObject;

public class util {

    public static float calculateLatestBestAggregatedPrice(String binanceData, String huobiData, String identifier) {
        try {
            //System.out.println("data util");
            //System.out.println(binanceData);
            //System.out.println(huobiData);

            // Parse JSON responses from Binance and Huobi
            JSONObject binanceJson = new JSONObject(binanceData);
            JSONObject huobiJson = new JSONObject(huobiData);

            // Extract bid and ask prices from both sources
            float binanceBidPrice = Float.parseFloat(binanceJson.getString("bidPrice"));
            float binanceAskPrice = Float.parseFloat(binanceJson.getString("askPrice"));

            float huobiBidPrice = Float.parseFloat(huobiJson.getString("bid"));
            float huobiAskPrice = Float.parseFloat(huobiJson.getString("ask"));

            // Calculate the best aggregated bid and ask prices
            float bestBidPrice = Math.min(binanceBidPrice, huobiBidPrice);
            float bestAskPrice = Math.max(binanceAskPrice, huobiAskPrice);

            // You can return the best aggregated bid or ask price, or both as needed
            // For exampleturn, re the average of the best bid and ask prices
            float bestAggregatedPrice = (bestBidPrice + bestAskPrice) / 2.0F;
            if(identifier.equals("1")) return bestAggregatedPrice;
            if(identifier.equals("2")) return bestBidPrice;
            if(identifier.equals("3")) return bestAskPrice;

            return -1.0F;
        } catch (Exception e) {

            e.printStackTrace();
            return -1.0F;
        }
    }
}
