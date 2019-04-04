package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    private int NumberOfNodesEvaluated = 0;

    /**
     *
     * @param s an object that we can search on him
     * @return a solve of the problem
     */
    @Override
    public Solution solve(ISearchable s) {
        ArrayList<AState> discovered = new ArrayList<>();
        Solution sol = new Solution();
        AState goal = s.getGoalState();
        AState temp = goal;
        Stack<AState> stack = new Stack<>();
        stack.push(goal);
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (s.getStartState().equals(temp)) {
                break;
            }
            if (!discovered.contains(temp)) {
                discovered.add(temp);
                for (AState adjState : s.getAllSuccessors(temp)) {
                    stack.push(adjState);
                    NumberOfNodesEvaluated++;
                }
            }
        }
        for (AState state = temp; state != null; state = state.getCameFrom()) {
            sol.addState(state);
        }
        return sol;
    }

    /**
     *
     * @return the name of the Searching Algorithm
     */
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

    /**
     *
     * @return the number of nodes that the algorithm has evaluated during the solve.
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }
}
