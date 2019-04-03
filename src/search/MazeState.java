package search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    Position currentPosition;
    public MazeState(int row, int column,MazeState from) {
        super(row + "," + column,from,0);
        currentPosition = new Position(row,column);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
}
