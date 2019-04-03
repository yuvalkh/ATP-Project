package search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze extends Maze implements ISearchable{

    public SearchableMaze(int numOfRows, int numOfColumns) {
        super(numOfRows, numOfColumns);
    }

    @Override
    public AState getStartState() {
        return new MazeState(getStartPosition().getRowIndex(),getStartPosition().getColumnIndex(),null);
    }

    @Override
    public AState getGoalState() {
        return new MazeState(getGoalPosition().getRowIndex(),getGoalPosition().getColumnIndex(),null);
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        if(!(s instanceof MazeState))
            return null;
        MazeState state = (MazeState)s;
        int CurrentRow = state.currentPosition.getRowIndex();
        int CurrentColumn = state.currentPosition.getRowIndex();
        ArrayList<AState> Successors = new ArrayList<>();
        if(CurrentRow-1 >= 0 && MazeInfo[CurrentRow-1][CurrentColumn] == 0)
            Successors.add(new MazeState(CurrentRow-1,CurrentColumn,state));
        if(CurrentColumn+1 < getNumOfColumns() && MazeInfo[CurrentRow][CurrentColumn+1] == 0)
            Successors.add(new MazeState(CurrentRow,CurrentColumn+1,state));
        if(CurrentRow+1 < getNumOfRows() && MazeInfo[CurrentRow+1][CurrentColumn] == 0)
            Successors.add(new MazeState(CurrentRow+1,CurrentColumn,state));
        if(CurrentColumn-1 >= 0 && MazeInfo[CurrentRow][CurrentColumn-1] == 0)
            Successors.add(new MazeState(CurrentRow,CurrentColumn-1,state));
        return Successors;
    }
}
