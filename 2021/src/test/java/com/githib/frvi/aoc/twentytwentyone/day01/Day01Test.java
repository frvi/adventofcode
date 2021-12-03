package com.githib.frvi.aoc.twentytwentyone.day01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day01Test {

    public static final String INPUT = "src/test/resources/input.01";

    @Test
    void should_ReadAllLinesFromFile() throws IOException {
        // Given
        Path path = Paths.get(INPUT);
        final String expectedFirstLine = "193";
        final int expectedLines = 2000;

        // When
        final var allLines = Files.readAllLines(path);
        final var read = allLines.get(0);

        // Then
        assertEquals(expectedLines, allLines.size());
        assertEquals(expectedFirstLine, read);
    }

    @Test
    void partOne() throws IOException {
        // given
        final var allLines = readAllLines(INPUT);
        final var expected = 1832;

        // when
        int larger = getLarger(allLines);

        // then
        assertEquals(expected, larger);
    }

    @Test
    void partTwo() throws IOException {
        // Given
        final var allLines = readAllLines(INPUT);
        final var expected = 1858;

        // When
        var slides = getSlidingWindowsOfThree(allLines);
        int larger = getLarger(slides);

        // Then
        assertEquals(expected, larger);
    }

    private List<Integer> getSlidingWindowsOfThree(List<Integer> allLines) {
        var slides = new ArrayList<Integer>();
        for (int i = 0; i < allLines.size(); i++) {
            if (i + 2 < allLines.size()) {
                slides.add(allLines.get(i) + allLines.get(i + 1) + allLines.get(i + 2));
            }
        }
        return slides;
    }

    private int getLarger(List<Integer> numbers) {
        int larger = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0) {
                continue;
            }
            if (numbers.get(i) > numbers.get(i - 1)) {
                larger++;
            }
        }
        return larger;
    }

    List<Integer> readAllLines(String source) throws IOException {
        Path path = Paths.get(source);

        return Files.readAllLines(path)
                .stream()
                .map(Integer::parseInt)
                .toList();
    }
}