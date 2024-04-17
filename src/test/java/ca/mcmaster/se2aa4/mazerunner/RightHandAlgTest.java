package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RightHandAlgTest {

    private Maze maze;
    private RightHandAlg rightHandAlg;
    private char[][] mazeArray;

    @BeforeEach
    void setup() {
        mazeArray = new char[][] {
            {'#', '#', '#', '#', '#', '#'},
            {' ', ' ', ' ', ' ', ' ', ' '},
            {'#', '#', '#', '#', '#', '#'}
        };

        maze = new Maze(mazeArray);
        rightHandAlg = new RightHandAlg(maze.getMazeConfig());
        maze.setMazeExplorer(rightHandAlg);
    }

    @Test
    void testExploreMaze() {
        String expectedPath = "RRRRRR";
        String resultPath = maze.exploreMaze();
        assertEquals(expectedPath, resultPath, "The explored path does not match the expected right-hand path.");
    }

    @Test
    void testCanMove() {
        assertTrue(rightHandAlg.canMove(), "Should be able to move right at the start.");
    }

    @Test
    void testFactorizePath(){
        maze = new Maze(mazeArray);
        String factorizedPath = maze.factorizePath("RRRRRR");
        assertEquals("R6", factorizedPath, "Path should be factorized to R6.");
    }
}