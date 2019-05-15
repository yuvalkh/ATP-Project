package algorithms.mazeGenerators;

import javafx.geometry.Pos;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Maze implements Serializable {
    protected int[][] MazeInfo;
    private int numOfRows;
    private int numOfColumns;
    private Position StartPosition;
    private Position GoalPosition;

    /**
     * @param maze - a Copied maze in the copy Constructor;
     */
    public Maze(Maze maze) {//Copy Constructor
        if (maze != null) {
            MazeInfo = new int[maze.numOfRows][maze.numOfColumns];
            for (int i = 0; i < maze.numOfRows; i++) {
                for (int j = 0; j < maze.numOfColumns; j++) {
                    this.MazeInfo[i][j] = maze.MazeInfo[i][j];
                }
            }
            this.numOfColumns = maze.numOfColumns;
            this.numOfRows = maze.numOfRows;
            this.StartPosition = new Position(maze.StartPosition.getRowIndex(), maze.StartPosition.getColumnIndex());
            this.GoalPosition = new Position(maze.GoalPosition.getRowIndex(), maze.GoalPosition.getColumnIndex());
        }
    }

    /**
     * @param numOfRows    - number of rows
     * @param numOfColumns - number of columns
     */
    public Maze(int numOfRows, int numOfColumns) {
        if (numOfColumns > 0 && numOfRows > 0) {
            this.numOfRows = numOfRows;
            this.numOfColumns = numOfColumns;
            MazeInfo = new int[numOfRows][numOfColumns];
            GenerateStartAndEndPoints();
        }
    }

    /**
     * @param row    - specified row
     * @param column - specified column
     * @return - value in the specified cell
     */
    public int getMazeInfo(int row, int column) {
        if (row >= 0 && column >= 0)
            return MazeInfo[row][column];
        return -1;
    }

    /**
     * @param row    - specified row
     * @param column - specified column
     * @param data   - value to set in the specified cell
     */
    public void setMazeInfo(int row, int column, int data) {
        if (row < numOfRows && row >= 0 && column < numOfColumns && column >= 0)
            MazeInfo[row][column] = data;
    }

    /**
     * @return - number of rows
     */
    public int getNumOfRows() {
        return numOfRows;
    }

    /**
     * @return - number of columns
     */
    public int getNumOfColumns() {
        return numOfColumns;
    }

    /**
     * @return - the Starting Cell of the maze
     */
    public Position getStartPosition() {
        if (StartPosition != null)
            return StartPosition;
        return null;
    }

    /**
     * @param startPosition - set a new starting cell
     */
    public void setStartPosition(Position startPosition) {
        if (startPosition != null)
            StartPosition = startPosition;
    }

    /**
     * @param goalPosition - set a new goal cell
     */
    public void setGoalPosition(Position goalPosition) {
        if (goalPosition != null)
            GoalPosition = goalPosition;
    }

    /**
     * @return - the Goal Cell of the maze
     */
    public Position getGoalPosition() {
        if (GoalPosition != null)
            return GoalPosition;
        return null;
    }

    /**
     * prints maze with solutions
     */
/*
    public void print() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (i == StartPosition.getRowIndex() && j == StartPosition.getColumnIndex()) {//startPosition
                    System.out.print("S ");
                } else if (i == GoalPosition.getRowIndex() && j == GoalPosition.getColumnIndex()) {//goalPosition
                    System.out.print("E ");
                } else
                    System.out.print(MazeInfo[i][j] + " ");
            }
            System.out.println();
        }
    }
*/
    public void print() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if (i == StartPosition.getRowIndex() && j == StartPosition.getColumnIndex()) {//startPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (i == GoalPosition.getRowIndex() && j == GoalPosition.getColumnIndex()) {//goalPosition
                    System.out.print(" " + "\u001B[44m" + " ");
                } else if (MazeInfo[i][j] == 1) {
                    System.out.print(" " + "\u001B[45m" + " ");
                } else if (MazeInfo[i][j] == 10) {
                    System.out.print(" " + "\u001B[41m" + " ");
                } else if (MazeInfo[i][j] == 11) {
                    System.out.print(" " + "\u001B[42m" + " ");
                } else if (MazeInfo[i][j] == 12) {
                    System.out.print(" " + "\u001B[43m" + " ");
                } else System.out.print(" " + "\u001B[107m" + " ");
            }
            System.out.println(" " + "\u001B[107m");
        }
    }

    /**
     * generates random start Cell and Goal Cell
     */
    public void GenerateStartAndEndPoints() {
        boolean GoodDistance = false;
        while (!GoodDistance) {
            if (numOfRows != 0 && numOfColumns != 0) {
                Random random = new Random();
                /*Make new StartPosition*/
                Position start = new Position(random.nextInt(this.numOfRows), random.nextInt(this.numOfColumns));
                while (this.MazeInfo[start.getRowIndex()][start.getColumnIndex()] == 1) {
                    start.setRowIndex(random.nextInt(this.numOfRows));
                    start.setColumnIndex(random.nextInt(this.numOfColumns));
                }
                setStartPosition(start);

                /*Make new GoalPosition*/
                Position goal = new Position(random.nextInt(this.numOfRows), random.nextInt(this.numOfColumns));
                //goal.getColumnIndex()!= start.getColumnIndex() && goal.getRowIndex() != start.getRowIndex() &&
                while (this.MazeInfo[goal.getRowIndex()][goal.getColumnIndex()] == 1) {
                    goal.setColumnIndex(random.nextInt(this.numOfColumns));
                    goal.setRowIndex(random.nextInt(this.numOfRows));
                }
                setGoalPosition(goal);
                GoodDistance = Distance(start, goal);
            }
        }
    }

    /**
     * @param a - cell
     * @param b - cell
     * @return - checks if the distance between 2 cells is Far enough
     * we will use it usually to check if the start Position is far enough from the goal position
     * to make the maze a bit more interesting.
     */
    private boolean Distance(Position a, Position b) {

        int x1 = a.getRowIndex();
        int y1 = a.getColumnIndex();
        int x2 = b.getRowIndex();
        int y2 = b.getColumnIndex();

        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        double Minimum = (this.numOfColumns + this.numOfRows) / 7;

        if (distance >= Minimum) {
            return true;
        }
        return false;
    }

    /**
     * @return all the information of the maze(dimensions,walls/no walls
     * entry and finish points).
     * [numOfRows/256][numOfRows%256],[numOfColumns/256][numOfColumns%256],[startRow/256][startRow%256],[startCol/256] [startCol%256],
     * [goalRow/256][goalRow%256],[goalCol/256][goalCol%256],[0],[1],...,[1]
     */
    public byte[] toByteArray() {
        byte[] byteinfo = new byte[12 + numOfRows * numOfColumns];
        byteinfo[0] = (byte) (this.numOfRows / 256); //0
        byteinfo[1] = (byte) (this.numOfRows % 256); //1
        byteinfo[2] = (byte) (this.numOfColumns / 256); //2
        byteinfo[3] = (byte) (this.numOfColumns % 256); //3
        byteinfo[4] = (byte) (this.StartPosition.RowIndex / 256); //4
        byteinfo[5] = (byte) (this.StartPosition.RowIndex % 256); //5
        byteinfo[6] = (byte) (this.StartPosition.ColumnIndex / 256); //6
        byteinfo[7] = (byte) (this.StartPosition.ColumnIndex % 256); //7
        byteinfo[8] = (byte) (this.GoalPosition.RowIndex / 256); //8
        byteinfo[9] = (byte) (this.GoalPosition.RowIndex % 256); //9
        byteinfo[10] = (byte) (this.GoalPosition.ColumnIndex / 256); //10
        byteinfo[11] = (byte) (this.GoalPosition.ColumnIndex % 256); //11
        /**now we save the mazeInfo**/
        int counter = 12;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                byteinfo[counter] = (byte) (getMazeInfo(i, j));
                counter++;
            }
        }
        return byteinfo;
    }


    /**
     * @param info the information(dimensions,walls/no walls
     *             entry and finish points) of the maze we want to build
     */
    public Maze(byte[] info) {
        this.numOfRows = info[0] * 256 + (info[1] & 0xff);
        this.numOfColumns = info[2] * 256 + (info[3] & 0xff);
        this.MazeInfo = new int[numOfRows][numOfColumns];
        Position startPos = new Position(info[4] * 256 + (info[5] & 0xff), info[6] * 256 + (info[7] & 0xff));
        Position GoalPos = new Position(info[8] * 256 + (info[9] & 0xff), info[10] * 256 + (info[11] & 0xff));
        setStartPosition(startPos);
        setGoalPosition(GoalPos);
        int CurrentCell = 12;
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfColumns; col++) {
                setMazeInfo(row, col, info[CurrentCell]);
                CurrentCell++;
            }
        }
    }
}