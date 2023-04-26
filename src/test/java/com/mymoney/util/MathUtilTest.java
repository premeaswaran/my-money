package com.mymoney.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathUtilTest extends MathUtil {

    @Test
    void shouldCalculatePercentage() {
        assertEquals(10d, percentage(100L, 1000L));
        assertEquals(5d, percentage(50L, 1000L));
        assertEquals(2.5d, percentage(25L, 1000L));
        assertEquals(2d, percentage(20L, 1000L));
    }

    @Test
    void shouldReturnShareValue() {
        assertEquals(100L, shareValueOf(10d, 1000L));
        assertEquals(50L, shareValueOf(5d, 1000L));
        assertEquals(25L, shareValueOf(2.5d, 1000L));
        assertEquals(20L, shareValueOf(2d, 1000L));
    }
}