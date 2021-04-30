public class Node {

    int index;
    int x;
    int y;

    public Node(int index, int x, int y) {
        this.index = index;
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return index + "";
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}