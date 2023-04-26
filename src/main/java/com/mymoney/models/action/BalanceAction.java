package com.mymoney.models.action;

import com.mymoney.enums.Command;
import com.mymoney.enums.Month;
import lombok.Builder;
import lombok.Getter;
import com.mymoney.service.PortfolioService;

import static com.mymoney.enums.Command.BALANCE;
import static com.mymoney.util.PrintUtil.customPrint;

/**
 * Concrete class representing the "Balance" action to be performed on the input data for the given month.
 *
 * @author Prem Kumar Easwaran
 */
@Getter
public class BalanceAction extends Action {
    public static final Command BALANCE_ACTION = BALANCE;
    private final Month value;

    @Builder
    public BalanceAction(Month value) {
        super(BALANCE_ACTION);
        this.value = value;
    }

    /**
     * Performs the balancing action on the portfolio based on the given input data.
     * @param portfolioService Portfolio com.mymoney.service which enables the balancing action.
     */
    @Override
    public void performAndPrint(PortfolioService portfolioService) {
        customPrint(portfolioService.getBalanceForAllAssetsForMonth(value).values());
    }
}
