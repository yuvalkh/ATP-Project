package algorithms.search;


import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    private int NumberOfNodesEvaluated=0;
    AState Goal;
    @Override
    public Solution solve(ISearchable s) {
        ArrayList<AState> discoveredStates = new ArrayList<>();
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
        while (!priorityQ.isEmpty()){
            temp = priorityQ.remove();
            if(goal.equals(temp)){
                break;
            }
            else {
                for (AState adjState : s.getAllSuccessors(temp)) {
                    if(!discoveredStates.contains(adjState)){
                        discoveredStates.add(adjState);
                        giveCost(adjState);
                        adjState.setCameFrom(temp);
                        priorityQ.add(adjState);
                        NumberOfNodesEvaluated++;
                    }
                }
            }
        }
        for (AState state = temp ;state != null;state = state.getCameFrom()) {
            list.add(state);
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            sol.addState(list.remove(i));
        }
        return sol;
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return NumberOfNodesEvaluated;
    }

    protected void giveCost(AState state){
        //state.setCost(state.getCameFrom().getCost() + 1);
        //state.setCost(0);
        return;
    }

}
