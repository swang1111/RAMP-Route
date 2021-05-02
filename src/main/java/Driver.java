// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) throws Exception {

        Object obj = new JSONParser().parse(new FileReader("example_input_6_users.json"));
        JSONObject jo = (JSONObject) obj;

        JSONArray nodesArr = (JSONArray) jo.get("nodes");
        JSONArray edgesArr = (JSONArray) jo.get("edges");

        Integer startIndex = ((Long) jo.get("startIndex")).intValue();
        Integer endIndex = ((Long) jo.get("endIndex")).intValue();

        int numNodes = nodesArr.size();
        int numEdges = edgesArr.size();

        Node[] nodes = new Node[numNodes];
        Graph graph = new Graph(numNodes, nodes);

        for (int i = 0; i < numNodes; i++) {
            JSONObject node = (JSONObject) nodesArr.get(i);
            Integer nodeIndex = ((Long) node.get("index")).intValue();
            Integer x = ((Long) node.get("x")).intValue();
            Integer y = ((Long) node.get("y")).intValue();
            nodes[i] = new Node(nodeIndex, x, y);
        }

        for (int i = 0; i < numEdges; i++) {
            JSONObject edge = (JSONObject) edgesArr.get(i);
            JSONArray nodeIndices = (JSONArray) edge.get("node indices");
            int n1 = ((Long) nodeIndices.get(0)).intValue();
            int n2 = ((Long) nodeIndices.get(1)).intValue();

            ArrayList<Integer> weights = new ArrayList<>();
            weights.add(((Long) edge.get("default weight")).intValue());
            weights.add(((Long) edge.get("power wheelchair weight")).intValue());
            weights.add(((Long) edge.get("manual wheelchair weight")).intValue());
            weights.add(((Long) edge.get("color blind weight")).intValue());
            weights.add(((Long) edge.get("autistic weight")).intValue());
            weights.add(((Long) edge.get("blind weight")).intValue());

            graph.addEdge(nodes[n1], nodes[n2], weights);
        }

        for (int i = 0; i < 6; i++) {
            User user = getUserEnum(i);
            graph.aStarPrintPaths(nodes[startIndex], nodes[endIndex], user);
        }

    }

    public static User getUserEnum(int userType) {
        switch (userType) {
            case 1:
                return User.POWER_WHEELCHAIR;
            case 2:
                return User.MANUAL_WHEELCHAIR;
            case 3:
                return User.COLOR_BLIND;
            case 4:
                return User.AUTISTIC;
            case 5:
                return User.BLIND;
            case 0:
            default:
                return User.DEFAULT;
        }
    }

}