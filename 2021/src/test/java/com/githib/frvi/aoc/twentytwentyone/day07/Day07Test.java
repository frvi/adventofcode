package com.githib.frvi.aoc.twentytwentyone.day07;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {

    public static final String SAMPLE = "src/test/resources/input.07.sample";
    public static final String INPUT = "src/test/resources/input.07";

    @Test
    public void sampleOne() throws IOException {
        // given
        final var input = Helper.readIntList(SAMPLE);
        final var expected = 37;


        final var sorted = input.stream().sorted().toList();

        final var ints = new int[sorted.get(sorted.size() - 1) + 1];
        final var costs = new int[sorted.get(sorted.size() - 1) + 1];

        for (var crab : sorted) {
            ints[crab]++;
        }

        for (var target : sorted.stream().distinct().toList()) {
            int totalFuel = 0;
            for (int i = 0; i < ints.length; i++) {
                final var distance = Math.abs(i - target);
                final var fuelNeeded = distance * ints[i];
                totalFuel += fuelNeeded;
            }
            costs[target] = totalFuel;
        }

        final var actual = Arrays.stream(costs).filter(cost -> cost != 0).min();

        // then
        assertEquals(expected, actual.isPresent() ? actual.getAsInt() : 0);
    }

    @Test
    public void testOne() throws IOException {
        // given
        final var input = Helper.readIntList(INPUT);
        final var expected = 326132;


        final var sorted = input.stream().sorted().toList();

        final var ints = new int[sorted.get(sorted.size() - 1) + 1];
        final var costs = new int[sorted.get(sorted.size() - 1) + 1];

        for (var crab : sorted) {
            ints[crab]++;
        }

        for (var target : sorted.stream().distinct().toList()) {
            int totalFuel = 0;
            for (int i = 0; i < ints.length; i++) {
                final var distance = Math.abs(i - target);
                final var fuelNeeded = distance * ints[i];
                totalFuel += fuelNeeded;
            }
            costs[target] = totalFuel;
        }

        final var actual = Arrays.stream(costs).filter(cost -> cost != 0).min();

        // then
        assertEquals(expected, actual.isPresent() ? actual.getAsInt() : 0);
    }

    @Test
    public void sampleTwo() throws IOException {
        // given
        final var input = Helper.readIntList(SAMPLE);
        final var expected = 168;

        final var sorted = input.stream().sorted().toList();

        final var ints = new int[sorted.get(sorted.size() - 1) + 1];
        final var costs = new int[sorted.get(sorted.size() - 1) + 1];

        for (var crab : sorted) {
            ints[crab]++;
        }

        // when
        for (int target = 0; target < ints.length; target++) {
            int totalFuel = 0;
            for (int i = 0; i < ints.length; i++) {
                final var distance = Math.abs(i - target);
                long actualDistance = 0;
                for (int j = 0; j < distance; j++) {
                    actualDistance += j + 1;
                }
                final var fuelNeeded = actualDistance * ints[i];
                totalFuel += fuelNeeded;
            }
            costs[target] = totalFuel;
        }

        final var actual = Arrays.stream(costs).filter(cost -> cost != 0).min();

        // then
        assertEquals(expected, actual.isPresent() ? actual.getAsInt() : 0);
    }

    @Test
    public void testTwo() throws IOException {
        // given
        final var input = Helper.readIntList(INPUT);
        final var expected = 88612508;

        final var sorted = input.stream().sorted().toList();

        final var ints = new int[sorted.get(sorted.size() - 1) + 1];
        final var costs = new int[sorted.get(sorted.size() - 1) + 1];

        for (var crab : sorted) {
            ints[crab]++;
        }

        // when
        for (int target = 0; target < ints.length; target++) {
            int totalFuel = 0;
            for (int i = 0; i < ints.length; i++) {
                final var distance = Math.abs(i - target);
                long actualDistance = 0;
                for (int j = 0; j < distance; j++) {
                    actualDistance += j + 1;
                }
                final var fuelNeeded = actualDistance * ints[i];
                totalFuel += fuelNeeded;
            }
            costs[target] = totalFuel;
        }

        final var actual = Arrays.stream(costs).filter(cost -> cost != 0).min();

        // then
        assertEquals(expected, actual.isPresent() ? actual.getAsInt() : 0);
    }
}
