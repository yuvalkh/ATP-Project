package algorithms.search;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    private int NumberOfNodesEvaluated = 0;
    protected Queue<AState> priorityQ;
    protected AState Goal;

    public BreadthFirstSearch() {
        priorityQ = new LinkedList();
    }

    /**
     * @param s an object that we can search on him
     * @return a solution of the problem
     */
    @Override
    public Solution solve(ISearchable s) {
        if(s != null) {
            HashSet<AState> discoveredStates = new HashSet<>();
            //ArrayList<AState> discoveredStates = new ArrayList<>();
            Solution sol = new Solution();
            ArrayList<AState> list = new ArrayList<>();
            AState start = s.getStartState();
            AState goal = s.getGoalState();
            Goal = goal;
            boolean Flag = false;
            AState temp = null;
            priorityQ.add(start);
            discoveredStates.add(start);
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
                            if(goal.equals(adjState)){
                                Flag = true;
                                break;
                            }
                        }
                    }
                    if(Flag)
                        break;
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
        state.setCost(0);
    }

}
