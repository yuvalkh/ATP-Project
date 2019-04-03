package algorithms.search;

import java.util.ArrayList;

public class BestFirstSearch extends BreadthFirstSearch{

    /*@Override
    public Solution solve(ISearchable s) {
        Solution withCrosses = super.solve(s);
        ArrayList<AState> Final = withCrosses.getSolutionPath();
        for (int i=0; i < withCrosses.getSolutionPath().size(); i++) {
            if(((MazeState)Final.get(i)).getCurrentPosition().getRowIndex())
        }

        return withCrosses;
    }*/
    @Override
    public String getName() {
        return "Best First Search";
    }

    @Override
    protected void giveCost(AState state){
        //state.setCost(state.getCameFrom().getCost() + 1);
        state.setCost(state.getCameFrom().getCost() + state.getDistanceFromGoal(Goal));
    }
}
