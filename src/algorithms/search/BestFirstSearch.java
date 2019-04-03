package algorithms.search;

public class BestFirstSearch extends BreadthFirstSearch{
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
