package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    private ArrayList<AState> Solve;

    public Solution() {
        Solve = new ArrayList<>();
    }

    /**
     *
     * @param state the state we want to add to the solution
     */
    public void addState(AState state){
        Solve.add(state);
    }

    /**
     *
     * @return the solution
     */
    public ArrayList<AState> getSolutionPath(){ return Solve; }
}
