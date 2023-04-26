package com.mymoney.validator;

import com.mymoney.exception.InvalidMyMoneyDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mymoney.faker.MyMoneyDataFaker.generateMyMoneyDataStringList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyMoneyDataValidatorTest extends MyMoneyDataValidator {

    private static List<String> inputLines;

    @BeforeEach
    void setup() {
        inputLines = generateMyMoneyDataStringList();
    }

    @Test
    void shouldReturnTrueIfAllValid() {
        assertTrue(validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfListIsEmpty() {
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(new ArrayList<>()));
    }

    @Test
    void shouldThrowExceptionIfListHasLessThanThreeElements() {
        List<String> invalidList = new ArrayList<>();
        invalidList.add(inputLines.get(0));
        invalidList.add(inputLines.get(1));
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(invalidList));
    }

    @Test
    void shouldThrowExceptionIfLineOneIsNotAllocationData() {
        inputLines.set(0, "SIP 3000 2000 1000");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfAllocationDataDoesNotHaveRequiredNumberOfColumns() {
        inputLines.set(0, "ALLOCATE 8000 6000");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfAllocationDataHasOtherThanNumbers() {
        inputLines.set(0, "ALLOCATE 8000 6000 TEXT");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfLineTwoIsNotSipData() {
        inputLines.set(1, "ALLOCATE 8000 6000 3500");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfSipDataDoesNotHaveRequiredNumberOfColumns() {
        inputLines.set(1, "SIP 3000 2000");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfSipDataHasOtherThanNumbers() {
        inputLines.set(1, "SIP 3000 TEXT 1000");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfThereIsNotChangeRateData() {
        List<String> invalidList = new ArrayList<>();
        invalidList.add(inputLines.get(0));
        invalidList.add(inputLines.get(1));
        invalidList.add(inputLines.get(7));
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(invalidList));
    }

    @Test
    void shouldThrowExceptionIfOneOfTheChangeRateDataDoesNotHaveRequiredNumberOfColumns() {
        inputLines.set(2, "CHANGE 11.00% 9.00% 4.00%");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfOneOfTheBalanceDataDoesNotHaveRequiredNumberOfColumns() {
        inputLines.set(6, "BALANCE");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfOneOfTheBalanceDataHasInvalidValueForMonth() {
        inputLines.set(6, "BALANCE INVALID");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

    @Test
    void shouldThrowExceptionIfRebalanceDataIsInvalid() {
        inputLines.set(6, "REBALANCE_INVALID");
        assertThrows(InvalidMyMoneyDataException.class, () -> validate(inputLines));
    }

}