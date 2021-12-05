package com.githib.frvi.aoc.twentytwentyone.day05;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    public static final String SAMPLE = "src/test/resources/input.05.sample";
    public static final String INPUT = "src/test/resources/input.05";

    @Test
    public void sample() throws IOException {
        final int expected = 5;
        final var lines = Helper.readAllLines(SAMPLE);

        // when
        final List<Pair> pairs = pairs(lines);
        final List<Coordinate> straightLines = straightLines(pairs);
        final long actual = overlaps(straightLines);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testOne() throws IOException {
        final int expected = 6189;
        final var lines = Helper.readAllLines(INPUT);

        // when
        final List<Pair> pairs = pairs(lines);
        final List<Coordinate> straightLines = straightLines(pairs);
        final long actual = overlaps(straightLines);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void sampleTwo() throws IOException {
        final int expected = 12;
        final var lines = Helper.readAllLines(SAMPLE);

        // when
        final List<Pair> pairs = pairs(lines);
        final List<Coordinate> straightLines = straightLines(pairs);
        final List<Coordinate> diagonalLines = diagonalLines(pairs);
        final var allLines = Stream.concat(straightLines.stream(), diagonalLines.stream()).collect(Collectors.toList());
        final long actual = overlaps(allLines);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testTwo() throws IOException {
        final int expected = 19164;
        final var lines = Helper.readAllLines(INPUT);

        // when
        final List<Pair> pairs = pairs(lines);
        final List<Coordinate> straightLines = straightLines(pairs);
        final List<Coordinate> diagonalLines = diagonalLines(pairs);
        final var allLines = Stream.concat(straightLines.stream(), diagonalLines.stream()).collect(Collectors.toList());
        final long actual = overlaps(allLines);

        // then
        assertEquals(expected, actual);
    }

    private List<Pair> pairs(List<String> lines) {
        var pairs = new ArrayList<Pair>();
        for (var line : lines) {
            final var coordinates = line.split(" -> ");
            final var start = coordinates[0].trim().split(",");
            final var end = coordinates[1].trim().split(",");
            final int x1 = Integer.parseInt(start[0]);
            final int y1 = Integer.parseInt(start[1]);
            final int x2 = Integer.parseInt(end[0]);
            final int y2 = Integer.parseInt(end[1]);
            pairs.add(new Pair(new Coordinate(x1, y1), new Coordinate(x2, y2)));
        }
        return pairs;
    }

    private List<Coordinate> straightLines(List<Pair> pairs) {
        List<Coordinate> horizontal = new ArrayList<>();
        List<Coordinate> vertical = new ArrayList<>();
        for (var pair : pairs) {
            final var start = pair.start;
            final var stop = pair.stop;
            final var x1 = start.x;
            final var x2 = stop.x;
            final var y1 = start.y;
            final var y2 = stop.y;
            final var dx = x2 - x1;
            final var dy = y2 - y1;

            if (dx == 0) {
                var ys = dy > 0 ? createRange(y1, y2) : reverseRange(y1, y2);
                vertical.addAll(ys.stream().map(y -> new Coordinate(x1, y)).toList());
            }
            if (dy == 0) {
                var xs = dx > 0 ? createRange(x1, x2) : reverseRange(x1, x2);
                horizontal.addAll(xs.stream().map(x -> new Coordinate(x, y1)).toList());
            }
        }
        return Stream.of(horizontal, vertical)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Coordinate> diagonalLines(List<Pair> pairs) {
        final var diagonal = new ArrayList<Coordinate>();
        for (var pair : pairs) {
            final var start = pair.start;
            final var stop = pair.stop;
            final var x1 = start.x;
            final var x2 = stop.x;
            final var y1 = start.y;
            final var y2 = stop.y;
            final var dx = x2 - x1;
            final var dy = y2 - y1;

            if (dx == 0 || dy == 0) {
                continue;
            }

            var xs = dx > 0 ? createRange(x1, x2) : reverseRange(x1, x2);
            var ys = dy > 0 ? createRange(y1, y2) : reverseRange(y1, y2);

            for (int i = 0; i < xs.size(); i++) {
                diagonal.add(new Coordinate(xs.get(i), ys.get(i)));
            }
        }
        return diagonal;
    }

    private long overlaps(List<Coordinate> coordinates) {
        final var map = new HashMap<Coordinate, Integer>();
        for (var coordinate : coordinates) {
            int value = map.getOrDefault(coordinate, 0);
            value++;
            map.put(coordinate, value);
        }
        return map.values().stream().filter(v -> v >= 2).count();
    }

    private List<Integer> createRange(int startInclusive, int stopInclusive) {
        return IntStream.range(startInclusive, stopInclusive + 1).boxed().toList();
    }

    private List<Integer> reverseRange(int startInclusive, int stopInclusive) {
        return createRange(stopInclusive, startInclusive).stream().sorted(Collections.reverseOrder()).toList();
    }

    private record Pair(Coordinate start, Coordinate stop) {
    }

    private record Coordinate(int x, int y) {
    }
}
