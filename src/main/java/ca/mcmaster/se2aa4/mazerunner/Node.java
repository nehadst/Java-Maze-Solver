package ca.mcmaster.se2aa4.mazerunner;
import java.util.*;

public class Node {
    private int x;
    private int y;
    private List<Edge> edges;
    private boolean visited;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.edges = new ArrayList<>();
        this.visited = false;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void visit() {
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}