// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {

    int vertices;
    ArrayList<Edge>[] adjacencylist;
    Node[] nodes;

    public Graph(int vertices, Node[] nodes) {
        this.vertices = vertices;
        adjacencylist = new ArrayList[vertices];
        // initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            adjacencylist[i] = new ArrayList<>();
        }
        this.nodes = nodes;
    }

    public void addEdge(Node source, Node destination, ArrayList<Integer> weights) {
        Edge edge = new Edge(source, destination, weights);
        adjacencylist[source.index].add(edge);

        // for undirected graph
        edge = new Edge(destination, source, weights);
        adjacencylist[destination.index].add(edge);
    }

    public int calculateDist(Node node, Node destination) {
        // calculating weight as distance between two Cartesian coordinates
        return (int) (Math.pow(destination.x - node.x, 2) + Math.pow(destination.y - node.y, 2));
    }

    public void dijkstra_PrintPaths(Node sourceNode, Node destinationNode, User user) {

        int sourceVertex = sourceNode.index;

        boolean[] SPT = new boolean[vertices];
        // distance used to store the distance of vertex from a source
        int[] distance = new int[vertices];

        int[] parentVertex = new int[vertices];

        // parent of the source vertex will be -1
        parentVertex[0] = -1;

        // Initialize all the distance to infinity
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE / 3;
        }
        // Initialize priority queue
        // override the comparator to do the sorting based keys
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                //sort using distance values
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1 - key2;
            }
        });
        // create the pair for for the first index, 0 distance 0 index
        distance[0] = 0;
        Pair<Integer, Integer> p0 = new Pair<>(distance[0], 0);
        // add it to pq
        pq.add(p0);

        // while priority queue is not empty
        while (!pq.isEmpty()) {
            // extract the min
            Pair<Integer, Integer> extractedPair = pq.poll();

            // extracted vertex
            int curIndex = extractedPair.getValue();
            if (!SPT[curIndex]) {
                SPT[curIndex] = true;

                // iterate through all the adjacent vertices and update the keys
                ArrayList<Edge> list = adjacencylist[curIndex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    int nextIndex = edge.end.index;
                    if (!SPT[nextIndex]) {
                        // check if distance needs an update or not
                        // means check total weight from source to vertex_V is less than
                        // the current distance value, if yes then update the distance
                        int newKey = distance[curIndex] + calculateDist(nodes[nextIndex], nodes[destinationNode.index]) + edge.getEdgeWeight(user);
                        int currentKey = distance[nextIndex];
                        if (currentKey > newKey) {
                            Pair<Integer, Integer> p = new Pair<>(newKey, nextIndex);
                            pq.offer(p);
                            distance[nextIndex] = newKey;
                            parentVertex[nextIndex] = curIndex;
                        }
                    }
                    if (nextIndex == destinationNode.index) {
                        // print dijkstra path
                        printDijkstra(parentVertex, distance, sourceVertex, destinationNode.index);
                        return;
                    }
                }
            }
        }
        System.out.println("Error");
    }

    public void printDijkstra(int[] parent, int[] distance, int sourceIndex, int destinationIndex) {
        System.out.println("Dijkstra Algorithm: ");
        System.out.print(" " + sourceIndex + " --> " + destinationIndex + ": distance = " + distance[destinationIndex] + "  Path : ");
        printPathUtil(parent, destinationIndex);
        System.out.println();
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
