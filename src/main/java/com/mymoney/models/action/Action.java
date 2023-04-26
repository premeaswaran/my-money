package com.mymoney.models.action;

import com.mymoney.enums.Command;
import lombok.AllArgsConstructor;
import lombok.ToString;
import com.mymoney.service.PortfolioService;

/**
 * Abstract class representing the action to be performed on the given input data.
 *
 * @author Prem Kumar Easwaran
 */
@ToString
@AllArgsConstructor
public abstract class Action {
    public Command command;
    public abstract void performAndPrint(PortfolioService portfolioService);
}
