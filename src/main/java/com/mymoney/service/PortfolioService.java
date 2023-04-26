package com.mymoney.service;

import com.mymoney.enums.AssetClass;
import com.mymoney.enums.Month;
import lombok.Builder;
import lombok.Data;
import com.mymoney.models.AssetClassData;
import com.mymoney.models.Portfolio;
import com.mymoney.models.PortfolioAssetInfo;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.mymoney.constant.Constants.REBALANCE_FREQUENCY;
import static com.mymoney.util.MathUtil.shareValueOf;

/**
 * This com.mymoney.service takes care of the Portfolio related functionalities including calculating the balanced rates for each
 * asset class, retrieving the balanced rates and retrieving the rebalanced rates.
 *
 * @author Prem Kumar Easwaran
 */
@Data
public class PortfolioService {
    private Portfolio portfolio;
    private Map<AssetClass, AssetClassData<AssetClass>> assetClassDataMap;
    private Map<AssetClass, Long> rebalancedValues = new LinkedHashMap<>();
    private Map<Month, Map<AssetClass, Long>> balancesOfAllAssets = new LinkedHashMap<>();

    @Builder
    public PortfolioService(Portfolio portfolio, Map<AssetClass, AssetClassData<AssetClass>> assetClassDataMap) {
        this.portfolio = portfolio;
        this.assetClassDataMap = assetClassDataMap;
    }

    /**
     * This method retrieves the balanced rates for all the assets for the given month.
     * @param forMonth The month for which the balanced rates are to be retrieved.
     * @return A map having the AssetClass as key and the balanced rate as the value.
     */
    public Map<AssetClass, Long> getBalanceForAllAssetsForMonth(Month forMonth) {
        calculateMonthlyBalances();
        return balancesOfAllAssets.get(forMonth);
    }

    /**
     * This method retrieves the last rebalanced values against each of the asset classes of the portfolio.
     * @return A map having the AssetClass as key and the rebalanced rate as the value.
     */
    public Map<AssetClass, Long> getLastRebalancedValues() {
        calculateMonthlyBalances();
        return rebalancedValues;
    }

    private void calculateMonthlyBalances() {
        if (balancesOfAllAssets.isEmpty()) {
            calculateBalancesForAllAssetsForAllMonths();
        }
    }

    private void calculateBalancesForAllAssetsForAllMonths() {
        Optional<AssetClassData<AssetClass>> assetClassData = assetClassDataMap.values().stream().findFirst();
        for (Month month : Month.values()) {
            if (assetClassData.isPresent() && month.getMonthNumber() <= assetClassData.get().getMonthlyChangeRate().size()) {
                Map<AssetClass, Long> balanceOfAllAssets = initializeAssetValues();
                portfolio.getAssets().forEach(asset -> {
                    applySipAndChangeRate(month, asset);
                    balanceOfAllAssets.put(asset.getAssetClass(), asset.getCurrentAssetValue());
                });
                if (shouldRebalance(month)) {
                    Long currentTotalAssetValue = portfolio.calculateCurrentTotalAssetValue();
                    portfolio.getAssets().forEach(asset -> performRebalance(asset, currentTotalAssetValue));
                }
                balancesOfAllAssets.put(month, balanceOfAllAssets);
            }
        }
    }

    private void applySipAndChangeRate(Month forMonth, PortfolioAssetInfo<AssetClass> assetInfo) {
        AssetClassData<AssetClass> assetClassData = this.getAssetClassDataMap().get(assetInfo.getAssetClass());
        assetInfo.applySip(forMonth);
        assetInfo.applyChangeRate(assetClassData.getChangeRateForMonth(forMonth));
    }

    private boolean shouldRebalance(Month month) {
        return month.getMonthNumber() % REBALANCE_FREQUENCY == 0;
    }

    private void performRebalance(PortfolioAssetInfo<AssetClass> assetInfo, Long currentTotalAssetValue) {
        Double assetAllocationPercentage = portfolio.getAssetsAllocationPercentages().get(assetInfo.getAssetClass());
        Long rebalancedValue = shareValueOf(assetAllocationPercentage, currentTotalAssetValue);
        assetInfo.setCurrentAssetValue(rebalancedValue);
        rebalancedValues.put(assetInfo.getAssetClass(), rebalancedValue);
    }

    private Map<AssetClass, Long> initializeAssetValues() {
        Map<AssetClass, Long> assetValueMap = new LinkedHashMap<>();
        Arrays.stream(AssetClass.values()).forEach(assetClass -> assetValueMap.put(assetClass, 0L));
        return assetValueMap;
    }
}
