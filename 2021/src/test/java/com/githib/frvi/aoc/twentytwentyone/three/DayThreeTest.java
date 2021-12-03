package com.githib.frvi.aoc.twentytwentyone.three;

import com.githib.frvi.aoc.twentytwentyone.helper.Comparor;
import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.githib.frvi.aoc.twentytwentyone.helper.Helper.bitsToDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayThreeTest {
    public static final String INPUT = "src/test/resources/three/input";

    @Test
    public void partOne() throws IOException {
        // given
        final var expected = 4118544;
        final var lines = Helper.readAllLines(INPUT);
        final var length = lines.get(0).length();

        // when
        final int[] sum = summarize(lines, length);

        var gamma = new StringBuilder();
        var epsilon = new StringBuilder();

        for (int i : sum) {
            gamma.append(i > 0 ? "1" : "0");
            epsilon.append(i < 0 ? "1" : "0");
        }

        // then
        final var actual = bitsToDecimal(gamma.toString()) * bitsToDecimal(epsilon.toString());
        assertEquals(expected, actual);
    }

    @Test
    public void partTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(INPUT);

        // when
        var oxygen = operate(lines, (a, b) -> a < b ? "0" : "1");
        var co2 = operate(lines, (a, b) -> a < b ? "1" : "0");

        // then
        assertEquals(3832770, oxygen * co2);
    }

    private int operate(List<String> lines, Comparor c) {
        final var length = lines.get(0).length();

        for (int i = 0; i < length; i++) {
            final int[] sum = summarize(lines, length);
            List<String> list = new ArrayList<>();
            for (String l : lines) {
                if (l.split("")[i].equals(c.compare(sum[i], 0))) {
                    list.add(l);
                }
            }
            if (list.size() == 1) {
                return Integer.parseInt(String.join("", list), 2);
            }
            lines = list;
        }
        return 0;
    }

    private int[] summarize(List<String> lines, int length) {
        final var sum = new int[length];

        for (String l : lines) {
            for (int i = 0; i < length; i++) {
                final var a = Integer.parseInt(l.split("")[i]);
                sum[i] += (a == 1) ? 1 : -1;
            }
        }
        return sum;
    }
}
