package com.mymoney.models;

import com.mymoney.enums.AssetClass;
import com.mymoney.enums.Month;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * This class represent the asset class specific data.
 *
 * @param <T> AssetClass for which the data relates to.
 * @author Prem Kumar Easwaran
 */
@Data
@Builder
public class AssetClassData<T extends Enum<AssetClass>> {
    private T assetClass;
    private Map<Month, Double> monthlyChangeRate;

    /**
     * This method retrieves the change rate for the given month in Double.
     * @param month The month for which the change rate is to be retrieved.
     * @return Change rate for the given month.
     */
    public Double getChangeRateForMonth(Month month) {
        return monthlyChangeRate.get(month);
    }
}