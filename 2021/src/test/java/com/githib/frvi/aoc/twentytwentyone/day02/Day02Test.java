package com.githib.frvi.aoc.twentytwentyone.day02;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.githib.frvi.aoc.twentytwentyone.helper.Helper.readAllLines;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    public static final String INPUT = "src/test/resources/input.02";

    @Test
    public void partOne() throws IOException {
        final var lines = readAllLines(INPUT);

        int forward = 0;
        int down = 0;
        int up = 0;
        int depth = 0;

        for (String line : lines) {
            final var s = line.split(" ");
            switch (s[0]) {
                case "up" -> up += toInt(s[1]);
                case "down" -> down += toInt(s[1]);
                case "forward" -> {
                    forward += toInt(s[1]);

                }

            }
        }

        depth = down - up;

        System.out.println(depth * forward);

    }

    @Test
    public void partTwo() throws IOException {
        // given
        final var lines = readAllLines(INPUT);
        final var expected = 1749524700;
        long forward = 0;
        long depth = 0;
        long aim = 0;

        // when
        final var operations = lines.stream()
                .map(Operation::new)
                .toList();

        for (Operation o : operations) {
            switch (o.direction) {
                case "up" -> aim -= o.move;
                case "down" -> aim += o.move;
                case "forward" -> {
                    forward += o.move;
                    depth += aim * o.move;
                }
            }
        }

        // then
        final var actual = depth * forward;
        assertEquals(expected, actual);
    }

    private int toInt(String s2) {
        return Integer.parseInt(s2);
    }


    private static class Operation {
        private final String direction;
        private final long move;

        public Operation(String line) {
            final var s = line.split(" ");
            this.direction = s[0];
            this.move = Integer.parseInt(s[1]);
        }
    }
}
