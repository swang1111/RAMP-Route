// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {

    int vertices;
    ArrayList<Edge>[] adjacencylist;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencylist = new ArrayList[vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            adjacencylist[i] = new ArrayList<>();
        }
    }

    public void addEdge(Node source, Node destination) {
        // calculating weight as distance between two Cartesian coordinates
        int weight = calculateDist(source, destination);

        Edge edge = new Edge(source, destination, weight);
        adjacencylist[source.index].add(edge);

        // for undirected graph
        edge = new Edge(destination, source, weight);
        adjacencylist[destination.index].add(edge);
    }

    public int calculateDist(Node source, Node destination) {
        return (int) (Math.pow(destination.x - source.x, 2) + Math.pow(destination.y - source.y, 2));
    }

    public void dijkstra_PrintPaths(Node sourceNode) {

        int sourceVertex = sourceNode.index;

        boolean[] SPT = new boolean[vertices];
        //distance used to store the distance of vertex from a source
        int[] distance = new int[vertices];

        int[] parentVertex = new int[vertices];

        //parent of the source vertex will be -1
        parentVertex[0] = -1;

        //Initialize all the distance to infinity
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        //Initialize priority queue
        //override the comparator to do the sorting based keys
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                //sort using distance values
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1 - key2;
            }
        });
        //create the pair for for the first index, 0 distance 0 index
        distance[0] = 0;
        Pair<Integer, Integer> p0 = new Pair<>(distance[0], 0);
        //add it to pq
        pq.add(p0);

        //while priority queue is not empty
        while (!pq.isEmpty()) {
            //extract the min
            Pair<Integer, Integer> extractedPair = pq.poll();

            //extracted vertex
            int extractedVertex = extractedPair.getValue();
            if (!SPT[extractedVertex]) {
                SPT[extractedVertex] = true;

                //iterate through all the adjacent vertices and update the keys
                ArrayList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    int destination = edge.destination.index;
                    //only if edge destination is not present in mst
                    if (!SPT[destination]) {
                        ///check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        int newKey = distance[extractedVertex] + edge.weight;
                        int currentKey = distance[destination];
                        if (currentKey > newKey) {
                            Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                            pq.offer(p);
                            distance[destination] = newKey;
                            parentVertex[destination] = extractedVertex;
                        }
                    }
                }
            }
        }
        //print Shortest Path Tree
        printDijkstra(parentVertex, distance, sourceVertex);
    }

    public void printDijkstra(int[] parent, int[] distance, int sourceVertex) {
        System.out.println("Dijkstra Algorithm: (With all paths)");
        for (int i = 0; i < vertices; i++) {
            System.out.print(" " + sourceVertex + " --> " + +i + ": distance = " + distance[i] + "  Path : ");
            printPathUtil(parent, i);
            System.out.println();
        }
    }

    public void printPathUtil(int parent[], int destination) {
        //if vertex is source then stop recursion
        if (parent[destination] == -1) {
            System.out.print("0 ");
            return;
        }
        printPathUtil(parent, parent[destination]);
        System.out.print(destination + " ");
    }

}
