package algorithms.mazeGenerators;

import java.util.Random;

public class Maze {
    public int[][] MazeInfo;
    private int numOfRows;
    private int numOfColumns;
    private Position StartPosition;
    private Position GoalPosition;

    public Maze(Maze maze){//Copy Constructor
        MazeInfo = new int[maze.numOfRows][maze.numOfColumns];
        for (int i = 0; i < maze.numOfRows ; i++)
        {
            for (int j = 0; j < maze.numOfColumns; j++)
            {
                this.MazeInfo[i][j] = maze.MazeInfo[i][j];
            }
        }
        this.numOfColumns = maze.numOfColumns;
        this.numOfRows = maze.numOfRows;
        this.StartPosition = new Position(maze.StartPosition.getRowIndex(),maze.StartPosition.getColumnIndex());
        this.GoalPosition = new Position(maze.GoalPosition.getRowIndex(),maze.GoalPosition.getColumnIndex());
    }

    public Maze(int numOfRows, int numOfColumns) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        MazeInfo = new int[numOfRows][numOfColumns];
    }

    public int getMazeInfo(int row,int column){
        return MazeInfo[row][column];
    }

    public void setMazeInfo(int row,int column,int data){
        if(row < numOfRows && row >=0 && column < numOfColumns && column >= 0)
        MazeInfo[row][column] = data;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public Position getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void print () {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (i == StartPosition.getRowIndex() && j == StartPosition.getColumnIndex()) {//startPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (i == GoalPosition.getRowIndex() && j == GoalPosition.getColumnIndex()) {//goalPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (MazeInfo[i][j] == 1) {System.out.print(" " + "\u001B[45m" + " ");}
                else if (MazeInfo[i][j] == 10) {System.out.print(" " + "\u001B[41m" + " ");}
                else if (MazeInfo[i][j] == 11) {System.out.print(" " + "\u001B[42m" + " ");}
                else if (MazeInfo[i][j] == 12) {System.out.print(" " + "\u001B[43m" + " ");}

                else System.out.print(" " + "\u001B[107m" + " ");
            }
            System.out.println(" " + "\u001B[107m");
        }
    }

    public void GenerateStartAndEndPoints () {
        if (numOfRows != 0 && numOfColumns != 0) {

            Random random = new Random();

            /*Make new StartPosition*/
            Position start = new Position(random.nextInt(this.numOfRows),random.nextInt(this.numOfColumns));
            while (this.MazeInfo[start.getRowIndex()][start.getColumnIndex()] == 1){
                start.setRowIndex(random.nextInt(this.numOfRows));
                start.setColumnIndex(random.nextInt(this.numOfColumns));
            }
            setStartPosition(start);
            System.out.println( "start index  = " + getMazeInfo(start.getRowIndex(),start.getColumnIndex()));

            /*Make new GoalPosition*/
            Position goal = new Position(random.nextInt(this.numOfRows),random.nextInt(this.numOfColumns));
            //goal.getColumnIndex()!= start.getColumnIndex() && goal.getRowIndex() != start.getRowIndex() &&
            while (this.MazeInfo[goal.getRowIndex()][goal.getColumnIndex()] == 1){
                goal.setColumnIndex(random.nextInt(this.numOfColumns));
                goal.setRowIndex(random.nextInt(this.numOfRows));
            }
            setGoalPosition(goal);
            System.out.println( "goal index  = " + getMazeInfo(goal.getRowIndex(),goal.getColumnIndex()));
        }
    }
}