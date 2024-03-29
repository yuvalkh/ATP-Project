package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    private int NumberOfNodesEvaluated = 0;
//..
    @Override
    public Solution solve(ISearchable s) {
        if (s != null) {
            HashSet<AState> discovered = new HashSet<>();
            //ArrayList<AState> discovered = new ArrayList<>();
            Solution sol = new Solution();
            AState goal = s.getGoalState();
            AState temp = goal;
            Stack<AState> stack = new Stack<>();
            stack.push(goal);
            discovered.add(goal);
            while (!stack.isEmpty()) {
                temp = stack.pop();
                if (s.getStartState().equals(temp)) {
                    System.out.println("Found");
                    break;
                }
                    for (AState adjState : s.getAllSuccessors(temp)) {
                        if(!discovered.contains(adjState)){
                            discovered.add(adjState);
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
        return "Depth First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }
}
