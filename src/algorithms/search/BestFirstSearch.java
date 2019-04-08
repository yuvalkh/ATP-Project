package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch() {
        AStateComparator compar = new AStateComparator();
        priorityQ = new PriorityQueue(compar);
    }

    /**
     * @return the name of the Algorithm
     */
    @Override
    public String getName() {
        return "Best First Search";
    }

    /**
     * @param state the state we want to set a cost to
     */
    @Override
    protected void giveCost(AState state) {
        state.setCost(state.getCameFrom().getCost() + state.getDistanceFromGoal(Goal));
    }
}
