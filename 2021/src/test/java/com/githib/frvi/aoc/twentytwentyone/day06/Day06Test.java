package com.githib.frvi.aoc.twentytwentyone.day06;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    public static final String SAMPLE = "src/test/resources/input.06.sample";
    public static final String INPUT = "src/test/resources/input.06";

    @Test
    public void sampleOne() throws IOException {
        // given
        final var input = Helper.readAllLines(SAMPLE).stream()
                .map(line -> line.trim().split(","))
                .flatMap(a -> Arrays.stream(a).map(Integer::parseInt))
                .toList();
        int time = 80;
        final var expected = 5934;
        var days = new int[9];

        for (var i : input) {
            days[i]++;
        }

        // when
        while (time > 0) {
            var t = new int[days.length];
            int reset = 0;
            for (int i = 0; i < days.length; i++) {
                final var fishesAtDay = days[i];
                if (i == 0) {
                    t[8] = fishesAtDay;
                    reset = fishesAtDay;
                    t[i] = 0;
                } else {
                    t[i - 1] = fishesAtDay;
                }
            }

            days = t;
            days[6] += reset;
            time--;
        }

        final var sum = Arrays.stream(days).sum();

        // then
        assertEquals(expected, sum);
    }

    @Test
    public void partOne() throws IOException {
        // given
        final var input = Helper.readAllLines(INPUT).stream()
                .map(line -> line.trim().split(","))
                .flatMap(a -> Arrays.stream(a).map(Integer::parseInt))
                .toList();
        int time = 80;
        final var expected = 375482;
        var days = new int[9];

        for (var i : input) {
            days[i]++;
        }

        // when
        while (time > 0) {
            var t = new int[days.length];
            int reset = 0;
            for (int i = 0; i < days.length; i++) {
                final var fishesAtDay = days[i];
                if (i == 0) {
                    t[8] = fishesAtDay;
                    reset = fishesAtDay;
                    t[i] = 0;
                } else {
                    t[i - 1] = fishesAtDay;
                }
            }

            days = t;
            days[6] += reset;
            time--;
        }

        final var sum = Arrays.stream(days).sum();

        // then
        assertEquals(expected, sum);
    }

    @Test
    public void partTwo() throws IOException {
        // given
        final var input = Helper.readAllLines(INPUT).stream()
                .map(line -> line.trim().split(","))
                .flatMap(a -> Arrays.stream(a).map(Integer::parseInt))
                .toList();
        int time = 256;
        final long expected = 1689540415957L;
        var days = new long[9];

        for (var i : input) {
            days[i]++;
        }

        // when
        while (time > 0) {
            var t = new long[days.length];
            long reset = 0;
            for (int i = 0; i < days.length; i++) {
                final var fishesAtDay = days[i];
                if (i == 0) {
                    t[8] = fishesAtDay;
                    reset = fishesAtDay;
                    t[i] = 0;
                } else {
                    t[i - 1] = fishesAtDay;
                }
            }

            days = t;
            days[6] += reset;
            time--;
        }

        final var sum = Arrays.stream(days).sum();

        // then
        assertEquals(expected, sum);
    }
}
