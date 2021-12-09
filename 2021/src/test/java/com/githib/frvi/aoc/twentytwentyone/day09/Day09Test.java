package com.githib.frvi.aoc.twentytwentyone.day09;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {

    public static final String SAMPLE = "src/test/resources/input.09.sample";
    public static final String INPUT = "src/test/resources/input.09";

    @Test
    public void sampleOne() throws IOException {
        final var lines = Helper.readAllLines(SAMPLE);
        final var matrix = Helper.createIntMatrix(lines);
        final int expected = 15;


        var lowPoints = new ArrayList<Integer>();

        // when
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                final var depth = matrix[i][j];
                if (i == 0) {
                    final var below = matrix[i + 1][j];
                    if (j == 0) {
                        final var right = matrix[i][j + 1];
                        if (depth < below
                                && depth < right) {
                            lowPoints.add(depth);
                        }
                    } else if (j == matrix[i].length - 1) {
                        final var left = matrix[i][j - 1];
                        if (depth < below
                                && depth < left) {
                            lowPoints.add(depth);
                        }
                    } else {
                        final var right = matrix[i][j + 1];
                        final var left = matrix[i][j - 1];
                        if (depth < below
                                && depth < right
                                && depth < left) {
                            lowPoints.add(depth);
                        }
                    }
                } else if (i < matrix.length - 1) {
                    if (j == 0) {
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i + 1][j]
                                && depth < matrix[i][j + 1]) {
                            lowPoints.add(depth);
                        }
                    } else if (j == matrix[i].length - 1) { // last column
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i + 1][j]
                                && depth < matrix[i][j - 1]) {
                            lowPoints.add(depth);
                        }
                    } else {
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i + 1][j]
                                && depth < matrix[i][j + 1]
                                && depth < matrix[i][j - 1]) {
                            lowPoints.add(depth);
                        }
                    }
                } else if (i == matrix.length - 1) { // final row
                    if (j == 0) { // first column
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i][j + 1]) {
                            lowPoints.add(depth);
                        }
                    } else if (j == matrix[i].length - 1) { // last column
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i][j - 1]) {
                            lowPoints.add(depth);
                        }
                    } else if (depth < matrix[i - 1][j]
                            && depth < matrix[i][j + 1]
                            && depth < matrix[i][j - 1]) {
                        lowPoints.add(depth);
                    }
                }
            }
        }

        final var actual = lowPoints.stream().map(a -> a + 1).reduce(Integer::sum);
        assertEquals(expected, actual.orElse(0));
    }

    @Test
    public void partOne() throws IOException {
        final var lines = Helper.readAllLines(INPUT);
        final var matrix = Helper.createIntMatrix(lines);
        final int expected = 537;


        var lowPoints = new ArrayList<Integer>();

        // when
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                final var depth = matrix[i][j];
                if (i == 0) {
                    final var below = matrix[i + 1][j];
                    if (j == 0) {
                        final var right = matrix[i][j + 1];
                        if (depth < below
                                && depth < right) {
                            lowPoints.add(depth);
                        }
                    } else if (j == matrix[i].length - 1) {
                        final var left = matrix[i][j - 1];
                        if (depth < below
                                && depth < left) {
                            lowPoints.add(depth);
                        }
                    } else {
                        final var right = matrix[i][j + 1];
                        final var left = matrix[i][j - 1];
                        if (depth < below
                                && depth < right
                                && depth < left) {
                            lowPoints.add(depth);
                        }
                    }
                } else if (i < matrix.length - 1) {
                    if (j == 0) {
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i + 1][j]
                                && depth < matrix[i][j + 1]) {
                            lowPoints.add(depth);
                        }
                    } else if (j == matrix[i].length - 1) { // last column
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i + 1][j]
                                && depth < matrix[i][j - 1]) {
                            lowPoints.add(depth);
                        }
                    } else {
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i + 1][j]
                                && depth < matrix[i][j + 1]
                                && depth < matrix[i][j - 1]) {
                            lowPoints.add(depth);
                        }
                    }
                } else if (i == matrix.length - 1) { // final row
                    if (j == 0) { // first column
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i][j + 1]) {
                            lowPoints.add(depth);
                        }
                    } else if (j == matrix[i].length - 1) { // last column
                        if (depth < matrix[i - 1][j]
                                && depth < matrix[i][j - 1]) {
                            lowPoints.add(depth);
                        }
                    } else if (depth < matrix[i - 1][j]
                            && depth < matrix[i][j + 1]
                            && depth < matrix[i][j - 1]) {
                        lowPoints.add(depth);
                    }
                }
            }
        }

        final var actual = lowPoints.stream().map(a -> a + 1).reduce(Integer::sum);
        assertEquals(expected, actual.orElse(0));
    }

    @Test
    public void sampleTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE);
        final var matrix = Helper.createIntMatrix(lines);
        final int expected = 1134;

        // when
        final var sinks = sinks(matrix);
        final int actual = getProductOfTopThree(sinks);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void partTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(INPUT);
        final var matrix = Helper.createIntMatrix(lines);
        final int expected = 1142757;

        // when
        final var sinks = sinks(matrix);
        final int actual = getProductOfTopThree(sinks);

        // then
        assertEquals(expected, actual);
    }

    private int getProductOfTopThree(ArrayList<Integer> connected) {
        Collections.sort(connected);

        Collections.reverse(connected);

        return connected.subList(0, 3).stream().reduce((a, b) -> a * b).orElse(0);
    }

    static boolean isInSink(int[][] matrix, int row, int col, boolean[][] visited) {
        final var height = matrix.length;
        final var width = matrix[0].length;
        return (row >= 0)
                && (row < height)
                && (col >= 0)
                && (col < width)
                && (matrix[row][col] != 9 && !visited[row][col]);
    }

    static int depthFirstSearch(int[][] matrix, int row, int col, boolean[][] visited, int count) {
        var rowNbr = new int[]{-1, 0, 0, 1};
        var colNbr = new int[]{0, -1, 1, 0};

        visited[row][col] = true;

        for (int k = 0; k < 4; ++k)
            if (isInSink(matrix, row + rowNbr[k], col + colNbr[k], visited)) {
                count = depthFirstSearch(matrix, row + rowNbr[k], col + colNbr[k], visited, ++count);
            }

        return count;
    }

    static ArrayList<Integer> sinks(int[][] matrix) {
        final var height = matrix.length;
        final var width = matrix[0].length;

        boolean[][] visited = new boolean[height][width];

        var result = new ArrayList<Integer>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != 9 && !visited[i][j]) {
                    int count = 1; // first valid position
                    count = depthFirstSearch(matrix, i, j, visited, count);
                    result.add(count);
                }
            }
        }
        return result;
    }
}
