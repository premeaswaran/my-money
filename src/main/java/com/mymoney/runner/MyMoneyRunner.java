package com.mymoney.runner;

import com.mymoney.models.MyMoneyData;
import com.mymoney.service.PortfolioService;

import static com.mymoney.mapper.MyMoneyMapper.getMyMoneyDataFromFile;

public class MyMoneyRunner {
    public static void run(String filePath){
        MyMoneyData myMoneyData = getMyMoneyDataFromFile(filePath);
        PortfolioService portfolioService = getPortfolioService(myMoneyData);
        if(myMoneyData.getActions().size() > 0) {
            myMoneyData.getActions().forEach(action -> action.performAndPrint(portfolioService));
        }
    }

    private static PortfolioService getPortfolioService(MyMoneyData myMoneyData) {
        return PortfolioService.builder()
                .portfolio(myMoneyData.getPortfolio())
                .assetClassDataMap(myMoneyData.getAssetClassData())
                .build();
    }
}