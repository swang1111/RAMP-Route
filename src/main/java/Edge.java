// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import java.util.ArrayList;

public class Edge {

    Node src;
    Node dest;
    ArrayList<Integer> weights;

    public Edge(Node src, Node dest, ArrayList<Integer> weights) {
        this.src = src;
        this.dest = dest;

        // copy weights
        this.weights = new ArrayList<Integer>();
        for (int i = 0; i < weights.size(); i++) {
            this.weights.add(weights.get(i));
        }

    }

    public int getEdgeWeight(User user) {
        switch (user) {
            case POWER_WHEELCHAIR:
                return weights.get(1);
            case MANUAL_WHEELCHAIR:
                return weights.get(2);
            case COLOR_BLIND:
                return weights.get(3);
            case AUTISTIC:
                return weights.get(4);
            case DEFAULT:
            default:
                return weights.get(0);
        }
    }

    public String toString() {
        return src + " " + dest;
    }

}