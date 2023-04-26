package com.mymoney.models;

import com.mymoney.enums.AssetClass;
import com.mymoney.enums.Month;
import lombok.Builder;
import lombok.Data;

/**
 * This class represents the asset-class related data specific for the portfolio.
 *
 * @param <T> AssetClass of the Portfolio for which the data relates to.
 * @author Prem Kumar Easwaran
 */
@Data
public class PortfolioAssetInfo<T extends Enum<AssetClass>> {
    private T assetClass;
    private Long allocation;
    private Long sip;
    private Long currentAssetValue;

    @Builder
    public PortfolioAssetInfo(T assetClass, Long allocation, Long sip) {
        this.assetClass = assetClass;
        this.allocation = allocation;
        this.sip = sip;
        this.currentAssetValue = allocation;
    }

    /**
     * Sets the currentAssetValue to be the same as initial allocation value.
     * @param allocation Initial allocation value for the asset class.
     */
    public void setAllocation(Long allocation) {
        this.allocation = allocation;
        this.currentAssetValue = allocation;
    }

    /**
     * Adds up the SIP value to currentAssetValue if it is applicable based on condition.
     * @param month Month of the year for which the SIP is to be applied.
     */
    public void applySip(Month month) {
        if(isSipApplicable(month)) {
            currentAssetValue += sip;
        }
    }

    /**
     * Applies the changes rate on the currentAssetValue.
     * @param changeRate Change rate of the asset class that is to be applied to the currentAssetValue.
     */
    public void applyChangeRate(Double changeRate) {
        currentAssetValue += (long) Math.floor(currentAssetValue * (changeRate / 100));
    }

    private boolean isSipApplicable(Month month) {
        return month != Month.JANUARY;
    }
}
