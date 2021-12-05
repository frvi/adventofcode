package com.githib.frvi.aoc.twentytwentyone.day05;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {

    public static final String SAMPLE = "src/test/resources/input.05.sample";
    public static final String INPUT = "src/test/resources/input.05";

    @Test
    public void sample() throws IOException {
        // given
        final int expected = 5;
        final var input = Helper.readAllLines(SAMPLE);

        // when
        final var actual = overlaps(
                lines(input)
                        .stream()
                        .filter(line -> line.isVertical() || line.isHorizontal())
                        .map(Line::coordinates)
                        .flatMap(Collection::stream)
                        .toList()
        );

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testOne() throws IOException {
        // given
        final int expected = 6189;
        final var input = Helper.readAllLines(INPUT);

        // when
        final var actual = overlaps(
                lines(input)
                        .stream()
                        .filter(line -> line.isVertical() || line.isHorizontal())
                        .map(Line::coordinates)
                        .flatMap(Collection::stream)
                        .toList()
        );

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void sampleTwo() throws IOException {
        // given
        final int expected = 12;
        final var input = Helper.readAllLines(SAMPLE);

        // when
        final var actual = overlaps(
                lines(input)
                        .stream()
                        .map(Line::coordinates)
                        .flatMap(Collection::stream)
                        .toList()
        );

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testTwo() throws IOException {
        // given
        final int expected = 19164;
        final var input = Helper.readAllLines(INPUT);

        // when
        final var actual = overlaps(
                lines(input)
                        .stream()
                        .map(Line::coordinates)
                        .flatMap(Collection::stream)
                        .toList()
        );

        // then
        assertEquals(expected, actual);
    }

    private List<Line> lines(List<String> input) {
        final var pattern = Pattern.compile("(?<x1>\\d+),(?<y1>\\d+) -> (?<x2>\\d+),(?<y2>\\d+)");
        return input.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .map(matcher -> {
                    final int x1 = Integer.parseInt(matcher.group("x1"));
                    final int y1 = Integer.parseInt(matcher.group("y1"));
                    final int x2 = Integer.parseInt(matcher.group("x2"));
                    final int y2 = Integer.parseInt(matcher.group("y2"));
                    return new Line(new Coordinate(x1, y1), new Coordinate(x2, y2));
                })
                .toList();
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

    private record Line(Coordinate start, Coordinate stop) {
        int minX() {
            return Math.min(start.x, stop.x);
        }

        int maxX() {
            return Math.max(start.x, stop.x);
        }

        int minY() {
            return Math.min(start.y, stop.y);
        }

        int maxY() {
            return Math.max(start.y, stop.y);
        }

        boolean isHorizontal() {
            return start.y == stop.y;
        }

        boolean isVertical() {
            return start.x == stop.x;
        }

        List<Coordinate> coordinates() {
            final var coordinates = new ArrayList<Coordinate>();
            if (this.isVertical()) {
                for (int j : createRange(this.minY(), this.maxY())) {
                    coordinates.add(new Coordinate(this.start.x, j));
                }
            } else if (this.isHorizontal()) {
                for (int j : createRange(this.minX(), this.maxX())) {
                    coordinates.add(new Coordinate(j, this.start.y));
                }
            } else {
                final var xSort = this.start.x > this.stop.x ? 1 : -1;
                final var ySort = this.start.y > this.stop.y ? 1 : -1;
                var xs = createRange(this.minX(), this.maxX(), xSort);
                var ys = createRange(this.minY(), this.maxY(), ySort);
                for (int i = 0; i < xs.length; i++) {
                    coordinates.add(new Coordinate(xs[i], ys[i]));
                }
            }
            return coordinates;
        }

        private int[] createRange(int startInclusive, int stopInclusive) {
            return createRange(startInclusive, stopInclusive, 1);
        }

        private int[] createRange(int startInclusive, int stopInclusive, int i) {
            return IntStream.range(startInclusive, stopInclusive + 1)
                    .map(a -> a * i)
                    .sorted()
                    .map(a -> a * i)
                    .toArray();
        }
    }

    private record Coordinate(int x, int y) {
    }
}
