// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {

    int numNodes;
    ArrayList<Edge>[] adjacencylist;
    Node[] nodes;

    public Graph(int numNodes, Node[] nodes) {
        this.numNodes = numNodes;
        adjacencylist = new ArrayList[numNodes];
        // initialize adjacency lists for all the vertices
        for (int i = 0; i < numNodes; i++) {
            adjacencylist[i] = new ArrayList<>();
        }
        this.nodes = nodes;
    }

    public void addEdge(Node curNode, Node nextNode, ArrayList<Integer> weights) {
        Edge edge = new Edge(curNode, nextNode, weights);
        adjacencylist[curNode.index].add(edge);

        // for undirected graph
        edge = new Edge(nextNode, curNode, weights);
        adjacencylist[nextNode.index].add(edge);
    }

    public int calculateDist(Node nextNode, Node endNode) {
        // calculating weight as distance between two Cartesian coordinates
        return (int) (Math.sqrt(Math.pow(endNode.x - nextNode.x, 2) + Math.pow(endNode.y - nextNode.y, 2)));
    }

    public void aStarPrintPaths(Node startNode, Node endNode, User user) {

        int startIndex = startNode.index;

        boolean[] SPT = new boolean[numNodes];
        // distance used to store the distance of vertex from a source
        int[] minDists = new int[numNodes];

        int[] parentIndices = new int[numNodes];

        // parent of the source vertex will be -1
        parentIndices[0] = -1;

        // Initialize all the distance to infinity
        for (int i = 0; i < numNodes; i++) {
            minDists[i] = Integer.MAX_VALUE / 3;
        }
        // Initialize priority queue
        // override the comparator to do the sorting based keys
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(numNodes, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                // sort using distance values
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1 - key2;
            }
        });
        // create the pair for for the first index, 0 distance 0 index
        minDists[0] = 0;
        Pair<Integer, Integer> p0 = new Pair<>(minDists[0], 0);
        // add it to pq
        pq.add(p0);

        // while priority queue is not empty
        while (!pq.isEmpty()) {
            // extract the min
            Pair<Integer, Integer> extractedPair = pq.poll();

            // extracted index (current)
            int curIndex = extractedPair.getValue();
            if (!SPT[curIndex]) {
                SPT[curIndex] = true;

                // iterate through all the adjacent vertices and update the keys
                ArrayList<Edge> list = adjacencylist[curIndex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    int nextIndex = edge.dest.index;
                    if (!SPT[nextIndex]) {
                        // check if distance needs an update or not
                        // means check total weight from source to node is less than
                        // the current distance value, if yes then update the distance
                        int newWeight = minDists[curIndex] + calculateDist(nodes[nextIndex], nodes[endNode.index]) + edge.getEdgeWeight(user);
                        int curWeight = minDists[nextIndex];
                        if (curWeight > newWeight) {
                            Pair<Integer, Integer> p = new Pair<>(newWeight, nextIndex);
                            pq.offer(p);
                            minDists[nextIndex] = newWeight;
                            parentIndices[nextIndex] = curIndex;
                        }
                    }
                    if (nextIndex == endNode.index) {
                        // print a* path
                        printAStar(parentIndices, minDists, startIndex, endNode.index);
                        return;
                    }
                }
            }
        }
        System.out.println("Error");
    }

    public void printAStar(int[] parent, int[] distance, int sourceIndex, int destinationIndex) {
        System.out.println("A* Algorithm: ");
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
