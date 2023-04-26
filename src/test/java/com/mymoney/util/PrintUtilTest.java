package com.mymoney.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintUtilTest extends PrintUtil {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final PrintStream actualOut = System.out;

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(outStream));
    }

    @AfterEach
    void restore() {
        System.setOut(actualOut);
    }

    @Test
    void shouldPrintListOfLongInCustomFormat() {
        List<Long> inputList = new ArrayList<>();
        inputList.add(10000L);
        inputList.add(2000L);

        customPrint(inputList);
        assertEquals("10000 2000", outStream.toString().trim());
    }
}