package ca.mcmaster.se2aa4.mazerunner;
public class Benchmark {
    private Maze maze;

    public Benchmark(Maze maze) {
        this.maze = maze;
    }

    public void run(String method, String baseline) {
        maze.setMazeExplorer(getStrategy(method));
        long startTime = System.nanoTime();
        String methodPath = maze.exploreMaze();
        long methodTime = System.nanoTime() - startTime;


        maze.setMazeExplorer(getStrategy(baseline));
        startTime = System.nanoTime();
        String baselinePath = maze.exploreMaze();
        long baselineTime = System.nanoTime() - startTime;

        double speedup = (double) baselinePath.length() / methodPath.length();

        System.out.printf("Method time (%s): %.2f ms\n", method, methodTime / 1e6);
        System.out.printf("Baseline time (%s): %.2f ms\n", baseline, baselineTime / 1e6);
        System.out.printf("Speedup: %.2f\n", speedup);
    }

    private MazeExplorer getStrategy(String name) {
        MazeExplorer explorer;
        switch (name.toLowerCase()) {
            case "dfs":
                explorer = new DFSAlgorithm(maze.getMazeConfig());
                break;
            case "righthand":
                explorer = new RightHandAlg(maze.getMazeConfig());
                break;
            default:
                throw new IllegalArgumentException("No strategy found for name: " + name);
        }
        maze.setMazeExplorer(explorer);
        return explorer;
    }
}