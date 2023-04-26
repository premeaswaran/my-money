package com.mymoney.util;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderUtilTest extends FileReaderUtil {
    private static final String TEST_FILE_PATH = "src/test/resources/Test_Input.txt";
    private static final String INVALID_TEST_FILE_PATH = "src/test/resources/Invalid.txt";

    @Test
    void shouldReturnLinesOfTheFileAsList() {
        List<String> actual = getInputDataFromFile(TEST_FILE_PATH);
        List<String> expected = new ArrayList<>();
        expected.add("LINE_1");
        expected.add("LINE_2");
        expected.add("LINE_3");

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowFileNotFoundException() {
        assertThrows(NoSuchFileException.class, () -> getInputDataFromFile(INVALID_TEST_FILE_PATH));
    }

}