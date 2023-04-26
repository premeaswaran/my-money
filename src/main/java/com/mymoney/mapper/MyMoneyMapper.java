package com.mymoney.mapper;

import com.mymoney.enums.AssetClass;
import com.mymoney.enums.Month;
import com.mymoney.models.AssetClassData;
import com.mymoney.models.MyMoneyData;
import com.mymoney.models.Portfolio;
import com.mymoney.models.PortfolioAssetInfo;
import com.mymoney.models.action.Action;
import com.mymoney.models.action.BalanceAction;
import com.mymoney.models.action.RebalanceAction;
import com.mymoney.util.FileReaderUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.mymoney.enums.AssetClass.*;
import static com.mymoney.enums.Command.*;
import static com.mymoney.validator.MyMoneyDataValidator.validate;

/**
 * This com.mymoney.mapper takes care of all the basic com.mymoney.MyMoney application related functionalities such
 * as converting the input data from file into MyMoneyData object.
 *
 * @author Prem Kumar Easwaran
 */
public class MyMoneyMapper {
    public static final String SEPARATOR_SPACE = " ";
    public static final String SEPARATOR_PERCENTAGE = "%";
    public static final Map<Integer, AssetClass> assetClassColumnNumberMap = new LinkedHashMap<>();

    //Can alternatively be read from configuration file if need be.
    static {
        assetClassColumnNumberMap.put(1, EQUITY);
        assetClassColumnNumberMap.put(2, DEBT);
        assetClassColumnNumberMap.put(3, GOLD);
    }

    /**
     * This method converts the input file present in the given filepath into a MyMoneyData object.
     * @param filePath Path to the input file.
     * @return Object of MyMoneyData which holds all the required information such as Portfolio data,
     * asset class data and actions to be performed.
     */
    public static MyMoneyData getMyMoneyDataFromFile(String filePath) {
        List<String> inputLines = FileReaderUtil.getInputDataFromFile(filePath);
        validate(inputLines);
        return convert(inputLines);
    }

    private static MyMoneyData convert(List<String> inputLines) {
        Portfolio portfolio = Portfolio.builder().assets(initializeAssets()).build();
        List<Action> actions = new ArrayList<>();
        Map<AssetClass, AssetClassData<AssetClass>> assetClassDataMap = new LinkedHashMap<>();

        for (int lineNumber = 0; lineNumber < inputLines.size(); lineNumber++) {
            String inputLine = inputLines.get(lineNumber);
            switch (lineNumber) {
                case 0:
                    convertToAllocationData(portfolio.getAssets(), inputLine);
                    break;
                case 1:
                    convertToSipData(portfolio.getAssets(), inputLine);
                    break;
                default:
                    if (inputLine.startsWith(CHANGE.name())) {
                        convertToAssetClassData(assetClassDataMap, inputLine);
                    } else {
                        convertToInstruction(actions, inputLine);
                    }
            }
        }

        return MyMoneyData.builder()
                .portfolio(portfolio)
                .assetClassData(assetClassDataMap)
                .actions(actions)
                .build();
    }

    private static void convertToAllocationData(List<PortfolioAssetInfo<AssetClass>> assets, String inputLine) {
        String[] assetAllocations = inputLine.split(SEPARATOR_SPACE);
        IntStream.range(1, assetAllocations.length).forEachOrdered(assetIndex -> assets.get(assetIndex - 1)
                .setAllocation(Long.valueOf(assetAllocations[assetIndex])));
    }

    private static void convertToSipData(List<PortfolioAssetInfo<AssetClass>> assets, String inputLine) {
        String[] sips = inputLine.split(SEPARATOR_SPACE);
        IntStream.range(1, sips.length).forEachOrdered(assetIndex -> assets.get(assetIndex - 1)
                .setSip(Long.valueOf(sips[assetIndex])));
    }

    private static void convertToInstruction(List<Action> actions, String inputLine) {
        if(inputLine.startsWith(BALANCE.name())) {
            actions.add(BalanceAction.builder().value(Month.valueOf(inputLine.split(SEPARATOR_SPACE)[1])).build());
        } else if(inputLine.startsWith(REBALANCE.name())) {
            actions.add(RebalanceAction.builder().build());
        }
    }

    private static List<PortfolioAssetInfo<AssetClass>> initializeAssets() {
        List<PortfolioAssetInfo<AssetClass>> assets = new ArrayList<>();
        assetClassColumnNumberMap.forEach((column, assetClass) -> assets.add(PortfolioAssetInfo.<AssetClass>builder().assetClass(assetClass).build()));
        return assets;
    }

    private static void convertToAssetClassData(Map<AssetClass, AssetClassData<AssetClass>> assetClassDataMap,
                                                String monthlyChangeRateString) {
        if(assetClassDataMap.isEmpty()) {
            initializeAssetClassDataMap(assetClassDataMap);
        }
        String[] splitStr = monthlyChangeRateString.split(SEPARATOR_SPACE);
        Month month = Month.valueOf(splitStr[assetClassColumnNumberMap.size() + 1]);
        assetClassColumnNumberMap.forEach((column, assetClass) ->
                assetClassDataMap.get(assetClass).getMonthlyChangeRate().put(month, convertToChangePercentage(splitStr[column])));
    }

    private static void initializeAssetClassDataMap(Map<AssetClass, AssetClassData<AssetClass>> assetClassDataMap) {
        assetClassColumnNumberMap.forEach((column, assetClass) ->
                assetClassDataMap.put(assetClass, AssetClassData.<AssetClass>builder().assetClass(assetClass).monthlyChangeRate(new LinkedHashMap<>()).build()));
    }

    private static Double convertToChangePercentage(String assetChangeRateString) {
        return Double.parseDouble(assetChangeRateString.split(SEPARATOR_PERCENTAGE)[0]);
    }
}
