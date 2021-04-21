// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Driver {

    public static void main(String[] args) throws IOException {

        // Input format (can change, possibly change to JSON?)
        // numNodes numEdges numUsers startIndex endIndex userIndex
        // next numNodes lines --> (x, y)
        // next numEdges lines (n1 connected to n2) --> (n1, n2, w1, w2, w3)
        BufferedReader br = new BufferedReader(new FileReader("Input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numNodes = Integer.parseInt(st.nextToken());
        int numEdges = Integer.parseInt(st.nextToken());
        int numUsers = Integer.parseInt(st.nextToken());
        int startIndex = Integer.parseInt(st.nextToken());
        int endIndex = Integer.parseInt(st.nextToken());
        int userIndex = Integer.parseInt(st.nextToken());


        Node[] nodes = new Node[numNodes];
        User user = getUserEnum(userIndex);
        Graph graph = new Graph(numNodes, nodes);

        for (int i = 0; i < numNodes; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < numEdges; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> weights = new ArrayList<>();
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numUsers; j++) {
                weights.add(Integer.parseInt(st.nextToken()));
            }
            graph.addEdge(nodes[n1], nodes[n2], weights);
        }

        // run and print shortest path for a startNode, endNode, and user
        graph.aStarPrintPaths(nodes[startIndex], nodes[endIndex], user);

    }

    public static User getUserEnum(int userIndex) {
        switch (userIndex) {
            case 1:
                return User.POWER_WHEELCHAIR;
            case 2:
                return User.MANUAL_WHEELCHAIR;
            case 3:
                return User.COLOR_BLIND;
            case 4:
                return User.AUTISTIC;
            case 0:
            default:
                return User.DEFAULT;
        }
    }

}