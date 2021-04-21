// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

public class Driver {

    public static void main(String[] args) {

        int vertices = 6;
        Graph graph = new Graph(vertices);

        Node n0 = new Node(0, 1, 1);
        Node n1 = new Node(1, 3, 3);
        Node n2 = new Node(2, 2, 2);
        Node n3 = new Node(3, 4, 4);
        Node n4 = new Node(4, 1, 0);
        Node n5 = new Node(5, 2, 1);

        graph.addEdge(n0, n1);
        graph.addEdge(n0, n2);
        graph.addEdge(n1, n2);
        graph.addEdge(n1, n3);
        graph.addEdge(n2, n3);
        graph.addEdge(n3, n4);
        graph.addEdge(n4, n5);

        graph.dijkstra_PrintPaths(n0);

    }

}