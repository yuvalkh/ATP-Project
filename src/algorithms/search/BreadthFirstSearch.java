package algorithms.search;


import javax.swing.plaf.nimbus.State;
import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    private int NumberOfNodesEvaluated = 0;
    AState Goal;

    /**
     * @param s an object that we can search on him
     * @return a solution of the problem
     */
    @Override
    public Solution solve(ISearchable s) {
        if(s != null) {
            HashSet<AState> discoveredStates = new HashSet<>();
            //ArrayList<AState> discoveredStates = new ArrayList<>();
            PriorityQueue<AState> priorityQ;
            Solution sol = new Solution();
            ArrayList<AState> list = new ArrayList<>();
            AStateComparator compar = new AStateComparator();
            priorityQ = new PriorityQueue<>(compar);
            AState start = s.getStartState();
            AState goal = s.getGoalState();
            Goal = goal;
            AState temp = null;
            priorityQ.add(start);
            while (!priorityQ.isEmpty()) {
                temp = priorityQ.remove();
                if (goal.equals(temp)) {
                    break;
                } else {
                    for (AState adjState : s.getAllSuccessors(temp)) {
                        if (!discoveredStates.contains(adjState)) {
                            discoveredStates.add(adjState);
                            giveCost(adjState);
                            adjState.setCameFrom(temp);
                            priorityQ.add(adjState);
                            NumberOfNodesEvaluated++;
                        }
                    }
                }
            }
            //now we run on the solution to get a list from start to the end
            for (AState state = temp; state != null; state = state.getCameFrom()) {
                list.add(state);
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                sol.addState(list.remove(i));
            }
            return sol;
        }
        return null;
    }

    /**
     * @return the name of the Searching Algorithm
     */
    @Override
    public String getName() {
        return "Breadth First Search";
    }

    /**
     * @return the number of nodes the algorithm has Evaluated during the solve
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }

    /**
     * @param state the state we want to give a cost to
     */
    protected void giveCost(AState state) {
        return;
    }

}
