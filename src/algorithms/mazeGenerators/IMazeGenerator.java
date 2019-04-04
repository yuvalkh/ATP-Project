package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /**
     *
     * @param rows - number of rows in the maze
     * @param columns -number of columns in the maze
     * @return- the output maze
     */
    public Maze generate(int rows,int columns);

    /**
     *
     * @param rows - number of rows in the maze
     * @param columns -number of columns in the maze
     * @return- the time that taken to create a maze
     */
    long measureAlgorithmTimeMillis(int rows,int columns);
}
