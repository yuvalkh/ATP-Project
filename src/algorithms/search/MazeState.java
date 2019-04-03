package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState {
    private Position currentPosition;
    public MazeState(int row, int column,MazeState from) {
        super(row + "," + column,from,0);
        currentPosition = new Position(row,column);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public boolean equal(AState state) {
        if(!(state instanceof MazeState))
            return false;
        MazeState castedState = (MazeState)state;
        if(currentPosition.getColumnIndex() == castedState.currentPosition.getColumnIndex() && currentPosition.getRowIndex() == castedState.currentPosition.getRowIndex())
            return true;
        return false;
    }
}
