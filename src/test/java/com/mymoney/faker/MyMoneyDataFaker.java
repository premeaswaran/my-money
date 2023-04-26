package com.mymoney.faker;

import com.mymoney.models.MyMoneyData;

import java.util.ArrayList;
import java.util.List;

import static com.mymoney.faker.ActionFaker.generateActionsList;
import static com.mymoney.faker.AssetClassDataFaker.generateAssetClassDataMap;
import static com.mymoney.faker.PortfolioFaker.generatePortfolio;

public class MyMoneyDataFaker {

    public static MyMoneyData generateMyMoneyData() {
        return MyMoneyData.builder()
                .portfolio(generatePortfolio())
                .assetClassData(generateAssetClassDataMap())
                .actions(generateActionsList())
                .build();
    }

    public static List<String> generateMyMoneyDataStringList() {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("ALLOCATE 8000 6000 3500");
        inputLines.add("SIP 3000 2000 1000");
        inputLines.add("CHANGE 11.00% 9.00% 4.00% JANUARY");
        inputLines.add("CHANGE -6.00% 21.00% -3.00% FEBRUARY");
        inputLines.add("CHANGE 12.50% 18.00% 12.50% MARCH");
        inputLines.add("CHANGE 23.00% -3.00% 7.00% APRIL");
        inputLines.add("BALANCE MARCH");
        inputLines.add("BALANCE APRIL");
        inputLines.add("REBALANCE");
        return inputLines;
    }

}
