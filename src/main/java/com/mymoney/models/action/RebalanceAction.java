package com.mymoney.models.action;

import com.mymoney.enums.AssetClass;
import com.mymoney.enums.Command;
import lombok.Builder;
import com.mymoney.service.PortfolioService;

import java.util.Map;

import static com.mymoney.constant.Constants.CANNOT_REBALANCE;
import static com.mymoney.enums.Command.REBALANCE;
import static com.mymoney.util.PrintUtil.customPrint;

/**
 * Concrete class representing the "Rebalance" action to be performed on the input data.
 *
 * @author Prem Kumar Easwaran
 */
public class RebalanceAction extends Action {
    public static final Command REBALANCE_ACTION = REBALANCE;

    @Builder
    public RebalanceAction() {
        super(REBALANCE_ACTION);
    }

    /**
     * Performs the re-balancing action on the portfolio based on the given input data.
     * @param portfolioService Portfolio com.mymoney.service which enables the re-balancing action.
     */
    @Override
    public void performAndPrint(PortfolioService portfolioService) {
        Map<AssetClass, Long> rebalancedValues = portfolioService.getLastRebalancedValues();
        if (rebalancedValues.isEmpty()) {
            System.out.println(CANNOT_REBALANCE);
        } else {
            customPrint(rebalancedValues.values());
        }
    }
}
