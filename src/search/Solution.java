package search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> Solve;

    public Solution() {
        Solve = new ArrayList<>();
    }
    public void addState(AState state){
        Solve.add(state);
    }
}
