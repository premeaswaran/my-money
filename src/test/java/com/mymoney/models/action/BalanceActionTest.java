package com.mymoney.models.action;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mymoney.service.PortfolioService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.mymoney.constant.TestConstants.*;
import static com.mymoney.enums.Month.MARCH;
import static com.mymoney.faker.PortfolioServiceFaker.generatePortfolioService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceActionTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream actualOut = System.out;
    private BalanceAction balanceAction;
    private PortfolioService portfolioService;

    @BeforeEach
    void setup() {
        balanceAction = BalanceAction.builder().value(MARCH).build();
        portfolioService = generatePortfolioService();
        System.setOut(new PrintStream(outStream));
    }

    @AfterEach
    void restore() {
        System.setOut(actualOut);
    }

    @Test
    void shouldPrintBalancedRatesForMonth() {
        balanceAction.performAndPrint(portfolioService);
        String expected = MARCH_EQUITY_BALANCE + " " + MARCH_DEBT_BALANCE + " " + MARCH_GOLD_BALANCE;
        assertEquals(expected, outStream.toString().trim());
    }
}
