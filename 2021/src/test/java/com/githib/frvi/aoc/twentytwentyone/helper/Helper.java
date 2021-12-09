package com.githib.frvi.aoc.twentytwentyone.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static List<String> readAllLines(String source) throws IOException {
        Path path = Paths.get(source);

        return Files.readAllLines(path);
    }

    public static List<Integer> readIntList(String source) throws IOException {
        return readAllLines(source).stream()
                .map(line -> line.trim().split(","))
                .flatMap(a -> Arrays.stream(a).map(Integer::parseInt))
                .toList();
    }

    public static int[][] createIntMatrix(List<String> rows) {
        final var height = rows.size();
        final var width = (int)Arrays.stream(rows.get(0).split("")).count();
        final var matrix = new int[height][width];
        for (int i = 0; i < height; i++) {
            final var row = rows.get(i).split("");
            for (int j = 0; j < row.length; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }
        return matrix;
    }

    public static int bitsToDecimal(String bits) {
        return Integer.parseInt(bits, 2);
    }

}
