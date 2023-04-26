package com.mymoney.util;

/**
 * This utility class performs basic arithmetic calculations.
 *
 * @author Prem Kumar Easwaran
 */
public class MathUtil {

    /**
     * Calculates the weightage/percentage of the share value in the given total value.
     * @param shareValue Value for which the percentage is to be calculated.
     * @param totalValue Total value out of which the percentage is to be calculated.
     * @return Weightage/percentage of the shareValue in the totalValue in Double (percentage).
     */
    public static Double percentage(Long shareValue, Long totalValue) {
        return (Double.valueOf(shareValue) / Double.valueOf(totalValue) * 100);
    }

    /**
     * Calculates the share value for the given percentage out of the total value.
     * @param percentage Percentage for which the share value is to be calculated.
     * @param totalValue Total value out of which the share value is to be calculated.
     * @return Share value of the percentage in the totalValue in Double.
     */
    public static Long shareValueOf(Double percentage, Long totalValue) {
        return (long) (totalValue * percentage) / 100L;
    }

    /**
     * This method returns true if the given text is a possible Long value and false if not.
     * @param text Input text to be checked
     * @return True if the given text is a possible Long value, False if not.
     */
    public static boolean isLongValue(String text) {
        try {
            Long.parseLong(text);
        } catch (NumberFormatException exception) {
            return true;
        }
        return false;
    }
}
