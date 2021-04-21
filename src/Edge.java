// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

public class Edge {

    Node source;
    Node destination;
    int weight;

    public Edge(Node source, Node destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

}