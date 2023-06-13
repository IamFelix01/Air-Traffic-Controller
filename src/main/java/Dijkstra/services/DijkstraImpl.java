package Dijkstra.services;

import Dijkstra.entities.*;
import Dijkstra.services.*;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DijkstraImpl {
    private static void getRoute(int[] prev, int i, List<Integer> route) {
        if (i >= 0) {
            getRoute(prev, prev[i], route);
            route.add(i);
        }
    }

    // Run Dijkstra’s algorithm on a given graph
    public static List<Integer> findShortestPaths(Graph graph, int AeroportDepartId, int n, int AeroportArriverId) {
        // create a min-heap and push source node having distance 0
        PriorityQueue<Node> minHeap;
        minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.add(new Node(AeroportDepartId, 0));
        // set initial distance from the source to `v` as infinity
        List<Integer> dist;
        dist = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));

        // distance from the source to itself is zero
        dist.set(AeroportDepartId, 0);
        System.out.println(dist);
        // boolean array to track vertices for which minimum
        // cost is already found
        boolean[] done = new boolean[n];
        done[AeroportDepartId] = true;

        // stores predecessor of a vertex (to a print path)
        int[] prev = new int[n];
        prev[AeroportDepartId] = -1;

        // run till min-heap is empty
        while (!minHeap.isEmpty()) {
            // Remove and return the best vertex
            Node node = minHeap.poll();

            // get the vertex number
            int u = node.vertex;

            // do for each neighbor `v` of `u`
            for (Edge edge : graph.adjList.get(u)) {
                int v = edge.AeroportArriverId;
                int weight = edge.weight;

                // Relaxation step
                if (!done[v] && (dist.get(u) + weight) < dist.get(v)) {
                    dist.set(v, dist.get(u) + weight);
                    prev[v] = u;
                    minHeap.add(new Node(v, dist.get(v)));
                }
            }

            // mark vertex `u` as done so it will not get picked up again
            done[u] = true;
        }

        List<Integer> route = new ArrayList<>();
        List<Integer> route1 = new ArrayList<>();
        if (AeroportArriverId != AeroportDepartId && dist.get(AeroportArriverId) != Integer.MAX_VALUE) {
            getRoute(prev, AeroportArriverId, route);
            System.out.printf("Path (%d —> %d): Minimum cost = %d, Route = %s\n",
                    AeroportDepartId, AeroportArriverId, dist.get(AeroportArriverId), route);

            for (Integer x : route) {
                route1.add(x);
            }
            route.clear();
        }

        return route1;
    }
}