package com.mymoney.util;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This utility class takes care of File reader related functionalities.
 *
 * @author Prem Kumar Easwaran
 */
public class FileReaderUtil {

    /**
     * This method converts the content of the given file into list of strings.
     * @param filePath Absolute path to the input file which needs to be read.
     * @return List of strings, each element representing a line of the input file.
     */
    @SneakyThrows
    public static List<String> getInputDataFromFile(String filePath) {
        return Files.readAllLines(Paths.get(filePath));
    }
}
