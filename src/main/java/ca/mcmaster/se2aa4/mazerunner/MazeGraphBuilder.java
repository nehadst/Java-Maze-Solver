package ca.mcmaster.se2aa4.mazerunner;

public class MazeGraphBuilder{
    private final Node[][] nodes;
    private final char [][] mazeArray;
    private static final char PATH = ' ';

    public MazeGraphBuilder(Maze maze, Node[][] nodes, char[][] mazeArray) {
        this.nodes = nodes;
        this.mazeArray = mazeArray;
    }

    public int getLength() {
        return nodes.length;
    }

    public int getWidth() {
        return nodes[0].length;
    }

    public void initializeGraph() {
        for (int i = 0; i < getLength(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (mazeArray[i][j] == PATH) {
                    if (nodes[i][j] == null) {
                        nodes[i][j] = new Node(i, j);
                    }
                    linkNodes(i, j);
                }
            }
        }
    }

    private void linkNodes(int i, int j) {
        if (i > 0 && mazeArray[i - 1][j] == PATH) {
            if (nodes[i-1][j] == null) nodes[i-1][j] = new Node(i-1, j);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i-1][j]));
        }
        if (i < getLength() - 1 && mazeArray[i + 1][j] == PATH) {
            if (nodes[i+1][j] == null) nodes[i+1][j] = new Node(i+1, j);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i+1][j]));
        }
        if (j > 0 && mazeArray[i][j - 1] == PATH) {
            if (nodes[i][j-1] == null) nodes[i][j-1] = new Node(i, j-1);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i][j-1]));
        }
        if (j < getWidth() - 1 && mazeArray[i][j + 1] == PATH) {
            if (nodes[i][j+1] == null) nodes[i][j+1] = new Node(i, j+1);
            nodes[i][j].addEdge(new Edge(nodes[i][j], nodes[i][j+1]));
        }
    }
}