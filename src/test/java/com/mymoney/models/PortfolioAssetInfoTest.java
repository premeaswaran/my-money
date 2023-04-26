package com.mymoney.models;

import com.mymoney.enums.AssetClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.mymoney.constant.TestConstants.*;
import static com.mymoney.enums.Month.*;
import static com.mymoney.faker.PortfolioAssetInfoFaker.generateTestPortfolioAssetInfo_Equity;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PortfolioAssetInfoTest {
    private PortfolioAssetInfo<AssetClass> portfolioAssetInfo;

    @BeforeEach
    void setup(){
        portfolioAssetInfo = generateTestPortfolioAssetInfo_Equity();
    }

    @Test
    void shouldSetCurrentAssetValueIfAllocationIsSetThroughConstructor() {
        assertEquals(TEST_ALLOCATION_1, portfolioAssetInfo.getAllocation());
        assertEquals(TEST_ALLOCATION_1, portfolioAssetInfo.getCurrentAssetValue());
    }

    @Test
    void shouldSetCurrentAssetValueIfAllocationIsSetThroughSetter() {
        portfolioAssetInfo = PortfolioAssetInfo.<AssetClass>builder().build();
        portfolioAssetInfo.setAllocation(TEST_ALLOCATION_1);

        assertEquals(TEST_ALLOCATION_1, portfolioAssetInfo.getAllocation());
        assertEquals(TEST_ALLOCATION_1, portfolioAssetInfo.getCurrentAssetValue());
    }

    @Test
    void shouldNotApplySipIfMonthIsJanuary() {
        portfolioAssetInfo.applySip(JANUARY);
        assertEquals(portfolioAssetInfo.getAllocation(), portfolioAssetInfo.getCurrentAssetValue());
    }

    @Test
    void shouldApplySipIfMonthIsNotJanuary() {
        portfolioAssetInfo.applySip(FEBRUARY);
        portfolioAssetInfo.applySip(MARCH);
        portfolioAssetInfo.applySip(APRIL);
        portfolioAssetInfo.applySip(MAY);
        portfolioAssetInfo.applySip(JUNE);
        portfolioAssetInfo.applySip(JULY);
        portfolioAssetInfo.applySip(AUGUST);
        portfolioAssetInfo.applySip(SEPTEMBER);
        portfolioAssetInfo.applySip(OCTOBER);
        portfolioAssetInfo.applySip(NOVEMBER);
        portfolioAssetInfo.applySip(DECEMBER);

        Long expected = portfolioAssetInfo.getAllocation() + (portfolioAssetInfo.getSip() * 11);
        assertEquals(expected, portfolioAssetInfo.getCurrentAssetValue());
    }

    @Test
    void shouldApplyChangeRate() {
        portfolioAssetInfo.applyChangeRate(TEST_CHANGE_RATE_1);
        assertEquals(CURRENT_ASSET_VALUE, portfolioAssetInfo.getCurrentAssetValue());
    }
}