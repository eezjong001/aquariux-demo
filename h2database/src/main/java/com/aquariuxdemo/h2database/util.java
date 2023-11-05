package com.aquariuxdemo.h2database;

import com.aquariuxdemo.h2database.entity.Ticker;
import org.json.JSONObject;

public class util {

    public static float calculateLatestBestAggregatedPrice(Ticker binanceData, Ticker huobiData, String identifier) {
        try {

            if(binanceData != null && huobiData != null) {

                float binanceBidPrice = binanceData.getBidPrice();
                float binanceAskPrice = binanceData.getAskPrice();

                float huobiBidPrice = huobiData.getBidPrice();
                float huobiAskPrice = huobiData.getAskPrice();


                float bestBidPrice = Math.min(binanceBidPrice, huobiBidPrice);
                float bestAskPrice = Math.max(binanceAskPrice, huobiAskPrice);


                float bestAggregatedPrice = (bestBidPrice + bestAskPrice) / 2.0F;
                if (identifier.equals("1")) return bestAggregatedPrice;
                if (identifier.equals("2")) return bestBidPrice;
                if (identifier.equals("3")) return bestAskPrice;
            }else{
                return -0.00F; // no matching data to aggregate
            }
            return -0.00F;
        } catch (Exception e) {

            e.printStackTrace();
            return -1.0F;
        }
    }
}
