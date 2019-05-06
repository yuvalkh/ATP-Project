package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.Objects;

public class MazeState extends AState {
    boolean isCross;
    private Position currentPosition;

    /**
     *
     * @param row the row of the cell
     * @param column the column of the cell
     * @param from the maze state we came from
     */
    public MazeState(int row, int column,MazeState from,boolean iscross) {
        super(row + "," + column,from,0);
        currentPosition = new Position(row,column);
        isCross = iscross;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPosition.getRowIndex(),currentPosition.getColumnIndex());
    }

    /**
     *
     * @return the current position of the state
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     *
     * @param o object we want to compare
     * @return if the object is equal to the state
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof MazeState))
            return false;
        MazeState castedState = (MazeState)o;
        if(currentPosition.getColumnIndex() == castedState.currentPosition.getColumnIndex() && currentPosition.getRowIndex() == castedState.currentPosition.getRowIndex())
            return true;
        return false;
    }

    /**
     *
     *
     * @param Goal The goal of the maze
     * @return the distance from the goal
     */
    @Override
    public double getDistanceFromGoal(AState Goal) {
        if(Goal instanceof MazeState) {
            if(isCross)
                return Math.sqrt(Math.pow(currentPosition.getColumnIndex() - ((MazeState) Goal).currentPosition.getColumnIndex(), 2) + Math.pow(currentPosition.getRowIndex() - ((MazeState) Goal).currentPosition.getRowIndex(), 2)) * 2;
            else
                return Math.sqrt(Math.pow(currentPosition.getColumnIndex() - ((MazeState) Goal).currentPosition.getColumnIndex(), 2) + Math.pow(currentPosition.getRowIndex() - ((MazeState) Goal).currentPosition.getRowIndex(), 2));
        }
        else return -1;
    }

    /**
     *
     * @return a string the represent the MazeState
     */
    @Override
    public String toString() {
        return "currentPosition=" + currentPosition;
    }
}
