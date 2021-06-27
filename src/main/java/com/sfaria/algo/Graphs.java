package com.sfaria.algo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Scott Faria
 */
public final class Graphs {

    // -------------------- Public Static Methods --------------------

    public static int[] dijkstra(int[][] graph, int source) {
        int[] dist = new int[graph.length];
        Queue<Integer> q = new LinkedList<>();
        // populate the distances with MAX_VALUE for all vertices besides
        // ourselves.
        for (int vertex = 0; vertex < graph.length; vertex++) {
            int distance = vertex == source ? 0 : Integer.MAX_VALUE;
            dist[vertex] = distance;
            q.add(vertex);
        }

        Set<Integer> visited = new HashSet<>();
        while (!q.isEmpty()) {
            int v = findClosest(dist, q);
            q.remove(v);

            for (int u = 0; u < graph.length; u++) {
                if (graph[v][u] != 0) {
                    // neighbor
                    int distance = dist[v] + graph[v][u];
                    if (distance < dist[u]) {
                        dist[u] = distance;
                    }
                }
            }
        }
        return dist;
    }

    // -------------------- Public Static Methods --------------------

    private static int findClosest(int[] dist, Queue<Integer> q) {
        int closestDistance = Integer.MAX_VALUE;
        int closestVertex = -1;
        for (int v : q) {
            int distance = dist[v];
            if (distance < closestDistance) {
                closestDistance = distance;
                closestVertex = v;
            }
        }
        return closestVertex;
    }


}
