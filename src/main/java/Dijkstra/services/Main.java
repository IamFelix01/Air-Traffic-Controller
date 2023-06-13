package Dijkstra.services;

import org.sid.airtrafficcontrolbackend.Dijkstra.entities.Edge;
import org.sid.airtrafficcontrolbackend.Dijkstra.entities.Graph;

import java.util.ArrayList;
import java.util.List;

/*Just For Testing */

public class Main {

    public static void main(String[] args) {

        // initialize edges as per the above diagram
        // (u, v, w) represent edge from vertex `u` to vertex `v` having weight `w`
               List<Edge> edges = new ArrayList<>();
               Edge edge3= new Edge(2, 1, 76);
               Edge edge2 = new Edge(3, 1, 21);
               Edge edge1= new Edge(3, 2, 81);
                edges.add(edge1);
                edges.add(edge2);
                edges.add(edge3);

        // total number of nodes in the graph (labelled from 0 to 4)
        int n = 5;

        // construct graph
        Graph graph = new Graph(edges, n);

        // run the Dijkstra’s algorithm from every node
        //for (int source = 0; source < n; source++) {
        ArrayList<Integer> listeRoute;
        listeRoute=(ArrayList<Integer>) DijkstraImpl.findShortestPaths(graph, 2, 5,1);
        System.out.println(listeRoute);
        //}
    }

}
