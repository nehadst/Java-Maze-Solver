package ca.mcmaster.se2aa4.mazerunner;
public class Edge {
    private Node start;
    private Node end;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}