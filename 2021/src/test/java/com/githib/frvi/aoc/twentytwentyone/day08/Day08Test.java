package com.githib.frvi.aoc.twentytwentyone.day08;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    public static final String SAMPLE = "src/test/resources/input.08.sample";
    public static final String INPUT = "src/test/resources/input.08";

    @Test
    public void sampleOne() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE);
        final var expected = 26;
        final List<IO> ios = createIO(lines);

        // when
        int unique = 0;
        for (var io : ios) {
            final var output = io.output;
            for (var o : output) {
                final var digits = Digit.digitsOfLength(o.length());
                if (digits.size() == 1) {
                    unique++;
                }
            }
        }

        // then
        assertEquals(expected, unique);
    }

    @Test
    public void partOne() throws IOException {
        // given
        final var lines = Helper.readAllLines(INPUT);
        final var expected = 247;
        final List<IO> ios = createIO(lines);

        // when
        int unique = 0;
        for (var io : ios) {
            final var output = io.output;
            for (var o : output) {
                final var digits = Digit.digitsOfLength(o.length());
                if (digits.size() == 1) {
                    unique++;
                }
            }
        }

        // then
        assertEquals(expected, unique);
    }

    private ArrayList<IO> createIO(List<String> lines) {
        final var io = new ArrayList<IO>();

        for (String side : lines) {
            final var sides = side.split(" \\| ");
            final var input = sides[0].trim().split(" ");
            final var output = sides[1].trim().split(" ");
            io.add(new IO(input, output));
        }
        return io;
    }


    private record IO(String[] input, String[] output) {
    }

    private enum Digit {
        ZERO("abcefg"),
        ONE("cf"),
        TWO("acdeg"),
        THREE("acdfg"),
        FOUR("bcdf"),
        FIVE("abdfg"),
        SIX("abdefg"),
        SEVEN("acf"),
        EIGHT("abcdefg"),
        NINE("abcdfg");

        public final String letters;

        Digit(String letters) {
            this.letters = letters;
        }

        public static List<Digit> digitsOfLength(int length) {
            return Arrays.stream(Digit.values())
                    .filter(d -> d.letters.length() == length)
                    .toList();
        }
    }
}
