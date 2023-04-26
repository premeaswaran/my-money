package com.mymoney.faker;

import com.mymoney.enums.AssetClass;
import com.mymoney.enums.Month;
import com.mymoney.models.AssetClassData;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.mymoney.enums.AssetClass.*;
import static com.mymoney.enums.Month.*;

public class AssetClassDataFaker {
    public static Map<AssetClass, AssetClassData<AssetClass>> generateAssetClassDataMap() {
        Map<AssetClass, AssetClassData<AssetClass>> assetClassDataMap = new LinkedHashMap<>();
        assetClassDataMap.put(EQUITY, generateAssetClassData_Equity());
        assetClassDataMap.put(DEBT, generateAssetClassData_Debt());
        assetClassDataMap.put(GOLD, generateAssetClassData_Gold());
        return assetClassDataMap;
    }

    public static Map<AssetClass, AssetClassData<AssetClass>> generateAssetClassDataMap_6M() {
        Map<AssetClass, AssetClassData<AssetClass>> assetClassDataMap = new LinkedHashMap<>();
        assetClassDataMap.put(EQUITY, generateAssetClassData_Equity_6M());
        assetClassDataMap.put(DEBT, generateAssetClassData_Debt_6M());
        assetClassDataMap.put(GOLD, generateAssetClassData_Gold_6M());
        return assetClassDataMap;
    }

    public static AssetClassData<AssetClass> generateAssetClassData_Equity() {
        return AssetClassData.<AssetClass>builder()
                .assetClass(EQUITY)
                .monthlyChangeRate(getMonthlyChangeRate(11d, -6d, 12.5d, 23d))
                .build();
    }

    private static AssetClassData<AssetClass> generateAssetClassData_Debt() {
        return AssetClassData.<AssetClass>builder()
                .assetClass(DEBT)
                .monthlyChangeRate(getMonthlyChangeRate(9d, 21d, 18d, -3d))
                .build();
    }

    private static AssetClassData<AssetClass> generateAssetClassData_Gold() {
        return AssetClassData.<AssetClass>builder()
                .assetClass(GOLD)
                .monthlyChangeRate(getMonthlyChangeRate(4d, -3d, 12.5d, 7d))
                .build();
    }

    public static AssetClassData<AssetClass> generateAssetClassData_Equity_6M() {
        return AssetClassData.<AssetClass>builder()
                .assetClass(EQUITY)
                .monthlyChangeRate(getMonthlyChangeRate_6M(11d, -6d, 12.5d, 23d, 5d, -2d))
                .build();
    }

    private static AssetClassData<AssetClass> generateAssetClassData_Debt_6M() {
        return AssetClassData.<AssetClass>builder()
                .assetClass(DEBT)
                .monthlyChangeRate(getMonthlyChangeRate_6M(9d, 21d, 18d, -3d, 4d, 12d))
                .build();
    }

    private static AssetClassData<AssetClass> generateAssetClassData_Gold_6M() {
        return AssetClassData.<AssetClass>builder()
                .assetClass(GOLD)
                .monthlyChangeRate(getMonthlyChangeRate_6M(4d, -3d, 12.5d, 7d, -2d, 7d))
                .build();
    }

    private static Map<Month, Double> getMonthlyChangeRate_6M(Double jan, Double feb, Double mar, Double apr, Double may, Double jun) {
        Map<Month, Double> monthlyChangeRate = getMonthlyChangeRate(jan, feb, mar, apr);
        monthlyChangeRate.put(MAY, may);
        monthlyChangeRate.put(JUNE, jun);
        return monthlyChangeRate;
    }

    private static Map<Month, Double> getMonthlyChangeRate(Double jan, Double feb, Double mar, Double apr) {
        Map<Month, Double> monthlyChangeRate = new LinkedHashMap<>();
        monthlyChangeRate.put(JANUARY, jan);
        monthlyChangeRate.put(FEBRUARY, feb);
        monthlyChangeRate.put(MARCH, mar);
        monthlyChangeRate.put(APRIL, apr);
        return monthlyChangeRate;
    }
}
