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
    public boolean equals(Object o) {
        if(!(o instanceof MazeState))
            return false;
        MazeState castedState = (MazeState)o;
        if(currentPosition.getColumnIndex() == castedState.currentPosition.getColumnIndex() && currentPosition.getRowIndex() == castedState.currentPosition.getRowIndex())
            return true;
        return false;
    }

    @Override
    public double getDistanceFromGoal(AState Goal) {
        if(Goal instanceof MazeState)
            return Math.sqrt(Math.pow(currentPosition.getColumnIndex() - ((MazeState) Goal).currentPosition.getColumnIndex(),2) + Math.pow(currentPosition.getRowIndex() - ((MazeState) Goal).currentPosition.getRowIndex(),2));
        else
            return -1;
    }

    @Override
    public String toString() {
        return "currentPosition=" + currentPosition;
    }
}
