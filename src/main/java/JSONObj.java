public class JSONObj {
    Node[] nodes;
    Edge[] edges;
    int startIndex;
    int endIndex;

    public JSONObj(Node[] nodes, Edge[] edges, int startIndex, int endIndex){
        this.nodes = nodes;
        this.edges = edges;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public Node[] getNodes() {
        return nodes;
    }
}
