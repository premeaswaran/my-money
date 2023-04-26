package com.mymoney.faker;

import com.mymoney.service.PortfolioService;

import static com.mymoney.faker.AssetClassDataFaker.generateAssetClassDataMap;
import static com.mymoney.faker.AssetClassDataFaker.generateAssetClassDataMap_6M;
import static com.mymoney.faker.PortfolioFaker.generatePortfolio;

public class PortfolioServiceFaker {

    public static PortfolioService generatePortfolioService() {
        return PortfolioService.builder()
                .portfolio(generatePortfolio())
                .assetClassDataMap(generateAssetClassDataMap())
                .build();
    }

    public static PortfolioService generatePortfolioServiceWithRebalanceRates() {
        return PortfolioService.builder()
                .portfolio(generatePortfolio())
                .assetClassDataMap(generateAssetClassDataMap_6M())
                .build();
    }

}
