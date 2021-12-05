package com.githib.frvi.aoc.twentytwentyone.day05;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.awt.*;
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
        final List<List<Point>> pairs = pointPairs(lines);
        final List<Point> straightLines = straightLines(pairs);
        final long actual = overlaps(straightLines);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testOne() throws IOException {
        final int expected = 6189;
        final var lines = Helper.readAllLines(INPUT);

        // when
        final List<List<Point>> pairs = pointPairs(lines);
        final List<Point> straightLines = straightLines(pairs);
        final long actual = overlaps(straightLines);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void sampleTwo() throws IOException {
        final int expected = 12;
        final var lines = Helper.readAllLines(SAMPLE);

        // when
        final List<List<Point>> pairs = pointPairs(lines);
        final List<Point> straightLines = straightLines(pairs);
        final List<Point> diagonalLines = diagonalLines(pairs);
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
        final List<List<Point>> pairs = pointPairs(lines);
        final List<Point> straightLines = straightLines(pairs);
        final List<Point> diagonalLines = diagonalLines(pairs);
        final var allLines = Stream.concat(straightLines.stream(), diagonalLines.stream()).collect(Collectors.toList());
        final long actual = overlaps(allLines);

        // then
        assertEquals(expected, actual);
    }

    private List<List<Point>> pointPairs(List<String> lines) {
        var points = new ArrayList<List<Point>>();
        for (var line : lines) {
            final var coordinates = line.split(" -> ");
            final var start = coordinates[0].trim().split(",");
            final var end = coordinates[1].trim().split(",");
            final int x1 = Integer.parseInt(start[0]);
            final int y1 = Integer.parseInt(start[1]);
            final int x2 = Integer.parseInt(end[0]);
            final int y2 = Integer.parseInt(end[1]);
            final var p1 = new Point(x1, y1);
            final var p2 = new Point(x2, y2);
            points.add(List.of(p1, p2));
        }
        return points;
    }

    private List<Point> straightLines(List<List<Point>> points) {
        List<Point> horizontal = new ArrayList<>();
        List<Point> vertical = new ArrayList<>();
        for (var pair : points) {
            final var start = pair.get(0);
            final var stop = pair.get(1);

            final var x1 = start.x;
            final var x2 = stop.x;
            final var y1 = start.y;
            final var y2 = stop.y;
            final var dx = x2 - x1;
            final var dy = y2 - y1;

            if (dx == 0) {
                var ys = dy > 0 ? createRange(y1, y2) : reverseRange(y1, y2);
                vertical.addAll(ys.stream().map(y -> new Point(x1, y)).toList());
            }
            if (dy == 0) {
                var xs = dx > 0 ? createRange(x1, x2) : reverseRange(x1, x2);
                horizontal.addAll(xs.stream().map(x -> new Point(x, y1)).toList());
            }
        }
        return Stream.of(horizontal, vertical)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Point> diagonalLines(List<List<Point>> pairs) {
        final var diagonal = new ArrayList<Point>();
        for (var pair : pairs) {
            final var start = pair.get(0);
            final var stop = pair.get(1);
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
                diagonal.add(new Point(xs.get(i), ys.get(i)));
            }
        }
        return diagonal;
    }

    private long overlaps(List<Point> points) {
        final var map = new HashMap<Point, Integer>();
        for (var point : points) {
            int value = map.getOrDefault(point, 0);
            value++;
            map.put(point, value);
        }
        return map.values().stream().filter(v -> v >= 2).count();
    }

    private List<Integer> createRange(int startInclusive, int stopInclusive) {
        return IntStream.range(startInclusive, stopInclusive + 1).boxed().toList();
    }

    private List<Integer> reverseRange(int startInclusive, int stopInclusive) {
        return createRange(stopInclusive, startInclusive).stream().sorted(Collections.reverseOrder()).toList();
    }
}
