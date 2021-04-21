// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import java.util.ArrayList;

public class Edge {

    Node start;
    Node end;
    ArrayList<Integer> weights;

    public Edge(Node source, Node destination, ArrayList<Integer> weights) {
        this.start = source;
        this.end = destination;

        // copy weights
        this.weights = new ArrayList<Integer>();
        for (int i = 0; i < weights.size(); i++) {
            this.weights.add(weights.get(i));
        }

    }

    public int getEdgeWeight(User user) {
        switch (user) {
            case WHEELCHAIR:
                return weights.get(1);
            case COLOR_BLIND:
                return weights.get(2);
            case NORMAL:
            default:
                return weights.get(0);
        }
    }

    public String toString() {
        return start + " " + end;
    }

}