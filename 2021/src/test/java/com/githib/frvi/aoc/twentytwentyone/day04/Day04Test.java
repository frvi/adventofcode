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
        final var sampleLines = Helper.readAllLines(SAMPLE);
        final var expected = 4512;
        final var numbers = getNumbers(sampleLines);
        final List<Brick> bricks = createBricks(sampleLines);
        List<Brick> winners = List.of();
        int winningNumber = 0;

        // when
        for (var number :
                numbers) {
            bricks.forEach(brick -> brick.mark(number));

            winners = bricks.stream()
                    .filter(Brick::hasWon)
                    .toList();

            if (winners.size() == 1) {
                winningNumber = number;
                break;
            }
        }

        // then
        assertEquals(3, bricks.size());
        assertEquals(1, winners.size());

        final var unmarkedSum = winners.get(0).unmarkedSum();

        assertEquals(expected, unmarkedSum * winningNumber);
    }

    @Test
    public void testOne() throws IOException {
        // given
        final var sampleLines = Helper.readAllLines(INPUT);
        final int expectedSum = 29440;
        final int expectedWinners = 1;

        final var numbers = getNumbers(sampleLines);
        final List<Brick> bricks = createBricks(sampleLines);

        List<Brick> winners = new ArrayList<>();
        int winningNumber = 0;

        // when
        for (var number : numbers) {
            winners = bricks.stream()
                    .peek(brick -> brick.mark(number))
                    .filter(Brick::hasWon)
                    .toList();

            if (!winners.isEmpty()) {
                winningNumber = number;
                break;
            }
        }

        // then
        assertEquals(expectedWinners, winners.size());

        final var unmarkedSum = winners.get(0).unmarkedSum();

        assertEquals(expectedSum, unmarkedSum * winningNumber);
    }

    @Test
    public void testTwo() throws IOException {
        // given
        final var sampleLines = Helper.readAllLines(INPUT);
        final var expected = 13884;
        List<Brick> winners = new ArrayList<>();
        int totalWinners = 0;
        int winningNumber = 0;
        final var numbers = getNumbers(sampleLines);
        List<Brick> bricks = createBricks(sampleLines);
        final int totalBricks = bricks.size();

        // when
        for (var number : numbers) {

            var currentWinners = bricks.stream()
                    .peek(brick -> brick.mark(number))
                    .filter(Brick::hasWon)
                    .toList();

            winners.addAll(currentWinners);
            bricks.removeAll(winners);

            final int previousWinners = winners.size();

            if (previousWinners > totalWinners) {
                winningNumber = number;
            }
            totalWinners = previousWinners;
            if (previousWinners == totalBricks) {
                break;
            }
        }

        // then
        final var finalWinner = winners.get(winners.size() - 1);
        final var unmarkedSum = finalWinner.unmarkedSum();
        final var actual = unmarkedSum * winningNumber;
        assertEquals(expected, actual);
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
        List<List<Dot>> rows;
        List<List<Dot>> cols;
        int lastNumber;

        Brick(List<List<Dot>> rows) {
            this.rows = rows;
            this.cols = createCols(rows);
        }

        private List<List<Dot>> createCols(List<List<Dot>> rows) {
            List<List<Dot>> cols = new ArrayList<>(List.of());

            for (var row :
                    rows) {
                cols.add(new ArrayList<>(row.size()));
            }

            for (int i = 0; i < rows.size(); i++) {

                final var row = rows.get(i);

                for (int j = 0; j < row.size(); j++) {
                    final var dot = row.get(j);
                    cols.get(j).add(i, dot);
                }
            }
            return cols;
        }

        void mark(int number) {
            for (List<Dot> row : this.rows) {
                row.forEach(d -> {
                    if (d.number == number) {
                        d.checked = true;
                        this.lastNumber = number;
                    }
                });
            }
            for (List<Dot> col : this.cols) {
                col.forEach(d -> {
                    if (d.number == number) {
                        d.checked = true;
                        this.lastNumber = number;
                    }
                });
            }
        }

        public boolean hasWon() {
            for (var row :
                    this.rows) {
                final var count = row.stream().filter(dot -> dot.checked).count();
                if (count == 5) {
                    return true;
                }
            }
            for (var col :
                    this.cols) {
                final var count = col.stream().filter(dot -> dot.checked).count();
                if (count == 5) {
                    return true;
                }
            }
            return false;
        }

        public int unmarkedSum() {
            int unmarkedSum = 0;
            for (var row :
                    this.rows) {
                for (var number :
                        row) {
                    if (!number.checked) {
                        unmarkedSum += number.number;
                    }
                }
            }
            return unmarkedSum;
        }
    }
}

