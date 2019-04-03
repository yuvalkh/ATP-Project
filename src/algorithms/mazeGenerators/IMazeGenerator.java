package algorithms.mazeGenerators;

public interface IMazeGenerator {
    public Maze generate(int rows,int columns);

    long measureAlgorithmTimeMillis(int rows,int columns);
}
