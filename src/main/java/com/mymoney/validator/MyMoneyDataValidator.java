package com.mymoney.validator;

import com.mymoney.enums.Month;
import com.mymoney.exception.InvalidMyMoneyDataException;

import java.util.List;
import java.util.stream.Collectors;

import static com.mymoney.constant.Constants.*;
import static com.mymoney.enums.Command.*;
import static com.mymoney.mapper.MyMoneyMapper.SEPARATOR_SPACE;
import static com.mymoney.util.MathUtil.isLongValue;

public class MyMoneyDataValidator {
    public static boolean validate(List<String> inputLines) {
        if(isValid(inputLines))
            return true;
        throw new InvalidMyMoneyDataException(INVALID_INPUT_DATA_EXCEPTION_MESSAGE);
    }

    private static boolean isValid(List<String> inputLines) {
        return isNotEmpty(inputLines)
                && hasMinimumThreeLines(inputLines)
                && isAllocationDataValid(inputLines.get(0))
                && isSipDataValid(inputLines.get(1))
                && isChangeRateDataValid(inputLines)
                && isBalanceDataValid(inputLines)
                && isRebalanceDataValid(inputLines);
    }

    private static boolean isNotEmpty(List<String> inputLines) {
        return !inputLines.isEmpty();
    }

    private static boolean hasMinimumThreeLines(List<String> inputLines) {
        return inputLines.size() > 2;
    }

    private static boolean isAllocationDataValid(String lineOne) {
        return lineOneIsAllocationData(lineOne)
                && allocationDataHasRequiredNumberOfColumns(lineOne)
                && inputLineHasOnlyNumbers(lineOne);
    }

    private static boolean lineOneIsAllocationData(String lineOne) {
        return lineOne.startsWith(ALLOCATE.name());
    }

    private static boolean allocationDataHasRequiredNumberOfColumns(String lineOne) {
        return lineOne.split(SEPARATOR_SPACE).length == EXPECTED_NUMBER_OF_COLUMNS_IN_ALLOCATE_AND_SIP_INPUT;
    }

    private static boolean isSipDataValid(String lineTwo) {
        return lineTwoIsSipData(lineTwo)
                && sipDataHasRequiredNumberOfColumns(lineTwo)
                && inputLineHasOnlyNumbers(lineTwo);
    }

    private static boolean lineTwoIsSipData(String lineTwo) {
        return lineTwo.startsWith(SIP.name());
    }

    private static boolean sipDataHasRequiredNumberOfColumns(String lineTwo) {
        return lineTwo.split(SEPARATOR_SPACE).length == EXPECTED_NUMBER_OF_COLUMNS_IN_ALLOCATE_AND_SIP_INPUT;
    }

    private static boolean isChangeRateDataValid(List<String> inputLines) {
        List<String> changeRateData = inputLines.stream().filter(line -> line.startsWith(CHANGE.name())).collect(Collectors.toList());
        return hasMinimumOneChangeRateInfo(changeRateData)
                && allChangeRateDataRowsHaveRequiredNumberOfColumns(changeRateData);
    }

    private static boolean hasMinimumOneChangeRateInfo(List<String> changeRateDataRows) {
        return changeRateDataRows != null
                && changeRateDataRows.size() > 0;
    }

    private static boolean allChangeRateDataRowsHaveRequiredNumberOfColumns(List<String> changeRateDataRows) {
        return changeRateDataRows.stream()
                .noneMatch(line -> line.split(SEPARATOR_SPACE)
                        .length != EXPECTED_NUMBER_OF_COLUMNS_IN_CHANGE_INPUT);

    }

    private static boolean isBalanceDataValid(List<String> inputLines) {
        List<String> balanceDataRows = inputLines.stream().filter(line -> line.startsWith(BALANCE.name())).collect(Collectors.toList());
        return balanceDataRows.stream().allMatch(balanceDataRow ->
                (balanceDataRow.split(SEPARATOR_SPACE).length == EXPECTED_NUMBER_OF_COLUMNS_IN_BALANCE_DATA_INPUT)
                        && Month.containsValue(balanceDataRow.split(SEPARATOR_SPACE)[1]));
    }

    private static boolean isRebalanceDataValid(List<String> inputLines) {
        List<String> rebalanceDataRows = inputLines.stream().filter(line -> line.startsWith(REBALANCE.name())).collect(Collectors.toList());
        return rebalanceDataRows.stream().allMatch(rebalanceDataRow -> rebalanceDataRow.equals(REBALANCE.name()));
    }

    private static boolean inputLineHasOnlyNumbers(String line) {
        String[] splitData = line.split(SEPARATOR_SPACE);
        for (int splitDataIndex = 1; splitDataIndex < splitData.length; splitDataIndex++) {
            if (isLongValue(splitData[splitDataIndex]))
                return false;
        }
        return true;
    }
}
