package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    private int NumberOfNodesEvaluated = 0;
//.
    @Override
    public Solution solve(ISearchable s) {
        if (s != null) {

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
        return null;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }
}
