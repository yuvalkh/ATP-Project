package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> Solve;

    public Solution() {
        Solve = new ArrayList<>();
    }

    public void addState(AState state) {
        Solve.add(state);
    }

    public ArrayList<AState> getSolutionPath() {
        return Solve;
    }
}
