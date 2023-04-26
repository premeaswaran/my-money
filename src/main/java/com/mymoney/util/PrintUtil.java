package com.mymoney.util;

import java.util.Collection;

/**
 * This utility class takes care of basic string conversions and printing to console.
 *
 * @author Prem Kumar Easwaran
 */
public class PrintUtil {

    /**
     * Converts the given collection of Long numbers into a string separated by commas and prints it to the console.
     * @param resultArray Collection of Long numbers which is to be converted to string and printed.
     */
    public static void customPrint(Collection<Long> resultArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Long element : resultArray) {
            stringBuilder.append(element.toString()).append(" ");
        }
        System.out.println(stringBuilder);
    }
}