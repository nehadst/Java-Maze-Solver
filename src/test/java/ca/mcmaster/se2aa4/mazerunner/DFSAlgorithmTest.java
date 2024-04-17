package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DFSAlgorithmTest {
    private Maze maze;
    private DFSAlgorithm dfs;

    @BeforeEach
    public void setup() {
        char[][] mazeSample = {
            {'#', '#', '#', '#', '#'},
            {' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#'},
            {'#', ' ', '#', '#', '#'}
        };
        maze = new Maze(mazeSample);
        dfs = new DFSAlgorithm(mazeSample);
    }
    
    @Test
    public void testSolveMazeWithDFS() {
        List<String> actualPath = maze.solveMazeWithDFS();
        List<String> expectedPath = List.of("(1,0)", "(1,1)", "(1,2)", "(1,3)");
        assertEquals(expectedPath, actualPath, "DFS did not find the correct path.");
    }
}
