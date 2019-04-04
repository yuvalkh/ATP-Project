package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * @param rows    - number of rows in the maze
     * @param columns -number of columns in the maze
     * @return - the output maze
     */
    @Override
    public Maze generate(int rows, int columns) {
        //will return an empty maze without any walls
        if (rows > 0 && columns > 0) {
            Maze maze = new Maze(rows, columns);
            for (int i = 0; i < maze.getNumOfRows(); i++) {
                for (int j = 0; j < maze.getNumOfColumns(); j++) {
                    maze.setMazeInfo(i, j, 0);
                }
            }
            maze.GenerateStartAndEndPoints();
            return maze;
        }
        return null;
    }
}
