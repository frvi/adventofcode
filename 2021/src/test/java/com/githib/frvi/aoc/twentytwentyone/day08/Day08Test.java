package com.githib.frvi.aoc.twentytwentyone.day08;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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

    @Test
    public void exampleTwo() {
        // given
        final var lines = List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
        final List<IO> ios = createIO(lines);

        final var map = new HashMap<Integer, String>();

        // when
        for (var io : ios) {
            final var input = io.input;
            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 2) {
                    map.put(1, String.join("", signalWires));
                }
                if (i.length() == 3) {
                    map.put(7, String.join("", signalWires));
                }
                if (i.length() == 4) {
                    map.put(4, String.join("", signalWires));
                }
                if (i.length() == 7) {
                    map.put(8, String.join("", signalWires));
                }
            }
        }
        for (var io : ios) {
            final var input = io.input;
            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 6) {
                    if (signalWires.containsAll(Arrays.stream(map.get(4).split("")).toList())) {
                        map.put(9, String.join("", signalWires));
                    } else if (signalWires.containsAll(Arrays.stream(map.get(7).split("")).toList())) {
                        map.put(0, String.join("", signalWires));
                    } else {
                        map.put(6, String.join("", signalWires));
                    }
                }
            }
        }


        for (var io : ios) {
            final var input = io.input;
            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 5) {
                    if (signalWires.containsAll(Arrays.stream(map.get(1).split("")).toList())) {
                        map.put(3, String.join("", signalWires));
                    } else if (Arrays.stream(map.get(9).split("")).toList().containsAll(signalWires)) {
                        map.put(5, String.join("", signalWires));
                    } else {
                        map.put(2, String.join("", signalWires));
                    }
                }

            }

        }

        StringBuilder outputValue = new StringBuilder();

        for (var io : ios) {
            final var output = io.output;


            for (var i : output) {
                final var digit = String.join("", Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList());

                for (var kv : map.entrySet()) {
                    if (digit.equals(kv.getValue())) {
                        outputValue.append(kv.getKey());
                    }
                }
            }
        }

        // then
        var actual = Integer.parseInt(outputValue.toString());
        assertEquals(5353, actual);
    }

    @Test
    public void sampleTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE);
        final var expected = 61229;
        final List<IO> ios = createIO(lines);
        int actual = 0;

        // when
        for (var io : ios) {

            final var map = new HashMap<Integer, String>();


            final var input = io.input;
            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 2) {
                    map.put(1, String.join("", signalWires));
                }
                if (i.length() == 3) {
                    map.put(7, String.join("", signalWires));
                }
                if (i.length() == 4) {
                    map.put(4, String.join("", signalWires));
                }
                if (i.length() == 7) {
                    map.put(8, String.join("", signalWires));
                }
            }

            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 6) {
                    if (signalWires.containsAll(Arrays.stream(map.get(4).split("")).toList())) {
                        map.put(9, String.join("", signalWires));
                    } else if (signalWires.containsAll(Arrays.stream(map.get(7).split("")).toList())) {
                        map.put(0, String.join("", signalWires));
                    } else {
                        map.put(6, String.join("", signalWires));
                    }
                }
            }

            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 5) {
                    if (signalWires.containsAll(Arrays.stream(map.get(1).split("")).toList())) {
                        map.put(3, String.join("", signalWires));
                    } else if (Arrays.stream(map.get(9).split("")).toList().containsAll(signalWires)) {
                        map.put(5, String.join("", signalWires));
                    } else {
                        map.put(2, String.join("", signalWires));
                    }
                }

            }

            final var output = io.output;

            StringBuilder outputValue = new StringBuilder();
            for (var i : output) {
                final var digit = String.join("", Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList());

                for (var kv : map.entrySet()) {
                    if (digit.equals(kv.getValue())) {
                        outputValue.append(kv.getKey());
                        break;
                    }
                }
            }
            actual += Integer.parseInt(outputValue.toString());
        }

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void partTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(INPUT);
        final var expected = 933305;
        final List<IO> ios = createIO(lines);
        long actual = 0;

        // when
        for (var io : ios) {

            final var map = new HashMap<Integer, String>();


            final var input = io.input;
            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 2) {
                    map.put(1, String.join("", signalWires));
                }
                if (i.length() == 3) {
                    map.put(7, String.join("", signalWires));
                }
                if (i.length() == 4) {
                    map.put(4, String.join("", signalWires));
                }
                if (i.length() == 7) {
                    map.put(8, String.join("", signalWires));
                }
            }

            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 6) {
                    if (signalWires.containsAll(Arrays.stream(map.get(4).split("")).toList())) {
                        map.put(9, String.join("", signalWires));
                    } else if (signalWires.containsAll(Arrays.stream(map.get(7).split("")).toList())) {
                        map.put(0, String.join("", signalWires));
                    } else {
                        map.put(6, String.join("", signalWires));
                    }
                }
            }

            for (var i : input) {
                final var signalWires = Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList();

                if (i.length() == 5) {
                    if (signalWires.containsAll(Arrays.stream(map.get(1).split("")).toList())) {
                        map.put(3, String.join("", signalWires));
                    } else if (Arrays.stream(map.get(9).split("")).toList().containsAll(signalWires)) {
                        map.put(5, String.join("", signalWires));
                    } else {
                        map.put(2, String.join("", signalWires));
                    }
                }

            }

            final var output = io.output;

            StringBuilder outputValue = new StringBuilder();
            for (var i : output) {
                final var digit = String.join("", Arrays
                        .stream(i.split(""))
                        .sorted(Comparator.naturalOrder())
                        .toList());

                for (var kv : map.entrySet()) {
                    if (digit.equals(kv.getValue())) {
                        outputValue.append(kv.getKey());
                        break;
                    }
                }
            }
            actual += Integer.parseInt(outputValue.toString());
        }

        // then
        // 612290 too low
        assertEquals(expected, actual);
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
        ZERO(new int[]{1, 1, 1, 0, 1, 1, 1}),
        ONE(new int[]{0, 0, 1, 0, 0, 1, 0}),
        TWO(new int[]{1, 0, 1, 1, 1, 0, 1}),
        THREE(new int[]{1, 0, 1, 1, 0, 1, 1}),
        FOUR(new int[]{0, 1, 1, 1, 0, 1, 0}),
        FIVE(new int[]{1, 1, 0, 1, 0, 1, 1}),
        SIX(new int[]{1, 1, 0, 1, 1, 1, 1}),
        SEVEN(new int[]{1, 0, 1, 0, 0, 1, 0}),
        EIGHT(new int[]{1, 1, 1, 1, 1, 1, 1}),
        NINE(new int[]{1, 1, 1, 1, 0, 1, 1});

        public final int[] shape;

        Digit(int[] shape) {
            this.shape = shape;
        }

        public static List<Digit> digitsOfLength(int length) {
            return Arrays.stream(Digit.values())
                    .filter(d -> Arrays.stream(d.shape).filter(i -> i != 0).count() == length)
                    .toList();
        }

    }
}
