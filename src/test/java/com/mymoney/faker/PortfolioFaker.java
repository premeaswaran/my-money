package com.mymoney.faker;

import com.mymoney.enums.AssetClass;
import com.mymoney.models.Portfolio;
import com.mymoney.models.PortfolioAssetInfo;

import java.util.ArrayList;
import java.util.List;

import static com.mymoney.faker.PortfolioAssetInfoFaker.*;

public class PortfolioFaker {

    public static Portfolio generatePortfolio() {
        List<PortfolioAssetInfo<AssetClass>> assets = new ArrayList<>();
        assets.add(generateTestPortfolioAssetInfo_Equity());
        assets.add(generateTestPortfolioAssetInfo_Debt());
        assets.add(generateTestPortfolioAssetInfo_Gold());
        return Portfolio.builder()
                .assets(assets)
                .build();
    }

}
