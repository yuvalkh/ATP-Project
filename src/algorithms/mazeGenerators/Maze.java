package algorithms.mazeGenerators;

public class Maze {
    protected int[][] MazeInfo;
    private int numOfRows;
    private int numOfColumns;
    private Position StartPosition;
    private Position GoalPosition;

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
                } else if (MazeInfo[i][j] == 1) System.out.print(" " + "\u001B[45m" + " ");
                else System.out.print(" " + "\u001B[107m" + " ");
            }
            System.out.println(" " + "\u001B[107m");
        }

    }
}