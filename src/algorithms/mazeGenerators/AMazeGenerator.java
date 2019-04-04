package algorithms.mazeGenerators;

public abstract class  AMazeGenerator implements IMazeGenerator{
    //The class that inherited this abstract class will write the
    //public Maze generate(int rows, int columns) {
    //
    //}

    /**
     *
     * @param rows - number of rows in the maze
     * @param columns -number of columns in the maze
     * @return- the output maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        //will run System.currentTimeMillis() before and after
        //the generate function and will return the time that it took
        long timeBefore = System.currentTimeMillis();
        generate(rows,columns);
        long timeAfter = System.currentTimeMillis();
        long totalTime = timeAfter - timeBefore;
        return totalTime;
    }

}
