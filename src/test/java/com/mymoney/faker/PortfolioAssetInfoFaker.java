package com.mymoney.faker;

import com.mymoney.enums.AssetClass;
import com.mymoney.models.PortfolioAssetInfo;

import static com.mymoney.enums.AssetClass.*;

public class PortfolioAssetInfoFaker {
    public static PortfolioAssetInfo<AssetClass> generateTestPortfolioAssetInfo_Equity(){
        return PortfolioAssetInfo.<AssetClass>builder()
                .assetClass(EQUITY)
                .allocation(8000L)
                .sip(3000L)
                .build();
    }

    public static PortfolioAssetInfo<AssetClass> generateTestPortfolioAssetInfo_Debt(){
        return PortfolioAssetInfo.<AssetClass>builder()
                .assetClass(DEBT)
                .allocation(6000L)
                .sip(2000L)
                .build();
    }

    public static PortfolioAssetInfo<AssetClass> generateTestPortfolioAssetInfo_Gold(){
        return PortfolioAssetInfo.<AssetClass>builder()
                .assetClass(GOLD)
                .allocation(3500L)
                .sip(1000L)
                .build();
    }
}
