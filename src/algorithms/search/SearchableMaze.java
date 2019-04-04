package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze extends Maze implements ISearchable{

    /**
     *
     * @param numOfRows number of rows
     * @param numOfColumns number of columns
     */
    public SearchableMaze(int numOfRows, int numOfColumns) {
        super(numOfRows, numOfColumns);
    }

    /**
     *
     * @param maze the maze we want to copy
     */
    public SearchableMaze(Maze maze){
        super(maze);
    }

    /**
     *
     * @return the start position of the maze
     */
    @Override
    public AState getStartState() {
        return new MazeState(getStartPosition().getRowIndex(),getStartPosition().getColumnIndex(),null);
    }

    /**
     *
     * @return the goal postion of the maze
     */
    @Override
    public AState getGoalState() {
        return new MazeState(getGoalPosition().getRowIndex(),getGoalPosition().getColumnIndex(),null);
    }

    /**
     *
     * @param s a specific state
     * @return a list of all the states that we can get from the state s
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        if(!(s instanceof MazeState))
            return null;
        MazeState state = (MazeState)s;
        int CurrentRow = state.getCurrentPosition().getRowIndex();
        int CurrentColumn = state.getCurrentPosition().getColumnIndex();
        ArrayList<AState> Successors = new ArrayList<>();


        if(CurrentRow-1 >= 0 && CurrentColumn+1 < getNumOfColumns() && (MazeInfo[CurrentRow-1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn+1] == 0) && MazeInfo[CurrentRow-1][CurrentColumn+1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow-1,CurrentColumn+1,state));

        if(CurrentRow+1 < getNumOfRows() && CurrentColumn+1 < getNumOfColumns() && (MazeInfo[CurrentRow+1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn+1] == 0) && MazeInfo[CurrentRow+1][CurrentColumn+1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow+1,CurrentColumn+1,state));

        if(CurrentRow+1 < getNumOfRows() && CurrentColumn-1 >= 0 && (MazeInfo[CurrentRow+1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn-1] == 0) && MazeInfo[CurrentRow+1][CurrentColumn-1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow+1,CurrentColumn-1,state));

        if(CurrentRow-1 >= 0 && CurrentColumn-1 >= 0 && (MazeInfo[CurrentRow-1][CurrentColumn] == 0 || MazeInfo[CurrentRow][CurrentColumn-1] == 0) && MazeInfo[CurrentRow-1][CurrentColumn-1] == 0 )//if top right is blank
            Successors.add(new MazeState(CurrentRow-1,CurrentColumn-1,state));

        if(CurrentRow-1 >= 0 && MazeInfo[CurrentRow-1][CurrentColumn] == 0 || CurrentRow-1 >= 0 && MazeInfo[CurrentRow-1][CurrentColumn] == 8)//if up is blank
            Successors.add(new MazeState(CurrentRow-1,CurrentColumn,state));
        if(CurrentColumn+1 < getNumOfColumns() && MazeInfo[CurrentRow][CurrentColumn+1] == 0 || CurrentColumn+1 < getNumOfColumns() && MazeInfo[CurrentRow][CurrentColumn+1] == 8)//if right is blank
            Successors.add(new MazeState(CurrentRow,CurrentColumn+1,state));
        if(CurrentRow+1 < getNumOfRows() && MazeInfo[CurrentRow+1][CurrentColumn] == 0 || CurrentRow+1 < getNumOfRows() && MazeInfo[CurrentRow+1][CurrentColumn] == 8)//if down is blank
            Successors.add(new MazeState(CurrentRow+1,CurrentColumn,state));
        if(CurrentColumn-1 >= 0 && MazeInfo[CurrentRow][CurrentColumn-1] == 0 || CurrentColumn-1 >= 0 && MazeInfo[CurrentRow][CurrentColumn-1] == 8)//if left is blank
            Successors.add(new MazeState(CurrentRow,CurrentColumn-1,state));

        return Successors;
    }
}
