package algorithms.search;

import org.omg.CORBA.WrongTransactionHelper;

import java.util.Comparator;

public abstract class AState {
    private AState cameFrom;
    private String stateName;
    private double cost;

    /**
     *
     * @param state the name of the state
     * @param from the state that we got from
     * @param cost the cost it took to get to this state
     */
    public AState(String state,AState from,double cost){
        stateName = state;
        this.cost = cost;
        cameFrom = from;
    }

    /**
     *
     * @param cameFrom the state that brought us to this state
     */
    public void setCameFrom(AState cameFrom) {
        if(cameFrom != null)
            this.cameFrom = cameFrom;
    }

    /**
     *
     * @return the cost of the state
     */
    public double getCost() {
        return cost;
    }

    /**
     *
     * @param cost the cost we want to give to the state
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     *
     * @return the state that brought us to this state
     */
    public AState getCameFrom() {
        return cameFrom;
    }

    /**
     *
     * @param s the object we want to compare
     * @return if they are the same or not
     */
    public abstract boolean equals(Object s);

    /**
     *
     * @param Goal The goal of the maze
     * @return
     */
    public abstract double getDistanceFromGoal(AState Goal);
}
