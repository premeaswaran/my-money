package com.mymoney.models.action;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mymoney.service.PortfolioService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.mymoney.constant.Constants.CANNOT_REBALANCE;
import static com.mymoney.constant.TestConstants.*;
import static com.mymoney.faker.PortfolioServiceFaker.generatePortfolioService;
import static com.mymoney.faker.PortfolioServiceFaker.generatePortfolioServiceWithRebalanceRates;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RebalanceActionTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream actualOut = System.out;
    private RebalanceAction rebalanceAction;
    private PortfolioService portfolioService;

    @BeforeEach
    void setup() {
        rebalanceAction = RebalanceAction.builder().build();
        portfolioService = generatePortfolioService();
        System.setOut(new PrintStream(outStream));
    }

    @AfterEach
    void restore() {
        System.setOut(actualOut);
    }

    @Test
    void shouldPrintCannotRebalanceIfNotApplicable() {
        rebalanceAction.performAndPrint(portfolioService);
        assertEquals(CANNOT_REBALANCE, outStream.toString().trim());
    }

    @Test
    void shouldPrintRebalancedValuesIfApplicable() {
        portfolioService = generatePortfolioServiceWithRebalanceRates();
        rebalanceAction.performAndPrint(portfolioService);
        String expected = EQUITY_REBALANCED + " " + DEBT_REBALANCED + " " + GOLD_REBALANCED;
        assertEquals(expected, outStream.toString().trim());
    }

}