import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class Tester {
    static final int MAXNODES = 1000;
    static final int MAX_XPOS = 10000;
    static final int MAX_YPOS = 10000;
    static final int MAX_WEIGHT = 1000;

    @Test
    public void randomTest() {
        Random random = new Random();
        int numNodes = random.nextInt(MAXNODES);

        Node[] nodes = new Node[numNodes];
        int count = 0;
        while(count<numNodes){
            int x = random.nextInt(MAX_XPOS);
            int y = random.nextInt(MAX_YPOS);
            if(x == y){
                continue;
            }
            Node n = new Node(count, x,y);
            nodes[count] = n;
            count++;
        }

        Graph g = new Graph(numNodes, nodes);

        for (int i = 0; i < numNodes; i++) {
            for (int j = i; j < numNodes; j++) {
                if (i != j) {
                    g.addEdge(nodes[i], nodes[j], generateRandomWeights());
                }
            }
        }

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

    @Test
    public void randomStressTest(){
        for(int i = 0; i<100; i++){
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
