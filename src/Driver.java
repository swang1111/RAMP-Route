// Modified from online Dijkstras SP code
// Original source: https://algorithms.tutorialhorizon.com/print-all-paths-in-dijkstras-shortest-path-algorithm/

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Driver {

    public static void main(String[] args) throws Exception {

        Object obj = new JSONParser().parse(new FileReader("RAMP-Route/ex_input.json"));
        JSONObject jo = (JSONObject) obj;

        JSONArray nodesArr = (JSONArray) jo.get("nodes");
        JSONArray edgesArr = (JSONArray) jo.get("edges");

        Integer startIndex = ((Long) jo.get("startIndex")).intValue();
        Integer endIndex = ((Long) jo.get("endIndex")).intValue();
        String userType = (String) jo.get("userType");

        int numNodes = nodesArr.size();
        int numEdges = edgesArr.size();

        Node[] nodes = new Node[numNodes];
        User user = getUserEnum(userType);
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

            graph.addEdge(nodes[n1], nodes[n2], weights);
        }

        graph.aStarPrintPaths(nodes[startIndex], nodes[endIndex], user);
    }

    public static User getUserEnum(String userType) {
        switch (userType) {
            case "POWER_WHEELCHAIR":
                return User.POWER_WHEELCHAIR;
            case "MANUAL_WHEELCHAIR":
                return User.MANUAL_WHEELCHAIR;
            case "COLOR_BLIND":
                return User.COLOR_BLIND;
            case "AUTISTIC":
                return User.AUTISTIC;
            case "DEFAULT":
            default:
                return User.DEFAULT;
        }
    }

}