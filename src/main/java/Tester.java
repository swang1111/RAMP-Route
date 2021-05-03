import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Tester {
    static final int MAXNODES = 10000;
    static final int MINNODES = 10;
    static final int MAX_XPOS = 10000;
    static final int MAX_YPOS = 10000;
    static final int MAX_WEIGHT = 1000;

    @Test
    public void randomTest() {
        Random random = new Random();
        int numNodes = random.nextInt(MAXNODES) + MINNODES;

        Node[] nodes = new Node[numNodes];
        int count = 0;
        while (count < numNodes) {
            int x = random.nextInt(MAX_XPOS);
            int y = random.nextInt(MAX_YPOS);
            // todo can add a check to see if node (x,y) already exists in nodes in case
            Node n = new Node(count, x, y);
            nodes[count] = n;
            count++;
        }

        Graph g = new Graph(numNodes, nodes);

        int startNode;
        int endNode;
        int range = numNodes * (numNodes - 1) / 2 - numNodes + 1;
        int numEdges = numNodes - 1 + random.nextInt(range);
        Set<Point> createdEdges = new HashSet<>();

        int index = 0;

        while (index < numEdges) {
            startNode = random.nextInt(numNodes);
            endNode = startNode;
            while (endNode == startNode) {
                endNode = random.nextInt(numNodes);
            }
            Point p = new Point(startNode, endNode);
            if (createdEdges.contains(p)) {
                continue;
            }
            g.addEdge(nodes[startNode], nodes[endNode], generateRandomWeights());
            createdEdges.add(p);
            index++;
        }

        for (int j = 0; j < 10; j++){
            int startIndex = random.nextInt(numNodes);
            int endIndex = random.nextInt(numNodes);

            long startTime = System.nanoTime();
            for (int i = 0; i < 5; i++) {
                User user = getUserEnum(i);
                g.aStarPrintPaths(nodes[startIndex], nodes[endIndex], user);
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime)/1000000;
            System.out.println("Runtime: " + duration + "ms");
        }

    }

    @Test
    public void randomStressTest(){
        for(int i = 0; i<10; i++){
            randomTest();
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
            case 0:
            default:
                return User.DEFAULT;
        }
    }

    public ArrayList<Integer> generateRandomWeights(){
        ArrayList<Integer> weights = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i<5; i++){
            int w = random.nextInt(MAX_WEIGHT);
            weights.add(w);
        }
        return weights;
    }
}

class Point {
    int x;
    int y;

    Point (int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
