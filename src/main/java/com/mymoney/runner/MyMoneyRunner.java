package com.mymoney.runner;

import com.mymoney.models.MyMoneyData;
import com.mymoney.service.PortfolioService;

import java.util.Scanner;

import static com.mymoney.constant.Constants.INPUT_FILEPATH_USER_PROMPT;
import static com.mymoney.mapper.MyMoneyMapper.getMyMoneyDataFromFile;

public class MyMoneyRunner {
    public static void run(){
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(INPUT_FILEPATH_USER_PROMPT);
        String FILE_PATH = inputScanner.next();
        MyMoneyData myMoneyData = getMyMoneyDataFromFile(FILE_PATH);
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