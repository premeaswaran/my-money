package com.mymoney.faker;

import com.mymoney.enums.Month;
import com.mymoney.models.action.Action;
import com.mymoney.models.action.BalanceAction;
import com.mymoney.models.action.RebalanceAction;

import java.util.ArrayList;
import java.util.List;

import static com.mymoney.enums.Month.APRIL;
import static com.mymoney.enums.Month.MARCH;

public class ActionFaker {
    public static List<Action> generateActionsList() {
        List<Action> actions = new ArrayList<>();
        actions.add(generateBalanceAction(MARCH));
        actions.add(generateBalanceAction(APRIL));
        actions.add(generateRebalanceAction());
        return actions;
    }

    private static BalanceAction generateBalanceAction(Month month) {
        return BalanceAction.builder()
                .value(month)
                .build();
    }

    private static RebalanceAction generateRebalanceAction() {
        return RebalanceAction.builder().build();
    }
}
