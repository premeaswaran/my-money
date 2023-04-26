package com.mymoney.service;

import com.mymoney.enums.AssetClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.mymoney.constant.TestConstants.*;
import static com.mymoney.enums.AssetClass.*;
import static com.mymoney.enums.Month.APRIL;
import static com.mymoney.enums.Month.MARCH;
import static com.mymoney.faker.PortfolioServiceFaker.generatePortfolioService;
import static com.mymoney.faker.PortfolioServiceFaker.generatePortfolioServiceWithRebalanceRates;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PortfolioServiceTest {
    private PortfolioService portfolioService;

    @BeforeEach
    void setup() {
        portfolioService = generatePortfolioService();
    }

    @Test
    void shouldCalculateBalancesForAllAssetsForMonth() {
        Map<AssetClass, Long> marchBalances = portfolioService.getBalanceForAllAssetsForMonth(MARCH);
        Map<AssetClass, Long> aprilBalances = portfolioService.getBalanceForAllAssetsForMonth(APRIL);
        assertEquals(MARCH_EQUITY_BALANCE, marchBalances.get(EQUITY));
        assertEquals(MARCH_DEBT_BALANCE, marchBalances.get(DEBT));
        assertEquals(MARCH_GOLD_BALANCE, marchBalances.get(GOLD));
        assertEquals(APRIL_EQUITY_BALANCE, aprilBalances.get(EQUITY));
        assertEquals(APRIL_DEBT_BALANCE, aprilBalances.get(DEBT));
        assertEquals(APRIL_GOLD_BALANCE, aprilBalances.get(GOLD));
    }

    @Test
    void shouldGetRebalancedValuesAsEmptyIfNotAvailable() {
        Map<AssetClass, Long> rebalancedValues = portfolioService.getLastRebalancedValues();
        assertTrue(rebalancedValues.isEmpty());
    }

    @Test
    void shouldGetRebalanceValuesIfApplicable() {
        portfolioService = generatePortfolioServiceWithRebalanceRates();
        Map<AssetClass, Long> rebalancedValues = portfolioService.getLastRebalancedValues();
        assertEquals(EQUITY_REBALANCED, rebalancedValues.get(EQUITY));
        assertEquals(DEBT_REBALANCED, rebalancedValues.get(DEBT));
        assertEquals(GOLD_REBALANCED, rebalancedValues.get(GOLD));
    }
}