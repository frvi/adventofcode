package com.githib.frvi.aoc.twentytwentyone.day11;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    public static final String SAMPLE = "src/test/resources/input.11.sample";
    public static final String INPUT = "src/test/resources/input.11";

    @Test
    public void sampleOne() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE);
        final var matrix = Helper.createIntMatrix(lines);
        final int expected = 1656;

        // when
        // TODO (idea, for each step do):
        //  a. iterate every element, increase (if not 9 already) and copy to new matrix.
        //  b. iterate new matrix, check the neighbors of each element. for each 9 neighbor, increase element.
        //  c. iterate updated new matrix, replace each 9 with 0.

        // then
        final int actual = 0;
        assertEquals(expected, actual);
    }

}
