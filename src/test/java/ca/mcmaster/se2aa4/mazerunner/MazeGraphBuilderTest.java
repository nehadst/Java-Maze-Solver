package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeGraphBuilderTest {

    private Maze maze;
    private MazeGraphBuilder builder;

    @BeforeEach
    void setup() {
        char[][] mazeArray = {
            {'#', '#', '#', '#'},
            {'#', ' ', ' ', '#'},
            {'#', ' ', '#', '#'},
            {'#', '#', '#', '#'}
        };

        maze = new Maze(mazeArray);
        builder = new MazeGraphBuilder(maze, maze.getNodes(), mazeArray);
        builder.initializeGraph();
    }

    @Test
    void testNodeCreation() {
        assertNotNull(maze.getNodes()[1][1], "Node should be created at (1,1)");
        assertNotNull(maze.getNodes()[1][2], "Node should be created at (1,2)");
        assertNull(maze.getNodes()[0][0], "No node should be created at (0,0) as it is a wall");
        assertNull(maze.getNodes()[2][2], "No node should be created at (2,2) as it is a wall");
    }

    @Test
    void testNodeLinks() {
        Node node11 = maze.getNodes()[1][1];
        Node node12 = maze.getNodes()[1][2];
        assertTrue(node11.getEdges().stream().anyMatch(e -> e.getEnd() == node12),
                   "Node at (1,1) should be connected to node at (1,2)");
        assertTrue(node12.getEdges().stream().anyMatch(e -> e.getEnd() == node11),
                   "Node at (1,2) should be connected to node at (1,1)");
        assertFalse(node11.getEdges().stream().anyMatch(e -> e.getEnd() == maze.getNodes()[2][1]),
                    "Node at (1,1) should not be connected to node at (2,1)");
    }
}
