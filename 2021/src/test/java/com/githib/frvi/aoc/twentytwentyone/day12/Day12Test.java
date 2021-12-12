package com.githib.frvi.aoc.twentytwentyone.day12;

import com.githib.frvi.aoc.twentytwentyone.helper.Helper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test {

    public static final String SAMPLE_LARGE = "src/test/resources/input.12.largeSample";
    public static final String SAMPLE = "src/test/resources/input.12.sample";
    public static final String INPUT = "src/test/resources/input.12";

    @Test
    public void sampleOne() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE);
        final int expected = 10;

        // when
        var graph = createGraph(lines);
        final var allPaths = graph.findPathsFromStartToEnd(true);

        // then
        assertEquals(6, graph.vertices().size());
        assertEquals(expected, allPaths.size());
    }

    @Test
    public void sampleLargeOne() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE_LARGE);
        final int expected = 19;

        // when
        var graph = createGraph(lines);
        final var allPaths = graph.findPathsFromStartToEnd(true);

        // then
        assertEquals(expected, allPaths.size());
    }

    @Test
    public void partOne() throws IOException {
        // given
        final var lines = Helper.readAllLines(INPUT);
        final int expected = 4970;

        // when
        var graph = createGraph(lines);
        final var allPaths = graph.findPathsFromStartToEnd(false);

        // then
        assertEquals(expected, allPaths.size());
    }

    @Test
    public void sampleTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE);
        final int expected = 36;

        // when
        var graph = createGraph(lines);
        final var allPaths = graph.findPathsFromStartToEndTwo(true);

        // then
        assertEquals(expected, allPaths.size());
    }

    @Test
    public void sampleLargeTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(SAMPLE_LARGE);
        final int expected = 103;

        // when
        var graph = createGraph(lines);
        final var allPaths = graph.findPathsFromStartToEndTwo(false);

        // then
        assertEquals(expected, allPaths.size());
    }

    @Test
    public void partTwo() throws IOException {
        // given
        final var lines = Helper.readAllLines(INPUT);
        final int expected = 137948;

        // when
        var graph = createGraph(lines);
        final var allPaths = graph.findPathsFromStartToEndTwo(false);

        // then
        assertEquals(expected, allPaths.size());
    }

    private Graph createGraph(List<String> lines) {
        final var splitLines = lines.stream().map(s -> s.split("-"))
                .map(a -> Arrays.stream(a).map(String::trim).toList())
                .toList();

        final var graph = new Graph(new HashMap<>());

        splitLines.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .forEach(graph::addVertex);

        for (var pair : splitLines) {
            graph.addEdge(pair.get(0), pair.get(1));
        }

        return graph;
    }

    private record Vertex(String label) {
    }

    private record Graph(Map<Vertex, Set<Vertex>> vertices) {
        void addVertex(String label) {
            vertices.putIfAbsent(new Vertex(label), new HashSet<>());
        }

        void addEdge(String labelA, String labelB) {
            final var vA = new Vertex(labelA);
            final var vB = new Vertex(labelB);
            vertices.get(vA).add(vB);
            vertices.get(vB).add(vA);
        }

        Set<Vertex> getAdjVertices(String label) {
            return vertices.get(new Vertex(label));
        }

        ArrayList<List<String>> findPathsFromStartToEnd(boolean shouldPrint) {
            var isVisited = new HashSet<String>();
            var allPaths = new ArrayList<List<String>>();
            var pathList = new ArrayList<String>();

            pathList.add("start");

            return findAllPathsUtil("start", "end", isVisited, pathList, allPaths, shouldPrint);
        }

        private ArrayList<List<String>> findAllPathsUtil(String start,
                                                         String end,
                                                         HashSet<String> isVisited,
                                                         List<String> localPathList,
                                                         ArrayList<List<String>> allPaths,
                                                         boolean shouldPrint) {

            if (start.equals(end)) {
                if (shouldPrint) {
                    System.out.println(localPathList);
                }
                // if match found then no need to traverse more till depth
                allPaths.add(localPathList);
                return allPaths;
            }

            // Mark the current node
            if (start.toLowerCase().equals(start)) {
                isVisited.add(start);
            }

            // Recur for all the vertices
            // adjacent to current vertex
            for (Vertex i : getAdjVertices(start)) {
                if (!isVisited.contains(i.label)) {
                    // store current node
                    // in path[]
                    localPathList.add(i.label);
                    findAllPathsUtil(i.label, end, isVisited, localPathList, allPaths, shouldPrint);
                    // remove current node
                    // in path[]
                    localPathList.remove(i.label);
                }
            }

            // Mark the current node
            isVisited.remove(start);
            return allPaths;
        }

        ArrayList<List<String>> findPathsFromStartToEndTwo(boolean shouldPrint) {
            var isVisited = new HashMap<String, Integer>();
            var allPaths = new ArrayList<List<String>>();
            var pathList = new ArrayList<String>();

            pathList.add("start");

            return findAllPathsUtilTwo("start", "end", isVisited, pathList, allPaths, shouldPrint);
        }

        private ArrayList<List<String>> findAllPathsUtilTwo(String start,
                                                            String end,
                                                            HashMap<String, Integer> isVisited,
                                                            List<String> localPathList,
                                                            ArrayList<List<String>> allPaths,
                                                            boolean shouldPrint) {

            if (start.equals(end)) {
                if (shouldPrint) {
                    System.out.println(localPathList);
                }
                // if match found then no need to traverse more till depth
                allPaths.add(new ArrayList<>(localPathList));
                return allPaths;
            }

            // Mark the current node
            if (start.equalsIgnoreCase("start") || start.equalsIgnoreCase("end")) {
                isVisited.put(start, 3);
            } else if (!isVisited.containsKey(start) && start.toLowerCase().equals(start)) {
                isVisited.put(start, 1);
            }

            for (Vertex i : getAdjVertices(start)) {
                if (!isVisited.containsKey(i.label)) {
                    localPathList.add(i.label);
                    findAllPathsUtilTwo(i.label, end, isVisited, localPathList, allPaths, shouldPrint);
                    for (int j = localPathList.size() - 1; j >= 0; j--) {
                        final var s = localPathList.get(j);
                        localPathList.remove(j);
                        if (s.equals(i.label)) {
                            break;
                        }
                    }
                } else {
                    var visits = isVisited.get(i.label);
                    final var isUppercase = i.label.equals(i.label.toUpperCase());
                    if (visits < 2 || isUppercase) {
                        if (isUppercase || visits == 0 || !isVisited.containsValue(2)) {
                            isVisited.replace(i.label, ++visits);
                            localPathList.add(i.label);
                            findAllPathsUtilTwo(i.label, end, isVisited, localPathList, allPaths, shouldPrint);
                            for (int j = localPathList.size() - 1; j >= 0; j--) {
                                final var s = localPathList.get(j);
                                localPathList.remove(j);
                                if (s.equals(i.label)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            // Remove current node visit
            if (isVisited.containsKey(start)) {
                var visits = isVisited.get(start);
                isVisited.replace(start, --visits);

            }
            return allPaths;
        }
    }
}
