package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze extends Maze implements ISearchable {

    public SearchableMaze(int numOfRows, int numOfColumns) {
        super(numOfRows, numOfColumns);
    }

    public SearchableMaze(Maze maze) {
        super(maze);
    }

    @Override
    public AState getStartState() {
        return new MazeState(getStartPosition().getRowIndex(), getStartPosition().getColumnIndex(), null);
    }

    @Override
    public AState getGoalState() {
        return new MazeState(getGoalPosition().getRowIndex(), getGoalPosition().getColumnIndex(), null);
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        if (!(s instanceof MazeState))
            return null;
        MazeState state = (MazeState) s;
        int CurrentRow = state.getCurrentPosition().getRowIndex();
        int CurrentColumn = state.getCurrentPosition().getColumnIndex();
        ArrayList<AState> Successors = new ArrayList<>();

/*
        if(CurrentRow-1 >= 0 && CurrentColumn+1 < getNumOfColumns() && (MazeInfo[CurrentRow-1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn+1] == 0) && MazeInfo[CurrentRow-1][CurrentColumn+1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow-1,CurrentColumn+1,state));


        if(CurrentRow+1 < getNumOfRows() && CurrentColumn+1 < getNumOfColumns() && (MazeInfo[CurrentRow+1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn+1] == 0) && MazeInfo[CurrentRow+1][CurrentColumn+1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow+1,CurrentColumn+1,state));


        if(CurrentRow+1 < getNumOfRows() && CurrentColumn-1 >= 0 && (MazeInfo[CurrentRow+1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn-1] == 0) && MazeInfo[CurrentRow+1][CurrentColumn-1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow+1,CurrentColumn-1,state));

        if(CurrentRow-1 >= 0 && CurrentColumn-1 >= 0 && (MazeInfo[CurrentRow-1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn-1] == 0) && MazeInfo[CurrentRow-1][CurrentColumn-1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow-1,CurrentColumn-1,state));
*/

        if (CurrentRow - 1 >= 0 && MazeInfo[CurrentRow - 1][CurrentColumn] == 0 || CurrentRow - 1 >= 0 && MazeInfo[CurrentRow - 1][CurrentColumn] == 8)//if up is blank
            Successors.add(new MazeState(CurrentRow - 1, CurrentColumn, state));
        if (CurrentColumn + 1 < getNumOfColumns() && MazeInfo[CurrentRow][CurrentColumn + 1] == 0 || CurrentColumn + 1 < getNumOfColumns() && MazeInfo[CurrentRow][CurrentColumn + 1] == 8)//if right is blank
            Successors.add(new MazeState(CurrentRow, CurrentColumn + 1, state));
        if (CurrentRow + 1 < getNumOfRows() && MazeInfo[CurrentRow + 1][CurrentColumn] == 0 || CurrentRow + 1 < getNumOfRows() && MazeInfo[CurrentRow + 1][CurrentColumn] == 8)//if down is blank
            Successors.add(new MazeState(CurrentRow + 1, CurrentColumn, state));
        if (CurrentColumn - 1 >= 0 && MazeInfo[CurrentRow][CurrentColumn - 1] == 0 || CurrentColumn - 1 >= 0 && MazeInfo[CurrentRow][CurrentColumn - 1] == 8)//if left is blank
            Successors.add(new MazeState(CurrentRow, CurrentColumn - 1, state));

        return Successors;
    }
}
