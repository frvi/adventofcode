package com.githib.frvi.aoc.twentytwentyone.day05;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
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
        final var straightLines = new ArrayList<Point>();
        for (var pair : points) {
            final var start = pair.get(0);
            final var stop = pair.get(1);

            if (start.x == stop.x) {
                final var min = Math.min(start.y, stop.y);
                final var max = Math.max(start.y, stop.y);
                straightLines.addAll(IntStream.range(min, max + 1).mapToObj(y -> new Point(start.x, y)).toList());
            }
            if (start.y == stop.y) {
                final var min = Math.min(start.x, stop.x);
                final var max = Math.max(start.x, stop.x);
                straightLines.addAll(IntStream.range(min, max + 1).mapToObj(x -> new Point(x, start.y)).toList());
            }
        }
        return straightLines;
    }

    private List<Point> diagonalLines(List<List<Point>> pairs) {
        final var diagonalLines = new ArrayList<Point>();
        for (var pair : pairs) {
            final var start = pair.get(0);
            final var stop = pair.get(1);

            if (start.x < stop.x) {
                if (start.y > stop.y) {
                    final var startX = start.x;
                    final var stopX = stop.x;
                    final var startY = stop.y;
                    final var stopY = start.y;
                    final var xs = IntStream.range(startX, stopX + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    final var ys = IntStream.range(startY, stopY + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    Collections.reverse(ys);
                    for (int i = 0; i < xs.size(); i++) {
                        diagonalLines.add(new Point(xs.get(i), ys.get(i)));
                    }
                }
                if (start.y < stop.y) {
                    final var startX = start.x;
                    final var stopX = stop.x;
                    final var startY = start.y;
                    final var stopY = stop.y;
                    final var xs = IntStream.range(startX, stopX + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    final var ys = IntStream.range(startY, stopY + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    for (int i = 0; i < xs.size(); i++) {
                        diagonalLines.add(new Point(xs.get(i), ys.get(i)));
                    }
                }
            }
            if (start.x > stop.x) {
                if (start.y > stop.y) {
                    final var startX = stop.x;
                    final var stopX = start.x;
                    final var startY = stop.y;
                    final var stopY = start.y;
                    final var xs = IntStream.range(startX, stopX + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    final var ys = IntStream.range(startY, stopY + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    Collections.reverse(xs);
                    Collections.reverse(ys);
                    for (int i = 0; i < xs.size(); i++) {
                        diagonalLines.add(new Point(xs.get(i), ys.get(i)));
                    }
                }
                if (start.y < stop.y) {
                    final var startX = stop.x;
                    final var stopX = start.x;
                    final var startY = start.y;
                    final var stopY = stop.y;
                    final var xs = IntStream.range(startX, stopX + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    final var ys = IntStream.range(startY, stopY + 1).boxed().collect(Collectors.toCollection(ArrayList::new));
                    Collections.reverse(xs);
                    for (int i = 0; i < xs.size(); i++) {
                        diagonalLines.add(new Point(xs.get(i), ys.get(i)));
                    }
                }
            }
        }
        return diagonalLines;
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
}
