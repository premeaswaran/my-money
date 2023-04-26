package com.mymoney.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;

/**
 * Represents the different months of the year.
 *
 * @author Prem Kumar Easwaran
 */
@Getter
@AllArgsConstructor
public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private Integer monthNumber;
    private static final HashSet<String> months = new HashSet<>();

    static {
        for (Month month : Month.values()) {
            months.add(month.name());
        }
    }

    public static boolean containsValue(String value) {
        return months.contains(value);
    }
}
