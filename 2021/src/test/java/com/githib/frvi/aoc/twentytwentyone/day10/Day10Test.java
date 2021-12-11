package com.githib.frvi.aoc.twentytwentyone.day10;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    public static final String SAMPLE = "src/test/resources/input.10.sample";
    public static final String INPUT = "src/test/resources/input.10";

    @Test
    public void sampleOne() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE);
        final int expected = 26397;

        // when

        // then
        final var actual = 0;
        assertEquals(expected, actual);
    }
}
