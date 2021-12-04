package com.githib.frvi.aoc.twentytwentyone.day04;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    public static final String SAMPLE = "src/test/resources/input.04.sample";
    public static final String INPUT = "src/test/resources/input.04";


    @Test
    public void sample() throws IOException {
        // given
        final var expected = 4512;
        final var sampleLines = Helper.readAllLines(SAMPLE);
        final var numbers = getNumbers(sampleLines);
        final List<Brick> bricks = createBricks(sampleLines);

        // when
        final Brick brick = getFirstBingo(numbers, bricks);

        // then
        assertEquals(expected, brick.uncheckedSum() * brick.latestNumber);
    }

    @Test
    public void testOne() throws IOException {
        // given
        final int expectedSum = 29440;
        final var sampleLines = Helper.readAllLines(INPUT);
        final var numbers = getNumbers(sampleLines);
        final List<Brick> bricks = createBricks(sampleLines);

        // when
        final var brick = getFirstBingo(numbers, bricks);

        // then
        assertEquals(expectedSum, brick.uncheckedSum() * brick.latestNumber);
    }

    @Test
    public void testTwo() throws IOException {
        // given
        final var expected = 13884;
        final var sampleLines = Helper.readAllLines(INPUT);
        final var numbers = getNumbers(sampleLines);
        List<Brick> bricks = createBricks(sampleLines);

        // when
        Brick brick = getLastBingo(numbers, bricks);

        // then
        final int actual = brick.uncheckedSum() * brick.latestNumber;
        assertEquals(expected, actual);
    }

    private List<Brick> getWinners(List<Integer> numbers, List<Brick> bricks, boolean shouldGetFirst) {
        List<Brick> winners = new ArrayList<>();
        for (var number : numbers) {
            winners.addAll(bricks.stream()
                    .peek(brick -> brick.mark(number))
                    .filter(Brick::bingo)
                    .toList());
            bricks.removeAll(winners);

            if (shouldGetFirst && winners.size() == 1) {
                break;
            }

            if (bricks.isEmpty()) {
                break;
            }
        }
        return winners;
    }

    private Brick getFirstBingo(List<Integer> numbers, List<Brick> bricks) {
        return getWinners(numbers, bricks, true).get(0);
    }

    private Brick getLastBingo(List<Integer> numbers, List<Brick> bricks) {
        final int totalBricks = bricks.size();
        return getWinners(numbers, bricks, false).get(totalBricks - 1);
    }

    private List<Integer> getNumbers(List<String> lines) {
        return Arrays.stream(lines.remove(0).split(",")).map(Integer::parseInt).toList();
    }

    private List<Brick> createBricks(List<String> lines) {
        final var cleaned = lines.stream()
                .map(String::trim)
                .map(l -> l.split("\\s+"))
                .filter(l -> l.length > 1)
                .map(n -> Arrays.stream(n).map(a -> new Dot(Integer.parseInt(a), false)).toList())
                .toList();

        return Lists.partition(cleaned, 5)
                .stream()
                .map(Brick::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static class Dot {
        int number;
        boolean checked;

        Dot(int number, boolean checked) {
            this.number = number;
            this.checked = checked;
        }
    }

    private static class Brick {
        Dot[][] dots;
        int latestNumber;

        Brick(List<List<Dot>> dots) {
            this.dots = buildBrick(dots);
        }

        private Dot[][] buildBrick(List<List<Dot>> rows) {
            final var size = rows.size();
            Dot[][] dots = new Dot[size][size];
            for (int i = 0; i < size; i++) {
                final var row = rows.get(i);
                for (int j = 0; j < row.size(); j++) {
                    dots[j][i] = row.get(j);
                }
            }
            return dots;
        }

        void mark(int number) {
            final int rows = this.dots.length;
            final int cols = this.dots[0].length;
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    final var dot = this.dots[i][j];
                    if (dot.number == number) {
                        dot.checked = true;
                        this.latestNumber = number;
                    }
                }
            }
        }

        public boolean bingo() {
            final var dots = this.dots;
            final int size = dots.length;
            for (int i = 0; i < size; i++) {
                List<Dot> row = new ArrayList<>(size);
                for (Dot[] dot : dots) {
                    row.add(dot[i]);
                }
                final boolean colBingo = Arrays.stream(dots[i]).allMatch(d -> d.checked);
                final boolean rowBingo = row.stream().allMatch(d -> d.checked);
                if (colBingo || rowBingo) {
                    return true;
                }
            }
            return false;
        }

        public int uncheckedSum() {
            return Arrays.stream(this.dots)
                    .map(column -> Arrays.stream(column)
                            .filter(d -> !d.checked).map(d -> d.number)
                            .reduce(0, Integer::sum))
                    .reduce(0, Integer::sum);
        }
    }
}

