package com.mymoney.models;

import com.mymoney.enums.AssetClass;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.mymoney.util.MathUtil.percentage;

/**
 * This class holds the Portfolio related information.
 *
 * @author Prem Kumar Easwaran
 */
@Data
@Builder
public class Portfolio {
    private List<PortfolioAssetInfo<AssetClass>> assets;

    /**
     * This method calculates and retrieves the allocation percentage of each asset in the overall portfolio.
     * @return A map having the AssetClass as key and the allocation percentage of it in the portfolio as value.
     */
    public Map<AssetClass, Double> getAssetsAllocationPercentages() {
        Map<AssetClass, Double> assetsAllocationPercentages = new LinkedHashMap<>();
        Long totalAssetAllocationValue = calculateTotalAssetAllocationValue();
        assets.forEach(asset -> assetsAllocationPercentages.put(asset.getAssetClass(), percentage(asset.getAllocation(), totalAssetAllocationValue)));
        return assetsAllocationPercentages;
    }

    /**
     * This method calculates the current total asset value of the entire portfolio.
     * @return Total asset value of the entire portfolio.
     */
    public Long calculateCurrentTotalAssetValue() {
        AtomicReference<Long> currentTotalAssetValue = new AtomicReference<>(0L);
        assets.forEach((asset -> currentTotalAssetValue.updateAndGet(total -> total + asset.getCurrentAssetValue())));
        return currentTotalAssetValue.get();
    }

    private Long calculateTotalAssetAllocationValue() {
        AtomicReference<Long> totalAssetAllocationValue = new AtomicReference<>(0L);
        assets.forEach((asset -> totalAssetAllocationValue.updateAndGet(total -> total + asset.getAllocation())));
        return totalAssetAllocationValue.get();
    }
}

