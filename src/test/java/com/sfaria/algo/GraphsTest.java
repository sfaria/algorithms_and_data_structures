package com.sfaria.algo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Scott Faria
 */
public class GraphsTest {

    @Test
    void testDijkstrasAlgorithm() {
        int[][] graph = {
           // S, A, B, C, D, E, F
            { 0, 3, 0, 2, 0, 0, 6 }, // S
            { 0, 0, 6, 0, 1, 0, 0 }, // A
            { 0, 0, 0, 0, 0, 1, 0 }, // B
            { 0, 2, 0, 0, 3, 0, 0 }, // C
            { 0, 0, 0, 0, 0, 4, 0 }, // D
            { 0, 0, 0, 0, 0, 0, 0 }, // E
            { 0, 0, 0, 0, 0, 2, 0 }  // F
        };

        {
            int vertex = 0;
            int[] actual = Graphs.dijkstra(graph, vertex);
                            // S, A, B, C, D, E, F
            int[] expected = { 0, 3, 9, 2, 4, 8, 6 };
            assertEquals(expected, actual);
        }
    }
}
