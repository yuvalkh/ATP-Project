package algorithms.mazeGenerators;


import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    @SuppressWarnings("Duplicates")
    public Maze generate(int rows, int columns) {
        //the idea is to generate random solution that will go straight to the goal and then generate random walls
        Random rand = new Random();
        Maze maze = new Maze(rows, columns);
        int randomNumber;
        int startRow = rand.nextInt(rows);
        int startColumn = 0;
        int currentRow = startRow;
        int currentColumn = startColumn;
        int goalRow = rand.nextInt(rows);
        int goalColumn = columns - 1;
        maze.setStartPosition(new Position(startRow, startColumn));//We start in top left corner
        maze.setGoalPosition(new Position(goalRow, goalColumn));//We end in bottom right corner
        if (startRow <= goalRow) {//if goal is down
            if (startColumn <= goalColumn) {//if goal is right
                for (int i = 0; i < maze.getNumOfRows() + maze.getNumOfColumns(); i++) {//if end is down right from start point
                    randomNumber = rand.nextInt(2);//generate if go right(1) or go down(0)
                    if (randomNumber == 1 && currentColumn < maze.getNumOfColumns() - 1) {//go right
                        currentColumn++;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                    if (randomNumber == 0 && currentRow < maze.getNumOfRows() - 1) {//go down
                        currentRow++;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                }
            } else {//if goal is left
                for (int i = 0; i < maze.getNumOfRows() + maze.getNumOfColumns(); i++) {//if end is left down from start point
                    randomNumber = rand.nextInt(2);//generate if go left(1) or go down(0)
                    if (randomNumber == 1 && currentColumn > 0) {//go left
                        currentColumn--;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                    if (randomNumber == 0 && currentRow < maze.getNumOfRows() - 1) {//go down
                        currentRow++;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                }
            }
        } else {//if goal is up
            if (startColumn <= goalColumn) {//if goal is right
                for (int i = 0; i < maze.getNumOfRows() + maze.getNumOfColumns(); i++) {//if end is right up from start point
                    randomNumber = rand.nextInt(2);//generate if go right(1) or go up(0)
                    if (randomNumber == 1 && currentColumn < maze.getNumOfColumns() - 1) {//go right
                        currentColumn++;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                    if (randomNumber == 0 && currentRow > 0) {//go up
                        currentRow--;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                }
            } else {//if goal is left
                for (int i = 0; i < maze.getNumOfRows() + maze.getNumOfColumns(); i++) {//if end is left down from start point
                    randomNumber = rand.nextInt(2);//generate if go left(1) or go up(0)
                    if (randomNumber == 1 && currentColumn > 0) {//go left
                        currentColumn--;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                    if (randomNumber == 0 && currentRow > 0) {//go up
                        currentRow--;
                        maze.setMazeInfo(currentRow, currentColumn, 5);//we will mark 5 as the solution
                    }
                }
            }
        }

        //right now we can be OR in the end OR in the last row OR in the last column.

        if (currentRow < goalRow) {//we can still go down
            while (currentRow != goalRow) {
                currentRow++;
                maze.setMazeInfo(currentRow, currentColumn, 5);
            }
        }
        if (currentRow > goalRow) {//we can still go up
            while (currentRow != goalRow) {
                currentRow--;
                maze.setMazeInfo(currentRow, currentColumn, 5);
            }
        }
        if (currentColumn < goalColumn) {//we can still go right
            while (currentColumn != goalColumn) {
                currentColumn++;
                maze.setMazeInfo(currentRow, currentColumn, 5);
            }
        }
        if (currentColumn > goalColumn) {//we can still go left
            while (currentColumn != goalColumn) {
                currentColumn--;
                maze.setMazeInfo(currentRow, currentColumn, 5);
            }
        }
        //now the random number will tell us if a cell is a wall or empty
        for (int i = 0; i < maze.getNumOfRows(); i++) {
            for (int j = 0; j < maze.getNumOfColumns(); j++) {
                randomNumber = rand.nextInt(2);
                if (maze.getMazeInfo(i, j) != 5) {//it's not the solution
                    maze.setMazeInfo(i, j, randomNumber);
                } else {
                    maze.setMazeInfo(i, j, 0);
                }
            }
        }
        return maze;
    }
}
