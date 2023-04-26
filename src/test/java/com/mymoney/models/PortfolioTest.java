package com.mymoney.models;

import com.mymoney.enums.AssetClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mymoney.constant.TestConstants.*;
import static com.mymoney.enums.AssetClass.*;
import static com.mymoney.faker.PortfolioAssetInfoFaker.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PortfolioTest {
    private Portfolio portfolio;

    @BeforeEach
    void setup() {
        List<PortfolioAssetInfo<AssetClass>> assets = new ArrayList<>();
        assets.add(generateTestPortfolioAssetInfo_Equity());
        assets.add(generateTestPortfolioAssetInfo_Debt());
        assets.add(generateTestPortfolioAssetInfo_Gold());
        portfolio = Portfolio.builder().assets(assets).build();
    }

    @Test
    void shouldGetAssetsAllocationPercentages() {
        Map<AssetClass, Double> assetsAllocationPercentages = portfolio.getAssetsAllocationPercentages();

        assertEquals(ASSET_ALLOCATION_EQUITY, assetsAllocationPercentages.get(EQUITY));
        assertEquals(ASSET_ALLOCATION_DEBT, assetsAllocationPercentages.get(DEBT));
        assertEquals(ASSET_ALLOCATION_GOLD, assetsAllocationPercentages.get(GOLD));
    }

    @Test
    void shouldCalculateCurrentTotalAssetValue() {
        assertEquals(TOTAL_ASSET_VALUE, portfolio.calculateCurrentTotalAssetValue());
    }

}