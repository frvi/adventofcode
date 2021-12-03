package com.githib.frvi.aoc.twentytwentyone.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Helper {
    public static List<String> readAllLines(String source) throws IOException {
        Path path = Paths.get(source);

        return Files.readAllLines(path);
    }

    public static int bitsToDecimal(String bits) {
        return Integer.parseInt(bits, 2);
    }

}
